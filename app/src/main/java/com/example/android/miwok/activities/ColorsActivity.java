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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWordAdapter;
import com.example.android.miwok.customsupport.CustomWord;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ListView listView = (ListView) findViewById(R.id.list_colors);

        ArrayList<CustomWord> colors = new ArrayList<>();
        colors.add(new CustomWord("Weṭeṭṭi", "Red", R.drawable.color_red));
        colors.add(new CustomWord("Chokokki", "Green", R.drawable.color_green));
        colors.add(new CustomWord("Takaakki", "Brown", R.drawable.color_brown));
        colors.add(new CustomWord("Topoppi", "Gray", R.drawable.color_gray));


        colors.add(new CustomWord("Black", "black", R.drawable.color_black));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, colors);

        listView.setAdapter(customWordAdapter);



    }
}
