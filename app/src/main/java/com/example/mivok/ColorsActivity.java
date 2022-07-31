package com.example.mivok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mMediaplyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> color_list = new ArrayList<Word>();


        color_list.add(new Word("White","सफेद",R.drawable.color_white,R.raw.white));
        color_list.add(new Word("Black","काला",R.drawable.color_black,R.raw.kaala));

        color_list.add(new Word("Yellow","पीला",R.drawable.color_mustard_yellow,R.raw.peela));
        color_list.add(new Word("Red","लाल",R.drawable.color_red,R.raw.red));

        color_list.add(new Word("Brown","भूरा",R.drawable.color_brown,R.raw.bhoora));

        color_list.add(new Word("Grey","ग्रे (धुमैला) ",R.drawable.color_gray,R.raw.grey));
        color_list.add(new Word("Orange","नारंगी",R.drawable.color_white,R.raw.narangi));



        WordAdapter colorAdapter = new WordAdapter(this,color_list,R.color.purple);

        ListView color_listView = findViewById(R.id.color_list);
        color_listView.setAdapter(colorAdapter);

        color_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Word word = color_list.get(i);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                mMediaplyer = MediaPlayer.create(ColorsActivity.this,word.getmMediaResourseID());

                mMediaplyer.start();
            }
        });
        // Setup a listener on the media player, so that we can stop and release the
        // media player once the sound has finished playing.
        mMediaplyer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Toast.makeText(NumberActivity.this, "complete", Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaplyer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaplyer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaplyer = null;
        }
    }
}