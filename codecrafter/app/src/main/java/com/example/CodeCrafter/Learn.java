package com.example.CodeCrafter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Learn extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        expandableListView = findViewById(R.id.elistView);

        // BELOW THIS COMMENT
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Introduction", Arrays.asList("What is Java?", "Why use Java?"), R.drawable.topicicon1));
        groups.add(new Group("Java Syntax", Arrays.asList("Java Syntax", "The main Method", "System.out.println()"), R.drawable.topicicon2));
        groups.add(new Group("Java Output/Print", Arrays.asList("Print Text", "Double Quotes", "The Print() Method", "Print Numbers"), R.drawable.topicicon3));
        groups.add(new Group("Java Comments", Arrays.asList("Java Comments", "Single-line Comments", "Java Multi-line Comments"), R.drawable.topicicon4));
        groups.add(new Group("Java Variables", Arrays.asList("Java Variables", "Declaring (Creating) Variables", "Final Variables",
                "Other Types", "Print/Display Variables", "Declare Multiple Variables", "Identifiers"), R.drawable.topicicon5));
        groups.add(new Group("Java Data Types", Arrays.asList("Java Data Types", "Primitive Data Types", "Java Numbers",
                "Boolean Types", "Java Characters", "Non-Primitive Data Types"), R.drawable.topicicon6));
        groups.add(new Group("Java Type Casting", Arrays.asList("Java Type Casting", "Widening Casting", "Narrowing Casting"), R.drawable.topicicon7));
        groups.add(new Group("Java Operators", Arrays.asList("Java Operators", "Arithmetic Operators", "Java Assignment Operators",
                "Java Comparison", "Java Logical Operators"), R.drawable.topicicon8));
        groups.add(new Group("Java Strings", Arrays.asList("Java Strings", "String Length", "More String Methods",
                "Finding a Character in a String", "Concatenation", "Numbers and Strings", "Special Characters"), R.drawable.topicicon9));
        groups.add(new Group("Java Math", Arrays.asList("Math.max(x,y)", "Math.min(x,y)", "Math.sqrt(x)",
                "Math.abs(x)", "Random Numbers"), R.drawable.topicicon10));
        groups.add(new Group("Java Booleans", Arrays.asList("Java Booleans", "Boolean Values", "Boolean Expression",
                "Real Life Examples"), R.drawable.topicicon11));
        groups.add(new Group("Java If...Else", Arrays.asList("Java Condition and If Statements", "The if Statements", "The else Statements",
                "The else if Statements", "Java Short Hand if...else"), R.drawable.topicicon12));
        groups.add(new Group("Java Switch", Arrays.asList("Java Switch Statements", "The break Keyword", "The default Keyword"), R.drawable.topicicon13));
        groups.add(new Group("Java While Loop", Arrays.asList("Loops", "Java while Loop", "The do/while Loop"), R.drawable.topicicon14));
        groups.add(new Group("Java for Loop", Arrays.asList("Java for Loop", "Another Example", "Nested Loops", "for each loop"), R.drawable.topicicon15));
        groups.add(new Group("Java break/continue", Arrays.asList("Java break", "Java continue", "break and continue in while Loop"), R.drawable.topicicon16));
        groups.add(new Group("Java Arrays", Arrays.asList("Java Arrays", "Access the Elements of an Array", "Change an Array Element",
                "Array Length", "Loop through an Array", "Multidimensional Arrays"), R.drawable.topicicon17));
        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(this, groups);
        expandableListView.setAdapter(adapter);
        // ABOVE THIS COMMENT

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                onSubmoduleClicked(groupPosition, childPosition);
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.learn);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.library) {
                    startActivity(new Intent(getApplicationContext(), Library.class));
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
    // BELOW THIS COMMENT
    public void onSubmoduleClicked(int moduleIndex, int submoduleIndex) {
        Log.e("moduleClicked", moduleIndex + " " + submoduleIndex);
        try {
            Intent intent = new Intent(this, learn_fragment.class);
            intent.putExtra("moduleId", moduleIndex);
            intent.putExtra("submoduleIndex", submoduleIndex);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("moduleClicked", Objects.requireNonNull(e.getMessage()));
        }
    }
    // ABOVE THIS COMMENT
}