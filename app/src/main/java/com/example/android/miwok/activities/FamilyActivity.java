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

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        ListView listView = (ListView) findViewById(R.id.list_family);

        final ArrayList<CustomWord> family = new ArrayList<>();
        family.add(new CustomWord("әpә", "Father", R.drawable.family_father, R.raw.family_father));
        family.add(new CustomWord("әṭa", "mother", R.drawable.family_mother, R.raw.family_mother));
        family.add(new CustomWord("angsi", "son", R.drawable.family_son, R.raw.family_son));
        family.add(new CustomWord("tune", "daughter", R.drawable.family_daughter, R.raw.family_daughter));
        family.add(new CustomWord("taachi", "older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        family.add(new CustomWord("chalitti", "younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        family.add(new CustomWord("teṭe", "older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        family.add(new CustomWord("kolliti", "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        family.add(new CustomWord("ama", "grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        family.add(new CustomWord("paapa", "grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, family, R.color.category_family);

        listView.setAdapter(customWordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                CustomWord customWord = family.get(position);
                Log.v("FamilyActivity", "Current Word: " + customWord);
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
