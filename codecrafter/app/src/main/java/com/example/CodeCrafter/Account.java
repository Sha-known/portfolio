package com.example.CodeCrafter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Account extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    TextView Username;
    TextView Gender;
    TextView Age;
    TextView Date_of_birth;
    TextView Mobile;
    TextView Email;
    TextView Name;
    String collectionEmail;
    String storedAge;
    String storedDateOfBirth;
    String storedEmail;
    String storedGender;
    String storedMobile;
    String storedUsername;
    ImageView ProfilePicture;
    String imageCollectionPath;
    String email;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CODE_PERMISSION = 100;
    DocumentReference documentReference;
    TextView userScore;
    String stringCurrentEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userScore = findViewById(R.id.userScore);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            email = user.getEmail();
            Log.d("CurrentUserEmail", "Email: " + email);
            imageCollectionPath = email;
            stringCurrentEmail = email;
            checkProfilePictureExists();
        } else {
            Log.d("CurrentUserEmail", "No user signed in");
        }

        findingViewById();
        getCurrentUser();

        Button signOutButton = findViewById(R.id.signOut_Button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent toLogin = new Intent(Account.this, Login_Page.class);
                toLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toLogin);
                finish();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.library) {
                    startActivity(new Intent(getApplicationContext(), Library.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (itemId == R.id.learn) {
                    startActivity(new Intent(getApplicationContext(), Learn.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                } else if (itemId == R.id.quizzes) {
                    startActivity(new Intent(getApplicationContext(), Quizzes.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                return false;
            }
        });
        ProfilePicture = findViewById(R.id.ProfilePicture);
        ProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(Account.this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Account.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_CODE_PERMISSION);
                    } else {
                        openGallery();
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(Account.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Account.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                    } else {
                        openGallery();
                    }
                }
            }
        });
        ProfilePicture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                loadProfilePicture();
                Toast.makeText(Account.this, "You long press this ImageView", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void findingViewById() {
        Username = findViewById(R.id.Username);
        Gender = findViewById(R.id.Gender);
        Age = findViewById(R.id.Age);
        Date_of_birth = findViewById(R.id.Date_of_birth);
        Mobile = findViewById(R.id.Mobile);
        Email = findViewById(R.id.Email);
        Name = findViewById(R.id.Name);
    }
    private void loadData(String collectionEmail) {
        database.collection("Account").whereEqualTo("email", collectionEmail).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    storedAge = document.getString("age");
                    storedDateOfBirth = document.getString("date_of_birth");
                    storedEmail = document.getString("email");
                    storedGender = document.getString("gender");
                    storedMobile = document.getString("mobile");
                    storedUsername = document.getString("username");

                    Username.setText(storedUsername);
                    Gender.setText(storedGender);
                    Age.setText(storedAge);
                    Date_of_birth.setText(storedDateOfBirth);
                    Mobile.setText(storedMobile);
                    Email.setText(storedEmail);
                    Name.setText(storedUsername);
                }
            }
        });
    }
    private void getCurrentUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            Log.d("CurrentUserEmail", "Email: " + email);
            collectionEmail = email;
            loadData(collectionEmail);
        } else {
            Log.d("CurrentUserEmail", "No user signed in");
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied to access the gallery", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            database.collection("UserImages").document(imageCollectionPath).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        updateImageData(imageUri);
                        Intent intent = getIntent();
                        startActivity(intent);
                    } else {
                        uploadNewImageData(imageUri);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("UploadImage", "Error checking image data existence", e);
                }
            });
        }
    }
    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }
    private void loadProfilePicture() {
        database.collection("UserImages").document(imageCollectionPath).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String imageUrl = document.getString("imageUrl");
                        Picasso.get().load(imageUrl).into(ProfilePicture);
                    } else {
                        Log.d("loadProfilePicture", "No such document");
                    }
                } else {
                    Log.e("loadProfilePicture", "Error getting document", task.getException());
                }
            }
        });
    }
    private void checkProfilePictureExists() {
        database.collection("UserImages").document(imageCollectionPath).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    loadProfilePicture();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("CheckProfilePicture", "Error checking profile picture existence", e);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        checkProfilePictureExists();
        updateUserScore();
    }
    private void uploadNewImageData(Uri imageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://codecrafter-937b3.appspot.com/Images").child("Images/");
        String imageName = "image_" + System.currentTimeMillis();
        StorageReference imageRef = storageRef.child("images/" + imageName + ".jpg");
        UploadTask uploadTask = imageRef.putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        String id = generateRandomId();

                        Map<String, Object> imageMap = new HashMap<>();
                        imageMap.put("imageUrl", imageUrl);
                        imageMap.put("id", id);

                        database.collection("UserImages").document(imageCollectionPath).set(imageMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("UploadImage", "New image URL added to Firestore under user's document");
                                        loadProfilePicture();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("UploadImage", "Error adding new image URL to Firestore", e);
                                    }
                                });
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("UploadImage", "Error uploading image", e);
            }
        });
    }
    private void updateImageData(Uri imageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://codecrafter-937b3.appspot.com/Images").child("Images/");
        String imageName = "image_" + System.currentTimeMillis();
        StorageReference imageRef = storageRef.child("images/" + imageName + ".jpg");
        UploadTask uploadTask = imageRef.putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        String id = generateRandomId();

                        Map<String, Object> imageMap = new HashMap<>();
                        imageMap.put("imageUrl", imageUrl);
                        imageMap.put("id", id);

                        database.collection("UserImages").document(imageCollectionPath).set(imageMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("UploadImage", "Image URL updated in Firestore under user's document");
                                        loadProfilePicture();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("UploadImage", "Error updating image URL in Firestore", e);
                                    }
                                });
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("UploadImage", "Error uploading image", e);
            }
        });
    }
    private void updateUserScore() {
        if(stringCurrentEmail != null) {
            documentReference = database.collection("UserScores").document(imageCollectionPath);
            documentReference.collection("Score")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                long totalScore = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Long score = document.getLong("score");
                                    if (score != null) {
                                        totalScore += score;
                                    }
                                }
                                userScore.setText(String.valueOf(totalScore));
                            } else {
                                Log.e("UserData", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
    }
}
