package com.example.aurora;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Musicplay1 extends AppCompatActivity {


    //variable initialization
    TextView playerPosition, playerDuration, audioName;
    SeekBar seekBar;
    ImageView btRew, btPlay, btPause, btFf;

    MediaPlayer mediaPlayer1;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplay);

        //Assign variable
        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btPlay = findViewById(R.id.bt_play);
        btPause = findViewById(R.id.bt_pause);
        btFf = findViewById(R.id.bt_ff);
        audioName = findViewById(R.id.audio_name);


        //Initialize Media Player
        mediaPlayer1 = MediaPlayer.create(this, R.raw.music1);
        File file = new File("music1.mp3");
        String strFileName = file.getName();
        audioName.setText(strFileName);
        audioName.setVisibility(View.VISIBLE);

        //Initialize runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                //Set progress on seekbar
                seekBar.setProgress(mediaPlayer1.getCurrentPosition());

                //handler post delay for 0.5 sec
                handler.postDelayed(this, 500);

            }
        };

        //Get duration of mediaplayer
        int duration = mediaPlayer1.getDuration();
        //Convert ms to min and sec
        String sDuration = convertFormat(duration);
        //Set duration on text view
        playerDuration.setText(sDuration);


        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide play button
                btPlay.setVisibility(View.GONE);
                //Show pause button
                btPause.setVisibility(View.VISIBLE);
                //Start Media player
                mediaPlayer1.start();
                //Set max on seek bar
                seekBar.setMax(mediaPlayer1.getDuration());
                //Start handler
                handler.postDelayed(runnable,0);
            }
        });
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide pause button
                btPause.setVisibility(View.GONE);
                //Show play button
                btPlay.setVisibility(View.VISIBLE);
                //Pause media player
                mediaPlayer1.pause();
                //Stop handler
                handler.removeCallbacks(runnable);
            }
        });
        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get current position of media player
                int currentPosition = mediaPlayer1.getCurrentPosition();
                //get duration of media player
                int duration = mediaPlayer1.getDuration();
                //Check condition
                if(mediaPlayer1.isPlaying() && duration != currentPosition){
                    //When media is playing and duration is not equal to current position
                    //Fast forward for 5 seconds
                    currentPosition += 5000;
                    //Set current position on textview
                    playerPosition.setText(convertFormat(currentPosition));
                    //Set progress on seekbar
                    mediaPlayer1.seekTo(currentPosition);
                }
            }
        });

        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get current position of mediaplayer
                int currentPosition = mediaPlayer1.getCurrentPosition();
                //Check condition
                if(mediaPlayer1.isPlaying() && currentPosition > 5000){
                    //When media is playing and current position is greater than 5 seconds
                    //Rewind for 5 seconds
                    currentPosition -= 5000;
                    //Get current position on text view
                    playerPosition.setText(convertFormat(currentPosition));
                    //Set progress on seekbar
                    mediaPlayer1.seekTo(currentPosition);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //check condition
                if(fromUser){
                    //when drag the seekbar
                    //Set progress on seekbar
                    mediaPlayer1.seekTo(progress);
                }
                //Set current position on text view
                playerPosition.setText(convertFormat(mediaPlayer1.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Hide pause button
                btPause.setVisibility(View.GONE);
                //Show play button
                btPlay.setVisibility(View.VISIBLE);
                //Set media player to initial position
                mediaPlayer1.seekTo(0);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration){
        return  String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }



}