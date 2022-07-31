package com.example.mivok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> phrses_list = new ArrayList<Word>();



//        phrses_list.add((new Word("Just Coming ","मैं अभी आ रहा हूँ |")));
//        phrses_list.add((new Word("Fine/Very Good ","बहुत अच्छा |")));
//        phrses_list.add((new Word("Fine/Very Good","अच्छी बात है |")));
//        phrses_list.add((new Word(" As You Like/As You Please","जैसी आपकी मर्जी |")));
//        phrses_list.add((new Word("Anything Else","और कुछ ?")));
//        phrses_list.add((new Word(" That’s enough","बस, रहने दो")));
//        phrses_list.add((new Word("Thanks For This Honour"," इस सम्मान के लिए धन्यबाद |")));
//        phrses_list.add((new Word("O.K","अच्छा |")));
//        phrses_list.add((new Word("Why Not","क्यों नहीं ?")));
//        phrses_list.add((new Word(" Not a Bit","थोडा-सा भी नहीं |")));
//        phrses_list.add((new Word(" Take Care"," ध्यान रखना |")));
//        phrses_list.add((new Word(" See You Tomorrow","कल मिलेंगे |")));
//        phrses_list.add((new Word("Yes, by All Means","हाँ जरूर |")));
//        phrses_list.add((new Word("That IS Too Much","बहुत है |")));
//        phrses_list.add((new Word(" Yes,Sir !","हाँ, सर |")));
//        phrses_list.add((new Word("No, Not At All","नहीं, कभी नहीं |")));
//        phrses_list.add((new Word(" Never Mind/ Does’t Matter","कोई बात नहीं |")));


        phrses_list.add((new Word("Just Coming ","मैं अभी आ रहा हूँ |",R.raw.m_abhi_aa)));
        phrses_list.add((new Word("Fine/Very Good ","बहुत अच्छा |",R.raw.bahut_accha)));
        phrses_list.add((new Word("Fine/Very Good","अच्छी बात है |",R.raw.bahut_accha)));
        phrses_list.add((new Word(" As You Like/As You Please","जैसी आपकी मर्जी |",R.raw.jese_aapki)));
        phrses_list.add((new Word("Anything Else","और कुछ ?",R.raw.or_kuch)));
        phrses_list.add((new Word(" That’s enough","बस, रहने दो",R.raw.rehne_do_yaar)));
        phrses_list.add((new Word("Thanks For This Honour"," इस सम्मान के लिए धन्यबाद |",R.raw.is_samman_k)));
        phrses_list.add((new Word("O.K","अच्छा |",R.raw.accha)));
        phrses_list.add((new Word("Why Not","क्यों नहीं ?",R.raw.kyon_nahi)));

        phrses_list.add((new Word(" Take Care"," ध्यान रखना |",R.raw.dhyan_rakhna)));
        phrses_list.add((new Word(" See You Tomorrow","कल मिलेंगे |",R.raw.kal_milenge)));
        phrses_list.add((new Word("Yes, by All Means","हाँ जरूर |",R.raw.haan_jarur)));
        phrses_list.add((new Word("That IS Too Much","बहुत है |",R.raw.bahut_accha)));
        phrses_list.add((new Word(" Yes,Sir !","हाँ, सर |",R.raw.haan)));
        phrses_list.add((new Word("No, Not At All","नहीं, कभी नहीं |",R.raw.nahi_kabhi_nahi)));
        phrses_list.add((new Word(" Never Mind/ Does’t Matter","कोई बात नहीं |",R.raw.excuses)));





        WordAdapter memberAdapter = new WordAdapter(this,phrses_list,R.color.firozi);

        ListView phrses_listView = findViewById(R.id.list_phrases);
        phrses_listView.setAdapter(memberAdapter);

        phrses_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = phrses_list.get(i);

                releaseMediaPlayer();
                mMediaplayer = MediaPlayer.create(PhrasesActivity.this,word.getmMediaResourseID());
                mMediaplayer.start();

                mMediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        releaseMediaPlayer();
                    }
                });


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
        if (mMediaplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaplayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaplayer = null;
        }
    }
}