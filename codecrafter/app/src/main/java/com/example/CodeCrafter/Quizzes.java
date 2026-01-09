package com.example.CodeCrafter;

import android.content.Intent;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Quizzes extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ExpandableListView expandableListView;

    List<String> chapterList;
    HashMap<String, List<String>> topicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        expandableListView = findViewById(R.id.elistView);

        List<Group> groups = new ArrayList<>();
        groups.add(new Group("Java Syntax", Arrays.asList("Exercise 1", "Exercise 2"), R.drawable.quiz_icon1));
        groups.add(new Group("Java Variables", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4", "Exercise 5"), R.drawable.quiz_icon2));
        groups.add(new Group("Java Data Types", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3"), R.drawable.quiz_icon3));
        groups.add(new Group("Java Operators", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4"), R.drawable.quiz_icon4));
        groups.add(new Group("Java Strings", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4", "Exercise 5", "Example 6"), R.drawable.quiz_icon5));
        groups.add(new Group("Java Math", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3"), R.drawable.quiz_icon6));
        groups.add(new Group("Java Booleans", Arrays.asList("Exercise 1", "Exercise 2"), R.drawable.quiz_icon7));
        groups.add(new Group("Java If...Else", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4", "Exercise 5"), R.drawable.quiz_icon8));
        groups.add(new Group("Java Switch", Arrays.asList("Exercise 1", "Exercise 2"), R.drawable.quiz_icon9));
        groups.add(new Group("Java Loops", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4", "Exercise 5", "Exercise 6"), R.drawable.quiz_icon10));
        groups.add(new Group("Java Arrays", Arrays.asList("Exercise 1", "Exercise 2","Exercise 3", "Exercise 4", "Exercise 5", "Exercise 6"), R.drawable.quiz_icon11));
        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(this, groups);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                onSubmoduleClicked(groupPosition, childPosition);
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigator);
        bottomNavigationView.setSelectedItemId(R.id.quizzes);
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
    public void onSubmoduleClicked(int moduleIndex, int submoduleIndex) {
        Log.e("moduleClicked", moduleIndex + " " + submoduleIndex);
        try {
            Intent intent = new Intent(this, quizzes_fragment.class);
            intent.putExtra("moduleIndex", moduleIndex);
            intent.putExtra("submoduleIndex", submoduleIndex);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("moduleClicked", Objects.requireNonNull(e.getMessage()));
        }
    }
}