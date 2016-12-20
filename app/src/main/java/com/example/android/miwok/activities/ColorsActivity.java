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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWordAdapter;
import com.example.android.miwok.customsupport.CustomWord;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    ArrayList<CustomWord> colors;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ListView listView = (ListView) findViewById(R.id.list_colors);

        colors = new ArrayList<>();
        colors.add(new CustomWord("Weṭeṭṭi", "Red", R.drawable.color_red, R.raw.color_red));
        colors.add(new CustomWord("Chokokki", "Green", R.drawable.color_green, R.raw.color_green));
        colors.add(new CustomWord("Takaakki", "Brown", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new CustomWord("Topoppi", "Gray", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new CustomWord("kululli", "black", R.drawable.color_black, R.raw.color_black));
        colors.add(new CustomWord("kelelli", "white", R.drawable.color_white, R.raw.color_white));
        colors.add(new CustomWord("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new CustomWord("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, colors, R.color.category_colors);
        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CustomWord customWord = colors.get(position);

                mediaPlayer = MediaPlayer.create(getApplicationContext(), customWord.getAudioResourceID());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
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
