package com.example.CodeCrafter;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class learn_fragment extends AppCompatActivity {
    ImageButton backToLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_fragment);

        TextView learn_fragment = findViewById(R.id.learn_fragment_textView);
        TextView learn_fragmentTitle = findViewById(R.id.titleLearnFragment);
//      BELOW THIS COMMENT
        LinearLayout imageContainer = findViewById(R.id.imageContainer);
//      ABOVE THIS COMMENT
        int moduleIndex = getIntent().getIntExtra("moduleId", -1);
        int submoduleIndex = getIntent().getIntExtra("submoduleIndex", -1);
        switch (moduleIndex) {
            case 0:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("What is Java?");
                        learn_fragment.setText(R.string.what_is_java);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Why use Java?");
                        learn_fragment.setText(R.string.why_use_java);
                        break;
                    default:
                        learn_fragment.setText("Module 0, Default");
                        break;
                }
                break;
            case 1:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Syntax");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_2_1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20,-3630,20,0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setPadding(0,400,0,0);
                        learn_fragment.setText(R.string.java_syntax);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("The main Method");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_2_2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -5840, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.the_main_method);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("System.out.println()");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_2_3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -3680, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.system_outprinln);
                        break;
                    default:
                        learn_fragment.setText("Module 1, Default");
                        break;
                }
                break;
            case 2:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Print Text");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_3_1_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -4700, 20, -240);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_3_1_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 2810, 20, -4160);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setPadding(0,0,0,0);
                        learn_fragment.setText(R.string.print_text);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Double Quotes");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_3_2_p1);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -1180, 20, 20);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);

//                        IMAGE 2
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_3_2_p2);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -1980, 20, -6500);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.double_quotes);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("The Print() Method");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_3_3);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -1910, 20, 0);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
                        learn_fragment.setText(R.string.the_print_method);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Print Numbers");
//                        IMAGE 1
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_3_4_p1);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, -2090, 20, -300);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
//                        IMAGE 2
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_3_4_p2);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, 200, 20, -730);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
//                        IMAGE 3
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_3_4_p3);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, 0, 20, 0);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
                        learn_fragment.setText(R.string.print_numbers);
                        break;
                    default:
                        learn_fragment.setText("Module 2, Default");
                        break;
                }
                break;
            case 3:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Comments");
                        learn_fragment.setText(R.string.java_comments);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Single-line Comments");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_4_2_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3790, 20, -180);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_4_2_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 400, 20, -2400);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.singleline_comments);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Java Multi-line Comments");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_4_3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -2910, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.java_multiline_comments);
                        break;
                    default:
                        learn_fragment.setText("Module 3, Default");
                        break;
                }
                break;
            case 4:
                switch(submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Variables");
                        learn_fragment.setText(R.string.java_variables);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Declaring (Creating) Variables");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_5_2_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -2940, 20, -530);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_5_2_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 190, 20, -700);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);

//                        IMAGE 3
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_5_2_p3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -20, 20, -530);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);

//                        IMAGE 4
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_5_2_p4);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, 150, 20, -2000);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.declaring_variables);

                        break;
                    case 2:
                        learn_fragmentTitle.setText("Final Variables");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_5_3);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -470, 20, 0);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
                        learn_fragment.setText(R.string.final_variables);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Other Types");
//                        IMAGE 1
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_5_4);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, -570, 20, -500);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.other_types);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Print/Display Variables");
//                        IMAGE 1
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_5_5_p1);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, -2880, 20, -700);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
//                        IMAGE 2
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_5_5_p2);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, 120, 20, -620);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
//                        IMAGE 3
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_example1_5_5_p3);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, 1050, 20, -5000);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
                        learn_fragment.setText(R.string.print_display_variables);
                        break;
                    case 5:
                        learn_fragmentTitle.setText("Declare Multiple Variables");
//                        IMAGE 1
                        ImageView imageView10 = new ImageView(this);
                        imageView10.setImageResource(R.drawable.code_example1_5_6_1_p1);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams10.setMargins(20, -2300, 20, -840);
                        imageView10.setLayoutParams(layoutParams10);
                        imageContainer.addView(imageView10);
//                        IMAGE 2
                        ImageView imageView11 = new ImageView(this);
                        imageView11.setImageResource(R.drawable.code_example1_5_6_1_p2);
                        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams11.setMargins(20, 0, 20, -320);
                        imageView11.setLayoutParams(layoutParams11);
                        imageContainer.addView(imageView11);
//                        IMAGE 3
                        ImageView imageView12 = new ImageView(this);
                        imageView12.setImageResource(R.drawable.code_example1_5_6_2);
                        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams12.setMargins(20, 50, 20, -2000);
                        imageView12.setLayoutParams(layoutParams12);
                        imageContainer.addView(imageView12);
                        learn_fragment.setText(R.string.declare_multiple_variables);
                        break;
                    case 6:
                        learn_fragmentTitle.setText("Identifiers");
//                        IMAGE 1
                        ImageView imageView13 = new ImageView(this);
                        imageView13.setImageResource(R.drawable.code_example1_5_7);
                        LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams13.setMargins(20, -3570, 20, 0);
                        imageView13.setLayoutParams(layoutParams13);
                        imageContainer.addView(imageView13);
                        learn_fragment.setText(R.string.identifiers);
                        break;
                    default:
                        learn_fragment.setText("Module 4, Default");
                        break;
                }
                break;
            case 5:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Data Types");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_6_1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3900, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.java_data_types);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Primitive Data Types");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_table1_6_2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -2310, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.primitive_data_types);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Java Numbers");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_6_3_2_1);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -7420, 20, -330);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
//                        IMAGE 2
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_6_3_2_2);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, 50, 20, -100);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
//                        IMAGE 3
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_6_3_2_3);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, 200, 20, -50);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 4
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_6_3_2_4);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 200, 20, 20);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
//                        IMAGE 5
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_6_3_3_p1);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, 260, 20, -480);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
//                        IMAGE 6
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_6_3_3_p2);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, -160, 20, -110);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
//                        IMAGE 7
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_example1_6_3_3_1);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, 170, 20, -5000);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
                        learn_fragment.setText(R.string.java_numbers);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Boolean Types");
//                        IMAGE 1
                        ImageView imageView10 = new ImageView(this);
                        imageView10.setImageResource(R.drawable.code_example1_6_4);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams10.setMargins(20, -2910, 20, 0);
                        imageView10.setLayoutParams(layoutParams10);
                        imageContainer.addView(imageView10);
                        learn_fragment.setText(R.string.boolean_types);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Java Characters");
//                        IMAGE 1
                        ImageView imageView11 = new ImageView(this);
                        imageView11.setImageResource(R.drawable.code_example1_6_5_1_p1);
                        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams11.setMargins(20, -3720, 20, -600);
                        imageView11.setLayoutParams(layoutParams11);
                        imageContainer.addView(imageView11);
//                        IMAGE 2
                        ImageView imageView12 = new ImageView(this);
                        imageView12.setImageResource(R.drawable.code_example1_6_5_1_p2);
                        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams12.setMargins(20, 40, 20, -280);
                        imageView12.setLayoutParams(layoutParams12);
                        imageContainer.addView(imageView12);
//                        IMAGE 3
                        ImageView imageView13 = new ImageView(this);
                        imageView13.setImageResource(R.drawable.code_example1_6_5_2);
                        LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams13.setMargins(20, 80, 20, -3000);
                        imageView13.setLayoutParams(layoutParams13);
                        imageContainer.addView(imageView13);
                        learn_fragment.setText(R.string.java_characters);
                        break;
                    case 5:
                        learn_fragmentTitle.setText("Non-Primitive Data Types");
                        learn_fragment.setText(R.string.non_primitive_data_types);
                        break;
                    default:
                        learn_fragment.setText("Module 5, Default");
                        break;
                }
                break;
            case 6:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Type Casting");
                        learn_fragment.setText(R.string.java_type_casting);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Widening Casting");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_7_2);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -600, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.widening_casting);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Narrowing Casting");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_7_3);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -580, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.narrowing_casting);
                        break;
                    default:
                        learn_fragment.setText("Module 6, Default");
                        break;
                }
                break;
            case 7:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Operators");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_8_1_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -2490, 20, -560);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_8_1_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 0, 20, -300);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.java_operators);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Arithmetic Operators");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_table1_8_2);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -770, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.arithmethic_operators);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Java Assignment Operators");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_8_3_p1);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -2490, 20, -630);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
//                        IMAGE 2
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_8_3_p2);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, 0, 20, -1080);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 3
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_table1_8_3_p3);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 200, 20, -1000);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.java_assignment_operators);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Java Comparison Operators");
//                        IMAGE 1
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_8_4_p1);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, -470, 20, -1180);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
//                        IMAGE 2
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_table1_8_4_p2);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, 0, 20, 0);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
                        learn_fragment.setText(R.string.java_comparison_operators);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Java Logical Operators");
//                        IMAGE 1
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_table1_8_5);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, -780, 20, -500);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
                        learn_fragment.setText(R.string.java_logical_operators);
                        break;
                    default:
                        learn_fragment.setText("Module 7, Default");
                        break;
                }
                break;
            case 8:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Strings");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_9_1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -460, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.java_strings);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("String Length");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_9_2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -1010, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.string_length);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("More String Methods");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_9_3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -4070, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.more_string_methods);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Finding a Character in a String");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_9_4);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -3380, 20, 0);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.finding_a_charac_in_a_string);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Concatenation");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_9_5_p1);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -4580, 20, 1350);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 2
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_9_5_p2);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 1250, 20, -500);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.concatenation);
                        break;
                    case 5:
                        learn_fragmentTitle.setText("Numbers and Strings");
//                        IMAGE 1
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_9_6_1_p1);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, -1970, 20, -600);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
//                        IMAGE 2
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_9_6_1_p2);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, 300, 20, -560);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
//                        IMAGE 3
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_example1_9_6_1_p3);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, 300, 20, -2000);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
                        learn_fragment.setText(R.string.number_and_strings);
                        break;
                    case 6:
                        learn_fragmentTitle.setText("Special Characters");
//                        IMAGE 1
                        ImageView imageView10 = new ImageView(this);
                        imageView10.setImageResource(R.drawable.code_example1_9_7_1_p1);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams10.setMargins(20, -4900, 20, -760);
                        imageView10.setLayoutParams(layoutParams10);
                        imageContainer.addView(imageView10);
//                        IMAGE 2
                        ImageView imageView11 = new ImageView(this);
                        imageView11.setImageResource(R.drawable.code_table1_9_7_1_p2);
                        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams11.setMargins(20, 71, 20, -810);
                        imageView11.setLayoutParams(layoutParams11);
                        imageContainer.addView(imageView11);
//                        IMAGE 3
                        ImageView imageView12 = new ImageView(this);
                        imageView12.setImageResource(R.drawable.code_example1_9_7_1_p3);
                        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams12.setMargins(20, 0, 20, -370);
                        imageView12.setLayoutParams(layoutParams12);
                        imageContainer.addView(imageView12);
//                        IMAGE 4
                        ImageView imageView13 = new ImageView(this);
                        imageView13.setImageResource(R.drawable.code_example1_9_7_1_p4);
                        LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams13.setMargins(20, 200, 20, -370);
                        imageView13.setLayoutParams(layoutParams13);
                        imageContainer.addView(imageView13);
//                        IMAGE 5
                        ImageView imageView14 = new ImageView(this);
                        imageView14.setImageResource(R.drawable.code_example1_9_7_1_p5);
                        LinearLayout.LayoutParams layoutParams14 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams14.setMargins(20, 250, 20, -870);
                        imageView14.setLayoutParams(layoutParams14);
                        imageContainer.addView(imageView14);
//                        IMAGE 6
                        ImageView imageView15 = new ImageView(this);
                        imageView15.setImageResource(R.drawable.code_table1_9_7_1_p6);
                        LinearLayout.LayoutParams layoutParams15 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams15.setMargins(20, 150, 20, -2000);
                        imageView15.setLayoutParams(layoutParams15);
                        imageContainer.addView(imageView15);
                        learn_fragment.setText(R.string.special_characters);
                        break;
                    default:
                        learn_fragment.setText("Module 8, Default");
                        break;
                }
                break;
            case 9:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Math.max(x,y)");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_10_1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -800, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.math_max_xy);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Math.min(x,y)");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_10_2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -790, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.math_min_xy);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Math.sqrt(x)");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_10_3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -800, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.math_sqrt_x);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Math.abs(x)");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_10_4);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -790, 20, 0);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.math_abs_x);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Random Numbers");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_10_5_p1);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -1460, 20, -420);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 2
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_10_5_p2);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 0, 20, -1000);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.random_numbers);
                        break;
                    default:
                        learn_fragment.setText("Module 9, Default");
                        break;
                }
                break;
            case 10:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Booleans");
                        learn_fragment.setText(R.string.java_booleans);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Boolean Values");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_11_2);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -1350, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.boolean_values);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Boolean Expression");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_11_3_p1);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -1340, 20, -720);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
//                        IMAGE 2
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_11_3_p2);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, 0, 20, -670);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
//                        IMAGE 3
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_11_3_p3);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, 100, 20, -870);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
//                        IMAGE 4
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_11_3_p4);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, 0, 20, -2000);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
                        learn_fragment.setText(R.string.boolean_expression);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Real Life Examples");
//                        IMAGE 1
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_11_4_p1);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, -2940, 20, -800);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
//                        IMAGE 2
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_11_4_p2);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, 300, 20, -2000);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
                        learn_fragment.setText(R.string.real_life_examples);
                        break;
                    default:
                        learn_fragment.setText("Module 10, Default");
                        break;
                }
                break;
            case 11:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Conditions and If Statements");
                        learn_fragment.setText(R.string.java_conditions_and_if_else_statements);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("The if Statement");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_12_2_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3150, 20, -480);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_12_2_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 200, 20, -690);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
//                        IMAGE 3
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_12_2_p3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, 200, 20, -3000);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.the_if_statements);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("The else Statement");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_12_3_p1);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -3770, 20, -810);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
//                        IMAGE 2
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_12_3_p2);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, 300, 20, -3000);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
                        learn_fragment.setText(R.string.the_else_statements);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("The else if Statement");
//                        IMAGE 1
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_12_4_p1);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, -3350, 20, -1390);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
//                        IMAGE 2
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_12_4_p2);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, 0, 20, -2000);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
                        learn_fragment.setText(R.string.the_else_if_statements);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Java Short Hand If…Else");
//                        IMAGE 1
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_12_5_p1);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, -4750, 20, 360);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
//                        IMAGE 2
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_example1_12_5_p2);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, 700, 20, -650);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
//                        IMAGE 3
                        ImageView imageView10 = new ImageView(this);
                        imageView10.setImageResource(R.drawable.code_example1_12_5_p3);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams10.setMargins(20, 300, 20, -5000);
                        imageView10.setLayoutParams(layoutParams10);
                        imageContainer.addView(imageView10);
                        learn_fragment.setText(R.string.java_short_hand_if_else);
                        break;
                    default:
                        learn_fragment.setText("Module 11, Default");
                        break;
                }
                break;
            case 12:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Switch Statement");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_13_1_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3000, 20, -1970);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_13_1_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 600, 20, -4000);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.java_switch_statements);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("The break Keyword");
                        learn_fragment.setText(R.string.the_break_keyword);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("The default Keyword");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_13_3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -1680, 20, -200);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.the_default_keyword);
                        break;
                    default:
                        learn_fragment.setText("Module 12, Default");
                        break;
                }
                break;
            case 13:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Loops");
                        learn_fragment.setText(R.string.loops);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Java While Loop");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_14_2_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3880, 20, -730);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_14_2_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 0, 20, -1200);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.java_while_loop);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("The Do/While Loop");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_14_3_p1);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -3750, 20, -830);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
//                        IMAGE 2
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_14_3_p2);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, 180, 20, -1300);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.the_do_while_loop);
                        break;
                    default:
                        learn_fragment.setText("Module 12, Default");
                        break;
                }
                break;
            case 14:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java For Loop");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_15_1_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -3290, 20, -160);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_15_1_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 250, 20, -1000);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.java_for_loop);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Another Example");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_15_2);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -6100, 20, 0);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.another_example);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Nested Loops");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_15_3);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -2750, 20, 100);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.nested_loops);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("For Each Loop");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_15_4_p1);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -1690, 20, -790);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 2
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_15_4_p2);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 0, 20, -1300);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.for_each_loop);
                        break;
                    default:
                        learn_fragment.setText("Module 13, Default");
                        break;
                }
                break;
            case 15:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Break");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_16_1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -1390, 20, 0);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
                        learn_fragment.setText(R.string.java_break);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Java Continue");
//                        IMAGE 1
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_16_2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, -1800, 20, 0);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
                        learn_fragment.setText(R.string.java_continue);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Break and Continue in While Loop");
//                        IMAGE 1
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_16_3_p1);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, -3110, 20, -805);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
//                        IMAGE 2
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_16_3_p2);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, 250, 20, -1700);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.break_and_continue_in_while_loop);
                        break;
                    default:
                        learn_fragment.setText("Module 15, Default");
                        break;
                }
                break;
            case 16:
                switch (submoduleIndex) {
                    case 0:
                        learn_fragmentTitle.setText("Java Arrays");
//                        IMAGE 1
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setImageResource(R.drawable.code_example1_17_1_p1);
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams1.setMargins(20, -1500, 20, -540);
                        imageView1.setLayoutParams(layoutParams1);
                        imageContainer.addView(imageView1);
//                        IMAGE 2
                        ImageView imageView2 = new ImageView(this);
                        imageView2.setImageResource(R.drawable.code_example1_17_1_p2);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams2.setMargins(20, 100, 20, -740);
                        imageView2.setLayoutParams(layoutParams2);
                        imageContainer.addView(imageView2);
//                        IMAGE 3
                        ImageView imageView3 = new ImageView(this);
                        imageView3.setImageResource(R.drawable.code_example1_17_1_p3);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams3.setMargins(20, 40, 20, -2000);
                        imageView3.setLayoutParams(layoutParams3);
                        imageContainer.addView(imageView3);
                        learn_fragment.setText(R.string.java_arrays);
                        break;
                    case 1:
                        learn_fragmentTitle.setText("Access the Elements of an Array");
//                        IMAGE 1
                        ImageView imageView4 = new ImageView(this);
                        imageView4.setImageResource(R.drawable.code_example1_17_2);
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams4.setMargins(20, -1030, 20, 0);
                        imageView4.setLayoutParams(layoutParams4);
                        imageContainer.addView(imageView4);
                        learn_fragment.setText(R.string.access_the_elements_of_an_array);
                        break;
                    case 2:
                        learn_fragmentTitle.setText("Change an Array Element");
//                        IMAGE 1
                        ImageView imageView5 = new ImageView(this);
                        imageView5.setImageResource(R.drawable.code_example1_17_3_p1);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams5.setMargins(20, -1840, 20, -870);
                        imageView5.setLayoutParams(layoutParams5);
                        imageContainer.addView(imageView5);
//                        IMAGE 2
                        ImageView imageView6 = new ImageView(this);
                        imageView6.setImageResource(R.drawable.code_example1_17_3_p2);
                        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams6.setMargins(20, 0, 20, -1300);
                        imageView6.setLayoutParams(layoutParams6);
                        imageContainer.addView(imageView6);
                        learn_fragment.setText(R.string.change_an_array_element);
                        break;
                    case 3:
                        learn_fragmentTitle.setText("Array Length");
//                        IMAGE 1
                        ImageView imageView7 = new ImageView(this);
                        imageView7.setImageResource(R.drawable.code_example1_17_4);
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams7.setMargins(20, -2200, 20, 0);
                        imageView7.setLayoutParams(layoutParams7);
                        imageContainer.addView(imageView7);
                        learn_fragment.setText(R.string.array_length);
                        break;
                    case 4:
                        learn_fragmentTitle.setText("Loop through an Array");
//                        IMAGE 1
                        ImageView imageView8 = new ImageView(this);
                        imageView8.setImageResource(R.drawable.code_example1_17_5_1);
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams8.setMargins(20, -4130, 20, -250);
                        imageView8.setLayoutParams(layoutParams8);
                        imageContainer.addView(imageView8);
//                        IMAGE 2
                        ImageView imageView9 = new ImageView(this);
                        imageView9.setImageResource(R.drawable.code_example1_17_5_2_p1);
                        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams9.setMargins(20, 250, 20, -810);
                        imageView9.setLayoutParams(layoutParams9);
                        imageContainer.addView(imageView9);
//                        IMAGE 3
                        ImageView imageView10 = new ImageView(this);
                        imageView10.setImageResource(R.drawable.code_example1_17_5_2_p2);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams10.setMargins(20, 170, 20, -2000);
                        imageView10.setLayoutParams(layoutParams10);
                        imageContainer.addView(imageView10);
                        learn_fragment.setText(R.string.loop_through_an_array);
                        break;
                    case 5:
                        learn_fragmentTitle.setText("Multidimensional Arrays");
//                        IMAGE 1
                        ImageView imageView11 = new ImageView(this);
                        imageView11.setImageResource(R.drawable.code_example1_17_6_1);
                        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams11.setMargins(20, -5470, 20, -100);
                        imageView11.setLayoutParams(layoutParams11);
                        imageContainer.addView(imageView11);
//                        IMAGE 2
                        ImageView imageView12 = new ImageView(this);
                        imageView12.setImageResource(R.drawable.code_example1_17_6_2);
                        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams12.setMargins(20, 260, 20, -280);
                        imageView12.setLayoutParams(layoutParams12);
                        imageContainer.addView(imageView12);
//                        IMAGE 3
                        ImageView imageView13 = new ImageView(this);
                        imageView13.setImageResource(R.drawable.code_example1_17_6_3);
                        LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams13.setMargins(20, 250, 20, -780);
                        imageView13.setLayoutParams(layoutParams13);
                        imageContainer.addView(imageView13);
//                        IMAGE 4
                        ImageView imageView14 = new ImageView(this);
                        imageView14.setImageResource(R.drawable.code_example1_17_6_4);
                        LinearLayout.LayoutParams layoutParams14 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        layoutParams14.setMargins(20, 800, 20, -900000);
                        imageView14.setLayoutParams(layoutParams14);
                        imageContainer.addView(imageView14);
                        learn_fragment.setText(R.string.multidimensional_arrays);
                        break;
                    default:
                        learn_fragment.setText("Module 16, Default");
                        break;
                }
        }
        backToLearn = findViewById(R.id.backToLearn);

        backToLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(learn_fragment.this, Learn.class));
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}