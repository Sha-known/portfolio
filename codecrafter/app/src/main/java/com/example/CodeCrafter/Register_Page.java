package com.example.CodeCrafter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.Objects;
public class Register_Page extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    String stringUsername;
    String stringAge;
    String stringGender;
    String stringDate_of_birth;
    String stringMobile;
    String stringEmail;
    String stringPassword;
    String stringRetype_password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    RadioGroup RadioParent;
    int selectedRadioButton;
    RadioButton selectedButton;
    TextInputEditText usernameInput;
    TextInputEditText ageInput;
    TextInputEditText date_of_birthInput;
    TextInputEditText mobileInput;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    TextInputEditText retype_passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        RadioParent = findViewById(R.id.RadioParent);
        firebaseAuth = FirebaseAuth.getInstance();
        TextView toLogin = findViewById(R.id.toLogin_Hyperlink);
        findingViewById();
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(Register_Page.this, Login_Page.class);
                startActivity(toLogin);
            }
        });
        Button register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButton = RadioParent.getCheckedRadioButtonId();
                selectedButton = findViewById(selectedRadioButton);
                if(selectedRadioButton != -1) {
                    stringGender = (String) selectedButton.getText();
                } else {
                    Log.d("SelectedRadioButton", "No Selected Radio Button");
                }
                extractData();
                isValidData();
            }
        });
    }
    public void saveDataToFirebase(User user) {
        database.collection("Account").whereEqualTo("email", stringEmail).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (!task.getResult().isEmpty()) {
                        Toast.makeText(Register_Page.this, "Duplicate email address detected.", Toast.LENGTH_SHORT).show();
                    } else {
                        database.collection("Account").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Register_Page.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register_Page.this, "Registration unsuccessful.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Log.d("Registration", "Error getting documents: ", task.getException());
                    Toast.makeText(Register_Page.this, "There was an error while verifying the email existence.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void extractData() {
        stringUsername = Objects.requireNonNull(usernameInput.getText()).toString();
        stringAge = Objects.requireNonNull(ageInput.getText()).toString();
        stringDate_of_birth = Objects.requireNonNull(date_of_birthInput.getText()).toString();
        stringMobile = Objects.requireNonNull(mobileInput.getText()).toString();
        stringEmail = Objects.requireNonNull(emailInput.getText()).toString();
        stringPassword = Objects.requireNonNull(passwordInput.getText()).toString();
        stringRetype_password = Objects.requireNonNull(retype_passwordInput.getText()).toString();
    }
    private void isValidData() {
        progressDialog = new ProgressDialog(Register_Page.this);
        try {
            if(!stringPassword.equals(stringRetype_password)) {
                passwordInput.setError("Password does not match");
                retype_passwordInput.setError("Password does not match");
                return;
            }
            if(stringPassword.isEmpty()) {
                passwordInput.setError("Input a Password");
                return;
            }
            if(stringPassword.length() < 8) {
                passwordInput.setError("Enter a valid password length");
                return;
            }
            if(stringAge.isEmpty()) {
                ageInput.setError("Input an age");
                return;
            }
            if(stringMobile.isEmpty()) {
                mobileInput.setError("Input a mobile number");
            }
            if(stringMobile.length() < 11) {
                mobileInput.setError("Invalid mobile number");
                return;
            }
            if(!isValidEmail(stringEmail)) {
                emailInput.setError("Invalid email address");
                return;
            }
            progressDialog.setMessage("Please wait while registering...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(stringEmail, stringPassword).addOnCompleteListener(Register_Page.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(Register_Page.this, "Account creation successful.", Toast.LENGTH_SHORT).show();
                        sendEmailVerification();
                        resetUI();
                        progressDialog.dismiss();
                        createFireStoreData();
                    } else {
                        Toast.makeText(Register_Page.this, "Account creation unsuccessful.", Toast.LENGTH_SHORT).show();
                        resetUI();
                        progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            Log.e("Register", Objects.requireNonNull(e.getMessage()));
        }
    }
    private void findingViewById() {
        usernameInput = findViewById(R.id.usernameInput);
        ageInput = findViewById(R.id.ageInput);
        date_of_birthInput = findViewById(R.id.date_of_birthInput);
        mobileInput = findViewById(R.id.mobileInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        retype_passwordInput = findViewById(R.id.retype_passwordInput);
    }
    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Register_Page.this, "Verification email sent. Please verify and log in again.", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(Register_Page.this, Login_Page.class));
                }
            });
        } else {
            Toast.makeText(Register_Page.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
        }
    }
    private void resetUI() {
        usernameInput.setText("");
        ageInput.setText("");
        date_of_birthInput.setText("");
        mobileInput.setText("");
        emailInput.setText("");
        passwordInput.setText("");
        retype_passwordInput.setText("");
        RadioParent.clearCheck();
    }
    private void createFireStoreData() {
        Encryption encryption = new Encryption();
        String hashPassword = encryption.hashPassword(stringPassword);
        User user = new User(stringUsername, stringGender, stringAge, stringDate_of_birth, stringMobile, stringEmail, hashPassword);
        saveDataToFirebase(user);
    }
}