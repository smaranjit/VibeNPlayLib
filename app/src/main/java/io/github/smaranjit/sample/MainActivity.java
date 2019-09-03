package io.github.smaranjit.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.smaranjit.vibenplaylib.VibeNPlay;

public class MainActivity extends AppCompatActivity {

    Button mStartButton, mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton = findViewById(R.id.buttonStart);
        mStopButton = findViewById(R.id.buttonStop);

        VibeNPlay.with(this).sound(true).vibration(true).initialize();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VibeNPlay.start();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VibeNPlay.stop();
            }
        });
    }
}
