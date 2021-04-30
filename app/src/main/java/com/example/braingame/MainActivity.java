package com.example.braingame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button changeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeActivity = findViewById(R.id.sartGame);
        changeActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startGameActivity();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startGameActivity()
    {
        Intent intent = new Intent(this,gameActivity.class);
        startActivity(intent);
    }
}