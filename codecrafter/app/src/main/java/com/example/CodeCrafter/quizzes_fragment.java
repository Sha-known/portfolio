package com.example.CodeCrafter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class quizzes_fragment extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    TextView quizzes_fragment;
    TextView quizzes_fragmentTitle;
    TextView quizzes_instruction;
    ImageButton toQuizzes;
    EditText EditTextAnswers;
    String userAnswer;
    String answer1;
    String answer2;
    String answer3;
    int score;
    String currentUserEmail;
    Button submitAnswers;
    TextView completeQuiz;
    TextView correctAnwers;
    DocumentReference documentReference;
    String stringId;
    String fullAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quizzes_fragment);
        Log.d("CurrentUserEmail", "Email: " + currentUserEmail);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        assert user != null;
        currentUserEmail = user.getEmail();

        correctAnwers = findViewById(R.id.correctAnwers);

        completeQuiz = findViewById(R.id.completeQuiz);

        submitAnswers = findViewById(R.id.submitAnswers);

        EditTextAnswers = findViewById(R.id.EditTextAnswers);

        toQuizzes = findViewById(R.id.toQuizzes);
        toQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(quizzes_fragment.this, Quizzes.class));
                finish();
            }
        });

        quizzes_fragment = findViewById(R.id.quizzes_fragment_textView);
        quizzes_fragmentTitle = findViewById(R.id.titleQuizzesFragment);
        quizzes_instruction = findViewById(R.id.quizzes_instructions);

        int moduleIndex = getIntent().getIntExtra("moduleIndex", -1);
        int submoduleIndex = getIntent().getIntExtra("submoduleIndex", -1);
        switch (moduleIndex) {
            case 0:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "System";
                        answer2 = "out";
                        answer3 = "println";
                        stringId = "Exercise1";
                        fullAnswer = "System,out,println";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Add the necessary segment to the provided code " +
                                "to display \"Hello World\" as the output.");
                        quizzes_fragment.setText("" +
                                "public class MyClass {\n" +
                                "     public static void main(String[] args) {\n" +
                                "          ______.___._______(\"Hello World\");\n" +
                                "     }\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "//";
                        answer2 = "/*";
                        answer3 = "*/";
                        stringId = "Exercise2";
                        fullAnswer = "//,/*,*/";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Add the required components to the provided statement " +
                                "to create Java comments using special characters.");
                        quizzes_fragment.setText("" +
                                "__ This is a single-line comment\n" +
                                "__ This is a multi-line comment __");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 0, Default");
                        break;
                }
                break;
            case 1:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "String";
                        answer2 = "carName";
                        answer3 = "\"Magma\"";
                        stringId = "Exercise3";
                        fullAnswer = "String,carName,\"Magma\"";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Declare a variable called carName and set its value to \"Magma\".");
                        quizzes_fragment.setText("______ _______ = _______;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "int";
                        answer2 = "carSpeed";
                        answer3 = "150";
                        stringId = "Exercise4";
                        fullAnswer = "int,carSpeed,150";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Declare a variable called carSpeed and initialize it with the value 150.");
                        quizzes_fragment.setText("___ ________ = ___;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "int";
                        answer2 = "x";
                        answer3 = "10";
                        stringId = "Exercise5";
                        fullAnswer = "int,x,10";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Show the result of adding 15 and 5 by utilizing two variables: x and y.");
                        quizzes_fragment.setText("___ _ = __;\n" +
                                "int y = 5;\n" +
                                "System.out.println(x + y);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "int";
                        answer2 = "z";
                        answer3 = "z";
                        stringId = "Exercise6";
                        fullAnswer = "int,z,z";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Define a variable named z, set it equal to the sum of x and y, then show the outcome.");
                        quizzes_fragment.setText("int x = 15;\n" +
                                "int y = 5;\n" +
                                "___ _ = x + y;\n" +
                                "System.out.println(_);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 4:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "int";
                        answer2 = ",";
                        answer3 = ",";
                        stringId = "Exercise7";
                        fullAnswer = "int,,,,";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Complete the statement to establish three variables of the same type, separating each with commas.");
                        quizzes_fragment.setText("___ x = 5_ y = 6_ z = 50;\n" +
                                "System.out.println(x + y + z);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 1, Default");
                        break;
                }
                break;
            case 2:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "float";
                        answer2 = "boolean";
                        answer3 = "String";
                        stringId = "Exercise8";
                        fullAnswer = "float,boolean,String";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Please specify the appropriate data types for the remaining variables provided.");
                        quizzes_fragment.setText("int myNum = 14;\n" +
                                "_____ myFloatNum = 7.68f;\n" +
                                "char myLetter = 'P';\n" +
                                "_______ myBool = false;\n" +
                                "______ myText = \"Hello World\";");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "primitive";
                        stringId = "Exercise9";
                        fullAnswer = "primitive";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("The terms byte, short, int, long, float, double, boolean, and char are known as:");
                        quizzes_fragment.setText("_________ data types.");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "(int)";
                        stringId = "Exercise10";
                        fullAnswer = "(int)";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Perform a type casting to transform the " +
                                "variable myDouble, which is of type double, into an integer (int) type.");
                        quizzes_fragment.setText("double myDouble = 8.45d;\n" +
                                "int myTwice = _____ myDouble;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 2, Default");
                        break;
                }
                break;
            case 3:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "*";
                        stringId = "Exercise11";
                        fullAnswer = "*";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Calculate the product of 15 multiplied by 2, then display the outcome.");
                        quizzes_fragment.setText("System.out.println(15 _ 2);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "/";
                        stringId = "Exercise12";
                        fullAnswer = "/";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Compute the quotient of 12 divided by 2, then print the result.");
                        quizzes_fragment.setText("System.out.println(12 _ 2);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "++";
                        stringId = "Exercise13";
                        fullAnswer = "++";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Employ the appropriate operator to increment the value of variable x by 1.");
                        quizzes_fragment.setText("int x = 10;\n" +
                                "__x;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "+=";
                        stringId = "Exercise14";
                        fullAnswer = "+=";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the addition assignment operator to append the value 5 to the variable x.");
                        quizzes_fragment.setText("int x = 10;\n" +
                                "x __ 5;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 3, Default");
                        break;
                }
                break;
            case 4:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "String";
                        answer2 = "\"Hi\"";
                        stringId = "Exercise15";
                        fullAnswer = "String, \"Hi\"";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Complete the statement to define a variable named greetings of type String and assign it the value \"Hi\".");
                        quizzes_fragment.setText("______ greetings = ____;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "text";
                        answer2 = "length()";
                        stringId = "Exercise16";
                        fullAnswer = "text,length()";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the appropriate method to print the length of the string variable text.");
                        quizzes_fragment.setText("String text = \"Hi\";\n" +
                                "System.out.println(____.________);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "text";
                        answer2 = "toUpperCase()";
                        stringId = "Exercise17";
                        fullAnswer = "text,toUpperCase()";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Transform the value of the variable text to uppercase.");
                        quizzes_fragment.setText("String text = \"Hello\";\n" +
                                "System.out.println(____._____________);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "+";
                        stringId = "Exercise18";
                        fullAnswer = "+";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Employ the appropriate operator to concatenate two strings together.");
                        quizzes_fragment.setText("String firstName = \"Felix \";\n" +
                                "String lastName = \"Basse\";\n" +
                                "System.out.println(firstName _ lastName);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 4:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "concat";
                        stringId = "Exercise19";
                        fullAnswer = "concat";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the appropriate method to concatenate two strings together.");
                        quizzes_fragment.setText("String firstName = \"Felix \";\n" +
                                "String lastName = \"Basse\";\n" +
                                "System.out.println(firstName.______(lastName));");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 5:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "indexOf";
                        answer2 = "\"o\"";
                        stringId = "Exercise20";
                        fullAnswer = "indexOf,\"o\"";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Provide the index (position) of the first occurrence of \"o\" within the given string.");
                        quizzes_fragment.setText("String text = \"Hello Everyone\";\n" +
                                "System.out.println(text._______(___));");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 4, Default");
                        break;
                }
                break;
            case 5:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "max";
                        stringId = "Exercise21";
                        fullAnswer = "max";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the appropriate method to determine the highest value between x and y.");
                        quizzes_fragment.setText("int x = 19;\n" +
                                "int y = 16;\n" +
                                "Math.___(x, y);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "sqrt";
                        stringId = "Exercise22";
                        fullAnswer = "sqrt";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the appropriate method to calculate the square root of x.");
                        quizzes_fragment.setText("int x = 25;\n" +
                                "Math.____(x);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "random()";
                        stringId = "Exercise23";
                        fullAnswer = "random()";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the appropriate method to generate a random number within the range of 0 (inclusive) and 1 (exclusive).");
                        quizzes_fragment.setText("Math.________;");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 5, Default");
                        break;
                }
                break;
            case 6:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "boolean";
                        answer2 = "boolean";
                        stringId = "Exercise24";
                        fullAnswer = "boolean,boolean";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Complete the statement to print the values true and false.");
                        quizzes_fragment.setText("_______ isJavaFun = true;\n" +
                                "_______ isVeggyTasty= false;\n" +
                                "System.out.println(isJavaFun);\n" +
                                "System.out.println(isVeggyTasty);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "x";
                        answer2 = ">";
                        answer3 = "y";
                        stringId = "Exercise25";
                        fullAnswer = "x,>,y";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Complete the statement to print the value true.");
                        quizzes_fragment.setText("int x = 14;\n" +
                                "int y = 5;\n" +
                                "System.out.println(_ _ _);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 6, Default");
                        break;
                }
                break;
            case 7:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "if";
                        answer2 = ">";
                        stringId = "Exercise26";
                        fullAnswer = "if,>";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Output \"Hello World\" if x is greater than y.");
                        quizzes_fragment.setText("int x = 75;\n" +
                                "int y = 45;\n" +
                                "__ (x _ y) {\n" +
                                "  System.out.println(\"Hello World\");\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "if";
                        answer2 = "==";
                        stringId = "Exercise27";
                        fullAnswer = "if,==";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Output \"Hello World\" if x is equal to y.");
                        quizzes_fragment.setText("int x = 100;\n" +
                                "int y = 100;\n" +
                                "__ (x __ y) {\n" +
                                "  System.out.println(\"Hello World\");\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "if";
                        answer2 = "==";
                        answer3 = "else";
                        stringId = "Exercise28";
                        fullAnswer = "if,==,else";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Display \"Yes\" if x equals y; if not, print \"No\".");
                        quizzes_fragment.setText("int x = 50;\n" +
                                "int y = 50;\n" +
                                "__ (x __ y) {\n" +
                                "  System.out.println(\"Yes\");\n" +
                                "} ____ {\n" +
                                "  System.out.println(\"No\");\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "==";
                        answer2 = "else if";
                        answer3 = "else";
                        stringId = "Exercise29";
                        fullAnswer = "==,else if,else";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("\n" +
                                "Output \"1\" if x equals y, \"2\" if x is greater than y, otherwise print \"3\".");
                        quizzes_fragment.setText("int x = 50;\n" +
                                "int y = 50;\n" +
                                "if (x __ y) {\n" +
                                "  System.out.println(\"1\");\n" +
                                "} _______ (x > y) {\n" +
                                "  System.out.println(\"2\");\n" +
                                "} ____ {\n" +
                                "  System.out.println(\"3\");\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 4:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = ")";
                        answer2 = "?";
                        answer3 = ":";
                        stringId = "Exercise30";
                        fullAnswer = "),?,:";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Fill in the gaps to complete the following \"short-hand if...else statement\" (ternary operator):");
                        quizzes_fragment.setText("int time = 20;\n" +
                                "String result = (time < 18_ _ \"Good day.\" _ \"Good evening.\";\n" +
                                "System.out.println(result); ");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 7, Default");
                        break;
                }
                break;
            case 8:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "day";
                        answer2 = "case";
                        answer3 = "break";
                        stringId = "Exercise31";
                        fullAnswer = "day,case,break";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Fill in the gaps to complete the following switch statement.");
                        quizzes_fragment.setText("int day = 2;\n" +
                                "switch (___) {\n" +
                                "    ____ 1:\n" +
                                "        System.out.println(\"Monday\");\n" +
                                "        break;\n" +
                                "    case 2:\n" +
                                "        System.out.println(\"Sunday\");\n" +
                                "        _____;\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "day";
                        answer2 = "break";
                        answer3 = "default";
                        stringId = "Exercise32";
                        fullAnswer = "day,break,default";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Complete the switch statement and append the correct keyword at the end to specify " +
                                "code to execute if no case matches the switch statement.");
                        quizzes_fragment.setText("int day = 4;\n" +
                                "switch (___) {\n" +
                                "    case 1:\n" +
                                "        System.out.println(\"Saturday\");\n" +
                                "        break;\n" +
                                "    case 2:\n" +
                                "        System.out.println(\"Sunday\");\n" +
                                "        _____;\n" +
                                "    _______:\n" +
                                "        System.out.println(\"Weekends\");\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 8, Default");
                        break;
                }
                break;
            case 9:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "while";
                        answer2 = "i++";
                        stringId = "Exercise33";
                        fullAnswer = "while,i++";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Continuously display the value of i as long as it remains below 6.");
                        quizzes_fragment.setText("int i = 1;\n" +
                                "_____ (i < 6) {\n" +
                                "  System.out.println(i);\n" +
                                "  ___;\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "do";
                        answer2 = "i++";
                        answer3 = "while";
                        stringId = "Exercise34";
                        fullAnswer = "do,i++,while";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Utilize the do/while loop to print the value of i while it is less than 6.");
                        quizzes_fragment.setText("int i = 1;\n" +
                                "__ {\n" +
                                "  System.out.println(i);\n" +
                                "  ___;\n" +
                                "} \n" +
                                "_____ (i < 6);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "for";
                        answer2 = "i++";
                        answer3 = "\"Yes\"";
                        stringId = "Exercise35";
                        fullAnswer = "for,i++,\"Yes\"";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Employ a for loop to print the word \"Yes\" five times.");
                        quizzes_fragment.setText("___ (int i = 0; i < 5; ___) {\n" +
                                "  System.out.println(_____);\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "for";
                        answer2 = "cars";
                        answer3 = "i";
                        stringId = "Exercise36";
                        fullAnswer = "for,cars,i";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Iterate through the items in the cars array using a loop.");
                        quizzes_fragment.setText("String[] cars = {\"Magma\", \"Honda\", \"BMW\"};\n" +
                                "___ (String i : ____) {\n" +
                                "  System.out.println(_);\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 4:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "for";
                        answer2 = "break";
                        answer3 = "i";
                        stringId = "Exercise37";
                        fullAnswer = "for,break,i";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Terminate the loop if the value of i reaches 5.");
                        quizzes_fragment.setText("___ (int i = 0; i < 10; i++) {\n" +
                                "  if (i == 5) {\n" +
                                "    _____;\n" +
                                "  }\n" +
                                "  System.out.println(_);\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 5:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "if";
                        answer2 = "continue";
                        stringId = "Exercise38";
                        fullAnswer = "if,continue";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("In the loop, skip directly to the next value when the current value is \"4\".");
                        quizzes_fragment.setText("for (int i = 0; i < 10; i++) {\n" +
                                "  __ (i == 4) {\n" +
                                "    ________;\n" +
                                "  }\n" +
                                "  System.out.println(i);\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 9, Default");
                        break;
                }
                break;
            case 10:
                switch (submoduleIndex) {
                    case 0:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "String[]";
                        answer2 = "cars";
                        stringId = "Exercise39";
                        fullAnswer = "String[],cars";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Declare an array named cars of type String.");
                        quizzes_fragment.setText("________ ____ = {\"Magma\", \"BMW\", \"Honda\"};");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 1:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "String[]";
                        answer2 = "cars[1]";
                        stringId = "Exercise40";
                        fullAnswer = "String[],cars[1]";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Output the second item in the cars array.");
                        quizzes_fragment.setText("________ cars = {\"Magma\", \"BMW\", \"Honda\"};\n" +
                                "System.out.println(_______);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 2:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "cars[0]";
                        answer2 = "\"Lava\"";
                        stringId = "Exercise41";
                        fullAnswer = "cars[0],\"Lava\"";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Replace the value \"Magma\" with \"Lava\" in the cars array.");
                        quizzes_fragment.setText("String[] cars = {\"Magma\", \"BMW\", \"Honda\"};\n" +
                                "_______ = ______;\n" +
                                "System.out.println(cars[0]);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 3:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "cars.length";
                        stringId = "Exercise42";
                        fullAnswer = "cars.length";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Determine the number of elements in the cars array.");
                        quizzes_fragment.setText("String[] cars = {\"Magma\", \"BMW\", \"Honda\"};\n" +
                                "System.out.println(___________);");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 4:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "for";
                        answer2 = "cars";
                        answer3 = "i";
                        stringId = "Exercise43";
                        fullAnswer = "for,cars,i";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Iterate through the items in the cars array using a loop.");
                        quizzes_fragment.setText("String[] cars = {\"Magma\", \"BMW\", \"Honda\"};\n" +
                                "___ (String i : ____) {\n" +
                                "  System.out.println(_);\n" +
                                "}");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    case 5:
                        documentReference = database.collection("UserScores").document(currentUserEmail);
                        answer1 = "int[][]";
                        stringId = "Exercise44";
                        fullAnswer = "int[][]";

                        quizzes_fragmentTitle.setText("Exercise:");
                        quizzes_instruction.setText("Fill in the gaps to create a two-dimensional array.");
                        quizzes_fragment.setText("_______ theNumbers = { {1, 2, 3, 4}, {5, 6, 7} };");
                        submitAnswers.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onClickListener();
                            }
                        });
                        listenForExerciseCompletion(stringId, completeQuiz);
                        break;
                    default:
                        quizzes_fragment.setText("Module 10, Default");
                        break;
                }
                break;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void submitAnswersForExercise(String exerciseId, int score, TextView completeQuiz) {
        documentReference.collection("Score").document(exerciseId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Boolean isDone = document.getBoolean("isDone");
                                if (isDone != null && isDone) {
                                    Log.d("UserData", "Document already marked as done");
                                    EditTextAnswers.setText("");
                                    return;
                                }
                            }
                            Map<String, Object> scoreData = new HashMap<>();
                            scoreData.put("score", score);
                            scoreData.put("isDone", true);
                            documentReference.collection("Score").document(exerciseId).set(scoreData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("UserData", "DocumentSnapshot added with ID: " + exerciseId);
                                            EditTextAnswers.setText("");
                                            completeQuiz.setText("Completed");
                                            completeQuiz.setTextColor(Color.parseColor("#F89253"));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("UserData", "Error adding document", e);
                                        }
                                    });
                        } else {
                            Log.e("UserData", "Error getting document", task.getException());
                        }
                    }
                });
    }
    private void listenForExerciseCompletion(String exerciseId, TextView completeQuiz) {
        documentReference.collection("Score").document(exerciseId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot document,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e("UserData", "Listen failed.", e);
                            return;
                        }

                        if (document != null && document.exists()) {
                            Boolean isDone = document.getBoolean("isDone");
                            if (isDone != null && isDone) {
                                completeQuiz.setText("Completed");
                                completeQuiz.setTextColor(Color.parseColor("#F89253"));
                                Toast.makeText(quizzes_fragment.this, "You have answered this exercise.", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("UserData", "isDone field does not exist or its value is false");
                            }
                        } else {
                            Log.d("UserData", "No such document");
                        }
                    }
                });
    }
    public void onClickListener() {
        userAnswer = EditTextAnswers.getText().toString();
        if (!userAnswer.isEmpty()) {
            String[] answers = userAnswer.split(",");
            int correctAnswer = 0;

            String answerNumber1 = "", answerNumber2 = "", answerNumber3 = "";

            if (answers.length > 0) {
                answerNumber1 = answers[0].trim();
            }
            if (answers.length > 1) {
                answerNumber2 = answers[1].trim();
            }
            if (answers.length > 2) {
                answerNumber3 = answers[2].trim();
            }

            if (answerNumber1.equals(answer1)) {
                correctAnswer++;
            }
            if (answerNumber2.equals(answer2)) {
                correctAnswer++;
            }
            if (answerNumber3.equals(answer3)) {
                correctAnswer++;
            }
            quizzes_fragment.this.correctAnwers.setText(fullAnswer);
            score += correctAnswer;
            submitAnswersForExercise(stringId, score, completeQuiz);
        }
    }
}