/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWord;
import com.example.android.miwok.customsupport.CustomWordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    protected AudioManager mAudioManager;
    protected MediaPlayer mediaPlayer;

    AudioManager.OnAudioFocusChangeListener mAudioFocusListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    if (mediaPlayer != null) {
                        switch (focusChange) {

                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                mediaPlayer.pause();
                                mediaPlayer.seekTo(0);
                                break;

                            case AudioManager.AUDIOFOCUS_GAIN:
                                mediaPlayer.start();
                                break;

                            case AudioManager.AUDIOFOCUS_LOSS:
                                releaseMediaPlayer();
                                break;
                        }
                    }
                }
            };

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager) NumbersActivity.this.getSystemService(Context.AUDIO_SERVICE);

        ListView listView = (ListView) findViewById(R.id.list);
        // Create a list of words
        final ArrayList<CustomWord> numbers = new ArrayList<>();
        numbers.add(new CustomWord("Lutti", "One", R.drawable.number_one, R.raw.number_one));
        numbers.add(new CustomWord("Otiiko", "Two", R.drawable.number_two, R.raw.number_two));
        numbers.add(new CustomWord("Tolookosu", "Three", R.drawable.number_three, R.raw.number_three));
        numbers.add(new CustomWord("Oyyisa", "Four", R.drawable.number_four, R.raw.number_four));
        numbers.add(new CustomWord("Massokka", "Five", R.drawable.number_five, R.raw.number_five));
        numbers.add(new CustomWord("Temmokka", "Six", R.drawable.number_six, R.raw.number_six));
        numbers.add(new CustomWord("Kenekaku", "Seven", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new CustomWord("Kawinta", "Eight", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new CustomWord("Wo'e", "Nine", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new CustomWord("Na'aacha", "Ten", R.drawable.number_ten, R.raw.number_ten));


        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, numbers, R.color.category_numbers);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                CustomWord customWord = numbers.get(position);
                Log.v("NumbersActivity", "Current Word: " + customWord);

                int result = mAudioManager.requestAudioFocus(mAudioFocusListener,
                                                AudioManager.STREAM_MUSIC,
                                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), customWord.getAudioResourceID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                } else {
                    Toast.makeText(NumbersActivity.this, "Could not find audio.", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }
}
