package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    long duration = 10000;
    int minute = 0;
    int second = 0;

    SeekBar seekBar;
    CountDownTimer timer;

//    TextView minuteText = (TextView) findViewById(R.id.minuteText);
//    TextView secondText = (TextView) findViewById(R.id.secondText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Timer
        TextView minuteText = (TextView) findViewById(R.id.minuteText);
        TextView secondText = (TextView) findViewById(R.id.secondText);

        // 1000 millisecond = 1 second
        // 60000 millisecond = 1 minute
//        CountDownTimer countDownTimer = new CountDownTimer(duration, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//                second = (int) millisUntilFinished / 1000;
//                minute = (int) millisUntilFinished / 60000;
//
//                secondText.setText(Integer.toString(second));
//                minuteText.setText(Integer.toString(minute));
//
//            }
//
//            @Override
//            public void onFinish() {
//
//                //play a sound
//
//            }
//        };

        //Seek bar set up
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setMax(360000);
        seekBar.setProgress(5000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                duration = (long) progress;

                second = (int) (duration/1000)%60;
                minute = (int) duration / 60000;
                if (second < 10)
                    secondText.setText("0" + Integer.toString(second));
                else
                    secondText.setText(Integer.toString(second));

                if (minute < 10)
                    minuteText.setText("0" + Integer.toString(minute));
                else
                    minuteText.setText(Integer.toString(minute));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timer = new CountDownTimer(duration, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        seekBar.setProgress((int) millisUntilFinished);

                        second = (int) (millisUntilFinished/1000)%60;
                        minute = (int) millisUntilFinished / 60000;
                        if (second < 10)
                            secondText.setText("0" + Integer.toString(second));
                        else
                            secondText.setText(Integer.toString(second));

                        if (minute < 10)
                            minuteText.setText("0" + Integer.toString(minute));
                        else
                            minuteText.setText(Integer.toString(minute));
                    }

                    @Override
                    public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep_sound);

                    mediaPlayer.start();
                    }
                }.start();

                seekBar.setEnabled(false);

            }
        });

    }
    //reset Button
    public void resetFunc (View view) {
        seekBar.setEnabled(true);
        seekBar.setProgress(0);
        timer.cancel();

        TextView minuteText = (TextView) findViewById(R.id.minuteText);
        TextView secondText = (TextView) findViewById(R.id.secondText);
        secondText.setText("00");
        minuteText.setText("00");
    }
    //Timer

}