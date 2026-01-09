package com.example.CodeCrafter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class CreateNotes extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    String currentUserEmail;
    DocumentReference documentReference;
    ImageButton backToLibrary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_notes);

        backToLibrary = findViewById(R.id.backToLibrary);
        backToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateNotes.this, Library.class));
                finish();
            }
        });

        EditText NotesTitle = findViewById(R.id.NotesTitle);
        EditText NotesContent = findViewById(R.id.NotesContent);
        Button SaveNotes = findViewById(R.id.SaveNotes);
        SaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = generateRandomId();

                String noteTitle = NotesTitle.getText().toString();
                String noteContent = NotesContent.getText().toString();
                Calendar calendar = Calendar.getInstance();

                if (noteTitle.isEmpty()) {
                    noteTitle = generateRandomTitle();
                }

                if (noteContent.isEmpty()) {
                    noteContent = generateRandomWords();
                }

                try {
                    Notes notes = new Notes(noteTitle, noteContent, calendar.getTime());
                    Map<String, Object> notesData = new HashMap<>();
                    notesData.put("id", id);
                    notesData.put("title", notes.getTitle());
                    notesData.put("content", notes.getContent());
                    notesData.put("time", new Timestamp(notes.getTimeStamp()));

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();

                    if (user != null) {
                        currentUserEmail = user.getEmail();
                        Log.d("CurrentUserEmail", "Email: " + currentUserEmail);
                        documentReference = database.collection("UserNotes").document(currentUserEmail);
                        documentReference.collection("Notes").add(notesData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.e("CreateNotes", documentReference.getId());
                                showDialog();
                                startActivity(new Intent(CreateNotes.this, Library.class));
                                NotesTitle.setText("");
                                NotesContent.setText("");
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("CreateNotes", "Failed to add note", e);
                            }
                        });
                    } else {
                        Log.d("CurrentUserEmail", "No user signed in");
                    }
                } catch (Exception e) {
                    Log.e("CreateNotes", "Error creating note", e);
                    Toast.makeText(CreateNotes.this, "" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private String generateRandomTitle() {
        String[] fruits = {
                "Apple", "Banana", "Orange", "Strawberry", "Grape",
                "Watermelon", "Pineapple", "Mango", "Kiwi", "Peach",
                "Pear", "Cherry", "Lemon", "Blueberry", "Raspberry",
                "Blackberry", "Coconut", "Avocado", "Pomegranate", "Apricot",
                "Plum", "Fig", "Papaya", "Lychee", "Cantaloupe",
                "Honeydew", "Guava", "Passion Fruit", "Dragon Fruit", "Kumquat"
        };

        int randomIndex = (int) (Math.random() * fruits.length);
        return fruits[randomIndex];
    }
    private String generateRandomWords() {
        String[] nameOfFruits = {
                "mighty Apple", "glorious Banana", "fantastic Orange", "brave Mango", "magnificent Pineapple",
                "wise Watermelon", "clever Grapes", "fearless Strawberry", "regal Cherry", "swift Peach",
                "mighty Pear", "glorious Kiwi", "fantastic Plum", "brave Apricot", "magnificent Lemon",
                "wise Lime", "clever Grapefruit", "fearless Pomegranate", "regal Coconut", "swift Avocado",
                "mighty Papaya", "glorious Lychee", "fantastic Cantaloupe", "brave Honeydew", "magnificent Raspberry",
                "wise Blueberry", "clever Blackberry", "fearless Cranberry", "regal Fig", "swift Guava",
                "mighty Passionfruit", "glorious Dragonfruit", "fantastic Kiwi", "brave Tangerine", "magnificent Clementine",
                "wise Nectarine", "clever Rambutan", "fearless Starfruit", "regal Persimmon", "swift Elderberry",
                "mighty Gooseberry", "glorious Mulberry", "fantastic Currant", "brave Kumquat", "magnificent Quince",
                "wise Jackfruit", "clever Date", "fearless Plantain"
        };

        StringBuilder loremIpsum = new StringBuilder();
        int numWords = (int) (Math.random() * 20) + 5;
        for (int i = 0; i < numWords; i++) {
            int index = (int) (Math.random() * nameOfFruits.length);
            loremIpsum.append(nameOfFruits[index]).append(" ");
        }
        return loremIpsum.toString().trim();
    }
    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Wait")
                .setCancelable(false);
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                    }
//                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}