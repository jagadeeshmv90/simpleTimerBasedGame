package com.j7ech.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button playAgainButton;
    TextView questionTextView;
    TextView feedBackTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int totalQns = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void resetScore(){
        score = 0;
        totalQns = 0;
        scoreTextView.setText("0/0");
    }


    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        timerStart();
        generateAnswer();
    }

    public void generateAnswer() {
        Random rand = new Random();
        int num1 = rand.nextInt(20);
        int num2 = rand.nextInt(20);

        questionTextView.setText(num1 + " + " + num2);

        int rightAnswer = num1 + num2;

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for(int i=0; i<4; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(rightAnswer);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == rightAnswer){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void findAnswer(View view) {

//        Log.i("just clicked ", view.getTag().toString());
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
//            Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show();
            feedBackTextView.setText("You're Correct!");
            score++;
        } else {
//            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            feedBackTextView.setText("You're Wrong!");
        }
        totalQns++;
        scoreTextView.setText(score + "/" + totalQns);
        generateAnswer();
    }

    public void timerStart(){
        CountDownTimer myCountDownTimer = new CountDownTimer(10100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + " s");

            }

            @Override
            public void onFinish() {
                feedBackTextView.setText("Times Up!");
                playAgainButton.setVisibility(View.VISIBLE);
                disabler();

            }
        }.start();
    }



    public void disabler(){
        button0.setClickable(false);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
    }

    public void enabler() {
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }

    public void playAgain(View view){
        enabler();
        resetScore();
        timerStart();
        playAgainButton.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        feedBackTextView = findViewById(R.id.feedbackTextView);
        questionTextView = findViewById(R.id.qnTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameLayout = findViewById(R.id.gameLayout);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button0 = findViewById(R.id.button0);




    }
}