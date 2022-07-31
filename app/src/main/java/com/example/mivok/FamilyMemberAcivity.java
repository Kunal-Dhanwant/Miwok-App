package com.example.mivok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMemberAcivity extends AppCompatActivity {


    MediaPlayer mMediaplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member_acivity);


        ArrayList<Word> family_member = new ArrayList<Word>();


        family_member.add(new Word("Father","पिता / बापू / पापा / अब्बा ",R.drawable.family_father,R.raw.father));
        family_member.add(new Word("Mother","माँ / माता / अम्मा",R.drawable.family_mother,R.raw.mother));
        family_member.add(new Word("Son","बेटा",R.drawable.family_son,R.raw.son));
        family_member.add(new Word("Daughter"," ेटी",R.drawable.family_daughter,R.raw.daughter));
        family_member.add(new Word("GrandFather","दादा",R.drawable.family_grandfather,R.raw.dada_jii));
        family_member.add(new Word("GrandMother","दादी",R.drawable.family_grandmother,R.raw.grandmother));
        family_member.add(new Word("Uncle","चाचा",R.drawable.family_older_brother,R.raw.chacha));
        family_member.add(new Word("Aunt","चाची / ताई",R.drawable.family_older_sister,R.raw.aunty));
        family_member.add(new Word("Elder Brother","भैया / बड़ा भाई ",R.drawable.family_younger_brother,R.raw.elderbro));
        family_member.add(new Word("Younger Brother","छोटा भाई",R.drawable.family_older_brother,R.raw.chota_bhai));
        family_member.add(new Word("Mother In-Law","सास / सासू माँ ",R.drawable.family_grandmother,R.raw.saas));
        family_member.add(new Word("Fther In-Law","ससुर",R.drawable.family_grandfather,R.raw.sasur));
        family_member.add(new Word("Sister","बहन",R.drawable.family_younger_sister,R.raw.bhen));
        family_member.add(new Word("Nephew","भतीजा / भतीजी",R.drawable.family_daughter,R.raw.nephew));


        WordAdapter memberAdapter = new WordAdapter(this,family_member,R.color.green);

        ListView member_list = findViewById(R.id.member_list);
        member_list.setAdapter(memberAdapter);

        member_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // i = position;
                Word word = family_member.get(i);

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                mMediaplayer = MediaPlayer.create(FamilyMemberAcivity.this,word.getmMediaResourseID());
                mMediaplayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        // Toast.makeText(NumberActivity.this, "complete", Toast.LENGTH_SHORT).show();
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