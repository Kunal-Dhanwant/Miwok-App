package com.example.mivok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {



    /** Handles playback of all the sound files */
    MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

//        ArrayList<String> words = new ArrayList<String>();
//        words.add("one");
//        words.add("Two");
//        words.add("Three");
//        words.add("Four");
//        words.add("Five");
//        words.add("Six");
//        words.add("Seven");
//        words.add("Eight");
//        words.add("Nine");
//        words.add("Ten");
//        words.add("Eleven");
//        words.add("Twelve");
//
//        words.add("one");
//        words.add("Two");
//        words.add("Three");
//        words.add("Four");
//        words.add("Five");
//        words.add("Six");
//        words.add("Seven");
//        words.add("Eight");
//        words.add("Nine");
//        words.add("Ten");
//        words.add("Eleven");
//        words.add("Twelve");
//
//        words.add("one");
//        words.add("Two");
//        words.add("Three");
//        words.add("Four");
//        words.add("Five");
//        words.add("Six");
//        words.add("Seven");
//        words.add("Eight");
//        words.add("Nine");
//        words.add("Ten");
//        words.add("Eleven");
//        words.add("Twelve");
//        words.add("one");
//        words.add("Two");
//        words.add("Three");
//        words.add("Four");
//        words.add("Five");
//        words.add("Six");
//        words.add("Seven");
//        words.add("Eight");
//        words.add("Nine");
//        words.add("Ten");
//        words.add("Eleven");
//        words.add("Twelve");

        //   LinearLayout rootView = findViewById(R.id.Rootlist);
        //        int index=0;
//        while(index <words.size()){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            rootView.addView(wordView);
//            index++;
        //       }


//        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
//        ListView listView = findViewById(R.id.list);
//        listView.setAdapter(itemAdapter);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> words = new ArrayList<Word>();

//        words.add(new Word("One","1",R.drawable.number_one));
//        words.add(new Word("Two","2",R.drawable.number_two));
//        words.add(new Word("Three","3",R.drawable.number_three));
//        words.add(new Word("Four","4",R.drawable.number_four));
//        words.add(new Word("Five","5",R.drawable.number_five));
//        words.add(new Word("Six","6",R.drawable.number_six));
//        words.add(new Word("Seven","7",R.drawable.number_seven));
//        words.add(new Word("Eight","8",R.drawable.number_eight));
//        words.add(new Word("Nine","9",R.drawable.number_nine));
//        words.add(new Word("Ten","10",R.drawable.number_ten));

        words.add(new Word("One", "1", R.drawable.number_one, R.raw.one_ek));
        words.add(new Word("Two", "2", R.drawable.number_two, R.raw.two));
        words.add(new Word("Three", "3", R.drawable.number_three, R.raw.teen));
        words.add(new Word("Four", "4", R.drawable.number_four, R.raw.chaar));
        words.add(new Word("Five", "5", R.drawable.number_five, R.raw.paanch));
        words.add(new Word("Six", "6", R.drawable.number_six, R.raw.cheh));
        words.add(new Word("Seven", "7", R.drawable.number_seven, R.raw.saat));
        words.add(new Word("Eight", "8", R.drawable.number_eight, R.raw.saat));
        words.add(new Word("Nine", "9", R.drawable.number_nine, R.raw.no));
        words.add(new Word("Ten", "10", R.drawable.number_ten, R.raw.dus));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.yellows);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listview = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listview.setAdapter(wordAdapter);

        // Set a click listener to play the audio when the list item is clicked on
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // i = position
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(i);


                // Release the media player if it currently exists because we are about to
                // play a different sound file
              releaseMediaPlayer();


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);



                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumberActivity.this, word.getmMediaResourseID());


                    // Start the audio file
                    mMediaPlayer.start();




                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            // Toast.makeText(NumberActivity.this, "complete", Toast.LENGTH_SHORT).show();
                            releaseMediaPlayer();
                        }
                    });



                }


            }
        });




    }

    @Override
    protected void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    }