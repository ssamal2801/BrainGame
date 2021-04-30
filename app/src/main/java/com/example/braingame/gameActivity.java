package com.example.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class gameActivity extends AppCompatActivity {

    TextView timerView,queryView,answerView,gameOver;
    int correctIndex;
    int questionNumber = 0;
    int score =0;

    Vibrator vibe;

     MediaPlayer winSound;
     MediaPlayer wrongSound;
     MediaPlayer tickSound;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button exit;

    public void evaluate(View view)
    {
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer wrongSound = MediaPlayer.create(this,R.raw.wrong);

        if(view.getTag().toString().equals(Integer.toString(correctIndex)))
        {
            Log.i("Awesome!", ";)");
            score++;
        }

        else {
            vibe.vibrate(200);
            wrongSound.start();
        }

        questionNumber++;
        answerView.setText(score+"/"+questionNumber);
        newQuestion();

    }

    public void gameRestart(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void newQuestion()
    {

        Random random = new Random();

        int a,b;
        ArrayList<Integer> answers = new ArrayList<Integer>();

        a = random.nextInt(22);
        b = random.nextInt(29);

        queryView.setText(a+" + "+b+" = ?");

        correctIndex = random.nextInt(4);
        answers.clear();

        for(int j=0; j<4; j++)
        {
            if(j==correctIndex)
            {
                answers.add(a+b);
            }
            else answers.add(random.nextInt(54));
        }

        b1.setText(String.valueOf(answers.get(0)));
        b2.setText(String.valueOf(answers.get(1)));
        b3.setText(String.valueOf(answers.get(2)));
        b4.setText(String.valueOf(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//----------------------DECLARATIONS----------------------
        timerView = findViewById(R.id.timerView);
        queryView = findViewById(R.id.queryView);
        answerView = findViewById(R.id.ansView);
        gameOver = findViewById(R.id.gameOverView);

         b1 = findViewById(R.id.button1);
         b2 = findViewById(R.id.button2);
         b3 = findViewById(R.id.button3);
         b4 = findViewById(R.id.button4);
         exit = findViewById(R.id.exit);

        final MediaPlayer winSound = MediaPlayer.create(this,R.raw.end);
        final MediaPlayer tickSound = MediaPlayer.create(this,R.raw.tick);

        newQuestion();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {

                timerView.setText(""+(l/1000));
                tickSound.start();

            }

            @Override
            public void onFinish() {
                tickSound.stop();
                winSound.start();

                b1.setAlpha((float) 0.25);
                b2.setAlpha((float) 0.25);
                b3.setAlpha((float) 0.25);
                b4.setAlpha((float) 0.25);
                exit.setAlpha(1);
                gameOver.setAlpha(1);
                answerView.setAlpha((float) 0.50);
                queryView.setAlpha((float) 0.25);
                timerView.setAlpha((float) 0.25);
            }
        }.start();

    }


}