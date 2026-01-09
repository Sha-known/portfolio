package com.example.CodeCrafter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Library extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    BottomNavigationView bottomNavigationView;
    LinearLayout NotesContainer;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout containerLayout;
    GradientDrawable border;
    LinearLayout.LayoutParams titleParams;
    LinearLayout.LayoutParams contentParams;
    LinearLayout.LayoutParams timeParams;
    GradientDrawable contentBorder;
    EditText LibrarySearch;
    String currentUserEmail;
    ImageView SearchIcon;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        SearchIcon = findViewById(R.id.SearchIcon);
        LibrarySearch = findViewById(R.id.LibrarySearch);
        LibrarySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchQuery = s.toString().trim().toLowerCase();
                // BELOW THIS COMMENT
                if (NotesContainer.getChildCount() == 0) {
                    return;
                }
                // ABOVE THIS COMMENT
                for (int i = 0; i < NotesContainer.getChildCount(); i++) {
                    LinearLayout containerLayout = (LinearLayout) NotesContainer.getChildAt(i);
                    TextView titleView = (TextView) containerLayout.getChildAt(0);
                    String title = titleView.getText().toString().toLowerCase();
                    if (title.contains(searchQuery)) {
                        containerLayout.setVisibility(View.VISIBLE);
                    } else {
                        containerLayout.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        LibrarySearch.setFocusable(false);
        LibrarySearch.setFocusableInTouchMode(false);
        LibrarySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibrarySearch.setFocusable(true);
                LibrarySearch.setFocusableInTouchMode(true);
                LibrarySearch.requestFocus();
            }
        });
        LibrarySearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    LibrarySearch.setFocusable(false);
                    LibrarySearch.setFocusableInTouchMode(false);
                }
            }
        });
        SearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = LibrarySearch.getText().toString().trim().toLowerCase();
                if(NotesContainer.getChildCount() == 0) {
                    return;
                }
                for (int i = 0; i < NotesContainer.getChildCount(); i++) {
                    LinearLayout containerLayout = (LinearLayout) NotesContainer.getChildAt(i);
                    TextView titleView = (TextView) containerLayout.getChildAt(0);
                    String title = titleView.getText().toString().toLowerCase();
                    if (title.contains(searchQuery)) {
                        containerLayout.setVisibility(View.VISIBLE);
                    } else {
                        containerLayout.setVisibility(View.GONE);
                    }
                }
            }
        });
        NotesContainer = findViewById(R.id.NotesContainer);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            currentUserEmail = user.getEmail();
            Log.d("CurrentUserEmail", "Email: " + currentUserEmail);
            loadNotesData(currentUserEmail);
        } else {
            Log.d("CurrentUserEmail", "No user signed in");
        }
        ImageButton addTopicToLibrary = findViewById(R.id.addTopicToLibrary);
        addTopicToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Library.this, CreateNotes.class));
            }
        });
        ImageView ProfileIcon = findViewById(R.id.ProfileIcon);
        ProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(Library.this, Account.class);
                startActivity(toProfile);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.library);
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
                } else if (itemId == R.id.account) {
                    startActivity(new Intent(getApplicationContext(), Account.class));
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
    private void loadNotesData(String currentUserEmail) {
        documentReference = database.collection("UserNotes").document(currentUserEmail);
        documentReference.collection("Notes").orderBy("time", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                        String noteTitle = document.getString("title");
                        String noteContent = document.getString("content");
                        Date currentDate = document.getDate("time");
                        String storedId = document.getString("id");
                        Log.d("NoteData", "Title: " + noteTitle + ", Content: " + noteContent + ", Time: " + currentDate.toString());
                        TextView titleView = new TextView(Library.this);
                        TextView contentView = new TextView(Library.this);
                        TextView timeView = new TextView(Library.this);
                        titleView.setTextColor(Color.BLACK);
                        contentView.setTextColor(Color.BLACK);
                        timeView.setTextColor(Color.BLACK);
                        Notes notes = new Notes(noteTitle, noteContent, currentDate);
                        String title = notes.getTitle();
                        String content = notes.getContent();
                        titleView.setText(title);
                        contentView.setText(content);
                        timeView.setText(formatDate(currentDate));
                        containerLayout = new LinearLayout(Library.this);
                        border = new GradientDrawable();
                        border.setColor(0xFFFFFFFF);
                        border.setStroke(2, 0xFF000000);
                        border.setCornerRadius(15);
                        contentBorder = new GradientDrawable();
                        contentBorder.setColor(0xFFFFFFFF);
                        contentBorder.setStroke(2, 0xFF000000);
//                        contentBorder.setCornerRadius(10);
                        contentBorder.setBounds(
                                10,
                                10,
                                10,
                                10
                        );
                        layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        titleParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        contentParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        timeParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams.setMargins(20, 10, 20, 10);
                        titleView.setGravity(Gravity.CENTER);
                        contentView.setGravity(Gravity.CENTER);
                        timeView.setGravity(Gravity.CENTER);
//                        timeView.setBackground(contentBorder);
                        titleView.setTextSize(20);
                        titleParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        contentView.setTextSize(18);
                        contentParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                        timeView.setPadding(10, 10, 10, 10);
                        titleView.setLayoutParams(titleParams);
                        titleView.setPadding(10, 10, 10, 10);
                        contentView.setLayoutParams(contentParams);
                        contentView.setPadding(10, 10, 10, 10);
                        timeView.setLayoutParams(timeParams);
//                        titleView.setBackground(contentBorder);
                        contentView.setBackground(contentBorder);
//                        timeView.setBackground(contentBorder);
//                    titleView.setGravity(Gravity.CENTER);
//                    contentView.setGravity(Gravity.CENTER);
//                    timeView.setGravity(Gravity.CENTER);
                        containerLayout.setOrientation(LinearLayout.VERTICAL);
                        containerLayout.addView(titleView);
                        containerLayout.addView(contentView);
                        containerLayout.addView(timeView);
                        containerLayout.setBackground(border);
                        containerLayout.setLayoutParams(layoutParams);
//                        containerLayout.setPadding(20, 30, 20, 20);
                        NotesContainer.addView(containerLayout, 0);
                        containerLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String stringTitle = titleView.getText().toString();
                                String stringContent = contentView.getText().toString();
                                String stringTime = timeView.getText().toString();
                                Intent toEditNotes = new Intent(Library.this, EditNotes.class);
                                toEditNotes.putExtra("title", stringTitle);
                                toEditNotes.putExtra("content", stringContent);
                                toEditNotes.putExtra("time", stringTime);
                                toEditNotes.putExtra("id", storedId);
                                Log.e("OnClickData", "title:" + stringTitle + "content:" + stringContent + "time:" + stringTime);
                                startActivity(toEditNotes);
                                finish();
                            }
                        });
                    }
                } else {
                    ImageView imageView = new ImageView(Library.this);
                    imageView.setImageResource(R.drawable.empty);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setAlpha(0.25f);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(20, 20, 20, 20);

                    imageView.setLayoutParams(params);
                    NotesContainer.addView(imageView);
                    LibrarySearch.setEnabled(false);
                    SearchIcon.setEnabled(false);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("LibraryFailed", "Failed to retrieve notes", e);
            }
        });
    }
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy 'at' h:mm:ss a 'UTC'Z", Locale.getDefault());
        String formattedDate = sdf.format(date);
        return formattedDate.replaceAll(" at 0?(\\d+):(\\d+):(\\d+) ", " at $1:$2:$3 ")
                .replaceAll("UTC\\+0+(\\d+)(?:00)?", "UTC+$1");
    }
    @Override
    public void onStart() {
        super.onStart();
        LibrarySearch.setFocusable(false);
        LibrarySearch.setFocusableInTouchMode(false);
    }
    @Override
    public void onResume() {
        super.onResume();
        LibrarySearch.setFocusable(false);
        LibrarySearch.setFocusableInTouchMode(false);
    }
}