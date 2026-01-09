package com.example.CodeCrafter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Change_Password extends AppCompatActivity {
    EditText EmailAddress;
    Button SubmitButton;
    String email;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        firebaseAuth = FirebaseAuth.getInstance();

        EmailAddress = findViewById(R.id.EmailAddress);
        SubmitButton = findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = EmailAddress.getText().toString().trim();
                if(email.isEmpty()) {
                    EmailAddress.setError("Enter your email address");
                    return;
                }
                if(!isValidEmail(email)) {
                    EmailAddress.setError("Invalid Email Address");
                    return;
                }
                sendPasswordResetEmail(email);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void sendPasswordResetEmail(String email) {
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Change_Password.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Change_Password.this, Login_Page.class));
                            finish();
                        } else {
                            Toast.makeText(Change_Password.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Change_Password.this, Login_Page.class));
                            finish();
                        }
                    }
                });
    }
}