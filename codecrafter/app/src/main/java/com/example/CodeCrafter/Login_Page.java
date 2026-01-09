package com.example.CodeCrafter;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;
public class Login_Page extends AppCompatActivity {
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String stringEmail;
    String stringPassword;
    TextInputEditText userInputUsername;
    TextInputEditText userInputPassword;
    TextView ForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ForgotPassword = findViewById(R.id.ForgotPassword);
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Page.this, Change_Password.class));
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        TextView toRegister = findViewById(R.id.toRegister_Hyperlink);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(Login_Page.this, Register_Page.class);
                startActivity(toRegister);
            }
        });
        Button login_button = findViewById(R.id.login_button);
        findingViewById();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringEmail = Objects.requireNonNull(userInputUsername.getText()).toString();
                stringPassword = Objects.requireNonNull(userInputPassword.getText()).toString();
                isValidData();
            }
        });
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent toMainActivity = new Intent(Login_Page.this, Library.class);
            startActivity(toMainActivity);
        }
    }
    private void isValidData() {
        progressDialog = new ProgressDialog(Login_Page.this);

        if (!isValidEmail(stringEmail)) {
            userInputUsername.setError("The provided email address is invalid.");
            return;
        }
        if (stringPassword.isEmpty()) {
            userInputPassword.setError("Please enter your password.");
        }
        if (stringPassword.length() < 8) {
            userInputPassword.setError("Please enter a password with a valid length.");
        }

        progressDialog.setMessage("Login in process...");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(stringEmail, stringPassword).addOnCompleteListener(Login_Page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user != null && user.isEmailVerified()) {
                        startActivity(new Intent(Login_Page.this, Library.class));
                        finish();
                    } else {
                        Toast.makeText(Login_Page.this, "Verification of your email address is required.", Toast.LENGTH_SHORT).show();
                        resetUI();
                        progressDialog.dismiss();
                    }
                } else {
                    Toast.makeText(Login_Page.this, "Authentication failed. Please check your credentials and Wi-Fi connection.", Toast.LENGTH_SHORT).show();
                    resetUI();
                    progressDialog.dismiss();
                }
            }
        });
    }
    private void findingViewById() {
        userInputUsername = findViewById(R.id.valid_username);
        userInputPassword = findViewById(R.id.valid_password);
    }
    private void resetUI() {
        userInputUsername.setText("");
        userInputPassword.setText("");
    }
}