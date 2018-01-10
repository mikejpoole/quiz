package com.example.android.quiz;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.lang.Double;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // DEFINE INTEGERS
    private Integer iQuestion = 1;
    private Integer iScore = 0;
    private Double dScorePercentage;


    private String sResult;

    // DEFINE TEXTVIEWS
    private TextView questionNumber;
    private TextView question;
    private View questionWrapper;
    private TextView scoreSoFar;


    // DEFINE RADIOGROUP AND RADIOBUTTONS
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;


    // DEFINE CHECKBOXES
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;


    // DEFINE TEXTFIELD
    private EditText textfield;


    // DEFINE SUBMIT BUTTON
    private Button nextButton;


    // DEFINE IMAGEVIEW
    private ImageView questionImage;



    // DEFINE ARRAY
    String[][] myArray = new String[12][8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionNumber = findViewById(R.id.questionNumber);
        question = findViewById(R.id.question);
        questionWrapper = findViewById(R.id.questionWrapper);

        scoreSoFar = findViewById(R.id.scoreSoFar);

        scoreSoFar.setVisibility(View.GONE);

        radioGroup =  findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);

        textfield = findViewById(R.id.textfield);

        nextButton = findViewById(R.id.nextButton);

        questionImage = findViewById(R.id.questionImage);

        fillArray();
        setQuestion();
    }



    // SAVE STATE FOR SCREEN ROTATION
    // From https://discussions.udacity.com/t/how-to-save-activity-state-on-screen-rotation/468626
    private static final String KEY_QUESTION = "SavedStateOfQuestion";
    private static final String KEY_SCORE = "SavedStateOfScore";

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iQuestion = savedInstanceState.getInt(KEY_QUESTION);
        iScore = savedInstanceState.getInt(KEY_SCORE);

//        Toast.makeText(getApplicationContext(), "You are on question " + iQuestion,Toast.LENGTH_SHORT).show();

        setQuestion();  // SET UP THE QUESTION AGAIN
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(KEY_QUESTION, iQuestion);
        savedInstanceState.putInt(KEY_SCORE, iScore);

        super.onSaveInstanceState(savedInstanceState);
    }





    public void fillArray(){
        myArray[0][0] = "";     // QUESTION
        myArray[0][1] = "";     // OPTION 1
        myArray[0][2] = "";     // OPTION 2
        myArray[0][3] = "";     // OPTION 3
        myArray[0][4] = "";     // OPTION 4
        myArray[0][5] = "";     // IMAGE
        myArray[0][6] = "";     // CORRECT ANSWER
        myArray[0][7] = "";     // QUESTION TYPE

//        Toast.makeText(getApplicationContext(), myArray[0][0],Toast.LENGTH_SHORT).show();

        // QUESTION 1
        myArray[1][0] = "Which side of this buoy should you pass?";
        myArray[1][1] = "North";
        myArray[1][2] = "South";
        myArray[1][3] = "East";
        myArray[1][4] = "West";
        myArray[1][5] = "ic_cardinal_buoy_south";
        myArray[1][6] = "South";
        myArray[1][7] = "radio";

        // QUESTION 2
        myArray[2][0] = "This mark is a...";
        myArray[2][1] = "Lateral";
        myArray[2][2] = "Cardinal";
        myArray[2][3] = "Special";
        myArray[2][4] = "Beacon";
        myArray[2][5] = "ic_cardinal_beacon_north";
        myArray[2][6] = "";
        myArray[2][7] = "check";

        // QUESTION 3
        myArray[3][0] = "This buoy means:";
        myArray[3][1] = "Isolated danger";
        myArray[3][2] = "Safe water";
        myArray[3][3] = "New danger";
        myArray[3][4] = "Diving operations";
        myArray[3][5] = "ic_isolateddangerbuoy";
        myArray[3][6] = "Isolated danger";
        myArray[3][7] = "radio";

        // QUESTION 4
        myArray[4][0] = "Which side of this buoy is there danger on?";
        myArray[4][1] = "Port";
        myArray[4][2] = "Starboard";
        myArray[4][3] = "East";
        myArray[4][4] = "West";
        myArray[4][5] = "ic_cardinal_buoy_west";
        myArray[4][6] = "East";
        myArray[4][7] = "radio";

        // QUESTION 5
        myArray[5][0] = "Which side of your boat should this buoy be on?";
        myArray[5][1] = "Port when leaving harbour";
        myArray[5][2] = "Starboard when leaving harbour";
        myArray[5][3] = "Starboard when entering harbour";
        myArray[5][4] = "Port when entering harbour";
        myArray[5][5] = "ic_lateral_greenconebuoy";
        myArray[5][6] = "";
        myArray[5][7] = "check";

        // QUESTION 6
        myArray[6][0] = "Is this a lateral or a cardinal mark?";
//        myArray[6][1] = "";
//        myArray[6][2] = "";
//        myArray[6][3] = "";
//        myArray[6][4] = "";
        myArray[6][5] = "ic_cardinal_buoy_north";
        myArray[6][6] = "cardinal";
        myArray[6][7] = "text";

        // QUESTION 7
        myArray[7][0] = "What does this buoy mean?";
        myArray[7][1] = "Dredging in operation";
        myArray[7][2] = "It is a leading light";
        myArray[7][3] = "Diving operations";
        myArray[7][4] = "There is a new danger";
        myArray[7][5] = "ic_newdangerbuoy";
        myArray[7][6] = "There is a new danger";
        myArray[7][7] = "radio";

        // QUESTION 8
        myArray[8][0] = "Which side of this buoy should you sail on?";
        myArray[8][1] = "North";
        myArray[8][2] = "South";
        myArray[8][3] = "East";
        myArray[8][4] = "West";
        myArray[8][5] = "ic_safewaterbuoy";
        myArray[8][6] = "";
        myArray[8][7] = "check";

        // QUESTION 9
        myArray[9][0] = "When might you see this buoy?";
        myArray[9][1] = "On a yacht race course";
        myArray[9][2] = "Around a fish farm";
        myArray[9][3] = "Outside the houses of parliament";
        myArray[9][4] = "None of the above";
        myArray[9][5] = "ic_specialmarkbuoy";
        myArray[9][6] = "";
        myArray[9][7] = "check";

        // QUESTION 10
        myArray[10][0] = "Which country would you see this buoy in?";
        myArray[10][1] = "United States of America";
        myArray[10][2] = "United Kingdom after it leaves the EU";
        myArray[10][3] = "Switzerland";
        myArray[10][4] = "Brazil";
        myArray[10][5] = "ic_ialaa_grg_redcanbeacon";
        myArray[10][6] = "";
        myArray[10][7] = "check";
    }


    public void radioButton(View view) {
        scoreSoFar.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
    }

    public void checkbox(View view) {
        scoreSoFar.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
    }



    public void nextButton(View view){
        checkAnswer();
        updateScore();

        if (iQuestion == 10){
            iQuestion++;
            finishQuiz();
        } else {
            iQuestion++;
            setQuestion();
        }
    }



    public void checkAnswer(){
        if (myArray[iQuestion][7] == "radio") {
            // CHECK RADIO GROUP
            RadioGroup radioGroup;
            RadioButton radioButton;
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            sResult = radioButton.getText().toString();

            if (myArray[iQuestion][6] == sResult){
                iScore++;
                Toast.makeText(getApplicationContext(), "Correct! The answer is " + myArray[iQuestion][6],Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Wrong. The correct answer was " + myArray[iQuestion][6],Toast.LENGTH_SHORT).show();
            }

//            Toast.makeText(getApplicationContext(), "You chose " + sResult,Toast.LENGTH_SHORT).show();
        }


        if (myArray[iQuestion][7] == "check") {
            // CHECK CHECKBOXES
            if (iQuestion == 2) {
                if (!checkbox1.isChecked() && checkbox2.isChecked() && !checkbox3.isChecked() && checkbox4.isChecked()) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, it is a cardinal beacon.", Toast.LENGTH_SHORT).show();
                }
            }

            if (iQuestion == 5) {
                if (checkbox1.isChecked() && !checkbox2.isChecked() && checkbox3.isChecked() && !checkbox4.isChecked()) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, it should be to starboard when coming in and to port when leaving.", Toast.LENGTH_SHORT).show();
                }
            }

            if (iQuestion == 8) {
                if (checkbox1.isChecked() && checkbox2.isChecked() && checkbox3.isChecked() && checkbox4.isChecked()) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, you can sail on any side of a safewater mark.", Toast.LENGTH_SHORT).show();
                }
            }

            if (iQuestion == 9) {
                if (checkbox1.isChecked() && checkbox2.isChecked() && checkbox3.isChecked() && !checkbox4.isChecked()) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, you could see a special mark in all those places.", Toast.LENGTH_SHORT).show();
                }
            }

            if (iQuestion == 10) {
                if (checkbox1.isChecked() && !checkbox2.isChecked() && !checkbox3.isChecked() && checkbox4.isChecked()) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, North America and South America use IALA-B lateral marks.", Toast.LENGTH_SHORT).show();
                }
            }
        }


        if (myArray[iQuestion][7] == "text") {
            // CHECK TEXT FIELDS
            if (iQuestion == 6) {
                EditText editTextField = (EditText) findViewById(R.id.textfield);
                String sTextField = editTextField.getText().toString();

                if (sTextField.equals("cardinal")) {
                    iScore++;
                    Toast.makeText(getApplicationContext(), "Correct, it is a cardinal mark.", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }



    public void updateScore(){
        if (iScore > 0) {
            double s = Double.parseDouble(iScore.toString());
            double q = Double.parseDouble(iQuestion.toString());
            dScorePercentage = (s/q) * 100;
        } else {
            dScorePercentage = 0.00;
        }

        scoreSoFar.setText("You have scored " + String.format("%.0f", dScorePercentage) + "% so far");

        scoreSoFar.setVisibility(View.VISIBLE);
    }



    public void setQuestion(){
        nextButton.setVisibility(View.GONE);

        questionNumber.setText("Question " + iQuestion.toString());
        question.setText(myArray[iQuestion][0]);

        // SET RADIO BUTTONS
        if (myArray[iQuestion][7] == "radio") {
            radioGroup.clearCheck();                        // CAUSES A RIPPLE

            radioButton1.setVisibility(View.VISIBLE);
            radioButton2.setVisibility(View.VISIBLE);
            radioButton3.setVisibility(View.VISIBLE);
            radioButton4.setVisibility(View.VISIBLE);
            radioButton1.setText(myArray[iQuestion][1]);
            radioButton2.setText(myArray[iQuestion][2]);
            radioButton3.setText(myArray[iQuestion][3]);
            radioButton4.setText(myArray[iQuestion][4]);
        } else {
            radioButton1.setVisibility(View.GONE);
            radioButton2.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);
            radioButton4.setVisibility(View.GONE);
        }


        // SET CHECK BOXES
        if (myArray[iQuestion][7] == "check") {
            checkbox1.setChecked(false);
            checkbox2.setChecked(false);
            checkbox3.setChecked(false);
            checkbox4.setChecked(false);
            checkbox1.setVisibility(View.VISIBLE);
            checkbox2.setVisibility(View.VISIBLE);
            checkbox3.setVisibility(View.VISIBLE);
            checkbox4.setVisibility(View.VISIBLE);
            checkbox1.setText(myArray[iQuestion][1]);
            checkbox2.setText(myArray[iQuestion][2]);
            checkbox3.setText(myArray[iQuestion][3]);
            checkbox4.setText(myArray[iQuestion][4]);
        } else {
            checkbox1.setVisibility(View.GONE);
            checkbox2.setVisibility(View.GONE);
            checkbox3.setVisibility(View.GONE);
            checkbox4.setVisibility(View.GONE);
        }


        // SET TEXT FIELD
        if (myArray[iQuestion][7] == "text") {
            textfield.setText("");
            textfield.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);     // Note that the submit button is visible for the text field
        } else {
            textfield.setVisibility(View.GONE);
        }

        // SET IMAGE
        String resName = getPackageName() + ":drawable/" +  myArray[iQuestion][5];
        int id = getResources().getIdentifier(resName, null, null);
        questionImage.setImageResource(id);
    }


    public void finishQuiz(){
        question.setVisibility(View.GONE);
        questionImage.setVisibility(View.GONE);
        questionWrapper.setVisibility(View.GONE);

        checkbox1.setVisibility(View.GONE);
        checkbox2.setVisibility(View.GONE);
        checkbox3.setVisibility(View.GONE);
        checkbox4.setVisibility(View.GONE);

        nextButton.setVisibility(View.GONE);
        scoreSoFar.setVisibility(View.GONE);

        questionNumber.setPadding(0,100,0,100);
        questionNumber.setTextSize(64);
        questionNumber.setText("You have Finished.\n\nYou scored\n" + iScore + " out of 10");

//        Toast.makeText(getApplicationContext(), "Congratulations you scored " + iScore + " out of 10",Toast.LENGTH_SHORT).show();

        // RESET APP FOR NEXT TIME
        iQuestion = 1;
        iScore = 0;
    }

}
