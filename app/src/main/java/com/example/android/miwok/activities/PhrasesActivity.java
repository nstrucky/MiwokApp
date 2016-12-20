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

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWordAdapter;
import com.example.android.miwok.customsupport.CustomWord;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ListView listView = (ListView) findViewById(R.id.list_phrases);

        final ArrayList<CustomWord> phrases = new ArrayList<>();
        phrases.add(new CustomWord("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        phrases.add(new CustomWord("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        phrases.add(new CustomWord("oyaaset...", "My name is...", R.raw.phrase_my_name_is));
        phrases.add(new CustomWord("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new CustomWord("kuchi achit", "I'm feeling good.", R.raw.phrase_im_feeling_good));
        phrases.add(new CustomWord("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        phrases.add(new CustomWord("hәә’ әәnәm", "Yes, I’m coming.", R.raw.phrase_yes_im_coming));
        phrases.add(new CustomWord("әәnәm", "I’m coming.", R.raw.phrase_im_coming));
        phrases.add(new CustomWord("yoowutis", "Let’s go.", R.raw.phrase_lets_go));
        phrases.add(new CustomWord("әnni'nem", "Come here.", R.raw.phrase_come_here));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, phrases, R.color.category_phrases);
        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                CustomWord customWord = phrases.get(position);
                Log.v("PhrasesActivity", "Current Word: " + customWord);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), customWord.getAudioResourceID());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
