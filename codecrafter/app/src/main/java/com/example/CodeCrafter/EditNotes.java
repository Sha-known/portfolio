package com.example.CodeCrafter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Date;
import com.google.firebase.Timestamp;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class EditNotes extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    Intent intent;
    String editedTitle;
    String editedContent;
    String title;
    String content;
    String time;
    String generatedId;
    String currentUserEmail;
    DocumentReference documentReference;
    ImageButton backLibrary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_notes);

        backLibrary = findViewById(R.id.backLibrary);
        backLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditNotes.this, Library.class));
                finish();
            }
        });

        Date currentDate = new Date();
        Timestamp currentTimeStamp = new Timestamp(currentDate);

        EditText EditTitle = findViewById(R.id.EditTitle);
        EditText EditContent = findViewById(R.id.EditContent);
        Button SaveChanges = findViewById(R.id.SaveChanges);
        Button DeleteNotes = findViewById(R.id.DeleteNotes);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        time = intent.getStringExtra("time");
        generatedId = intent.getStringExtra("id");

        Log.e("EditNotes", "title:" + title + "content:" + content + "time:" + time + "id:" + generatedId);

        EditTitle.setText(title);
        EditContent.setText(content);

        assert user != null;
        currentUserEmail = user.getEmail();
        Log.d("CurrentUserEmail", "Email: " + currentUserEmail);

        SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ClickedButton", "You clicked a button");
                if (currentUserEmail != null) {
                    editedTitle = EditTitle.getText().toString();
                    editedContent = EditContent.getText().toString();
                    Log.e("EditNote", "Title:" + editedTitle + "Content:" + editedContent);
                    documentReference = database.collection("UserNotes").document(currentUserEmail);
                    documentReference.collection("Notes").whereEqualTo("id", generatedId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    document.getReference().update(
                                            "title", editedTitle,
                                            "content", editedContent,
                                            "time", currentTimeStamp
                                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                showDialog();
                                                startActivity(new Intent(EditNotes.this, Library.class));
                                                EditTitle.setText("");
                                                EditContent.setText("");
                                                finish();
                                            } else {
                                                Log.e("elseFailed", String.valueOf(task.getException()));
                                            }
                                        }
                                    });
                                }
                            } else {
                                Log.e("EditNotes", "Error getting documents", task.getException());
                            }
                        }
                    });
                } else {
                    Log.e("EditNotes", "Collection email is null");
                }
            }
        });

        DeleteNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentReference = database.collection("UserNotes").document(currentUserEmail);
                documentReference.collection("Notes").whereEqualTo("id", generatedId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                document.getReference().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        showDialog();
                                        startActivity(new Intent(EditNotes.this, Library.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("DeleteNotes", "Error deleting document", e);
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Wait")
                .setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }
}