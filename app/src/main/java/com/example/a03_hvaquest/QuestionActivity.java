package com.example.a03_hvaquest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;


public class QuestionActivity extends AppCompatActivity {

    int questionNumber = 1;
    String[] items;
    TextView questionTextview;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    String correctAnswer;
    Button answerButton;
    RadioGroup radioGroup;
    public static final String questionKey = "questionKey";


    private int[] imageNames = {R.drawable.nicolaes_tulphuis, R.drawable.fraijlemaborg, R.drawable.leeuwenburg,
            R.drawable.muller_lulofshuis, R.drawable.wibauthuis, R.drawable.studio_hva, R.drawable.theo_thijssenhuis,
            R.drawable.kohnstammhuis, R.drawable.benno_premselahuis, R.drawable.koetsier_montaignehuis, R.drawable.maagdenhuis};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionTextview = findViewById(R.id.textView);
        answer1 = findViewById(R.id.radioButton1);
        answer2 = findViewById(R.id.radioButton2);
        answer3= findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);
        answerButton = findViewById(R.id.button2);


        populateQuestion(questionNumber);


        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1)
                    Snackbar.make(view, getApplicationContext().getString(R.string.choose_answer), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                else {
                    RadioButton answer = (RadioButton) findViewById(selectedId);
                    if (answer.getText().equals(correctAnswer)) {

                        Intent intent = new Intent(QuestionActivity.this, LocationActivity.class);
                        intent.putExtra(questionKey, questionNumber);
                        startActivityForResult(intent,1234);

                    } else {
                        Snackbar.make(view, getApplicationContext().getString(R.string.try_again), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }
        });


    }

    public void populateQuestion(int questionNumber){

        //   populate question
        String question = "question" + questionNumber;
        int holderint1 = getResources().getIdentifier(question, "string",
                this.getPackageName()); // You had used "name"
        String questionScreen = getResources().getString(holderint1);

        questionTextview.setText(questionScreen);

        // populate answer
        String answer = "answer" + questionNumber;
        int holderint = getResources().getIdentifier(answer, "array",
                this.getPackageName()); // You had used "name"
        items = getResources().getStringArray(holderint);
        answer1.setText(items[1]);
        answer2.setText(items[2]);
        answer3.setText(items[3]);
        correctAnswer = items[0];
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            //Check if the request code is correct
            if (requestCode == 1234) {
                questionNumber = data.getIntExtra(LocationActivity.clueKey,-1);
                populateQuestion(questionNumber);
                radioGroup.clearCheck();


            }
        }

    }

}
