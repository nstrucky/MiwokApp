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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWord;
import com.example.android.miwok.customsupport.CustomWordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ListView listView = (ListView) findViewById(R.id.list);
        // Create a list of words
        ArrayList<CustomWord> numbers = new ArrayList<CustomWord>();
        numbers.add(new CustomWord("Lutti", "One", R.drawable.number_one));
        numbers.add(new CustomWord("Otiiko", "Two", R.drawable.number_two));
        numbers.add(new CustomWord("Tolookosu", "Three", R.drawable.number_three));
        numbers.add(new CustomWord("Oyyisa", "Four", R.drawable.number_four));
        numbers.add(new CustomWord("Massokka", "Five", R.drawable.number_five));
        numbers.add(new CustomWord("Temmokka", "Six", R.drawable.number_six));
        numbers.add(new CustomWord("Kenekaku", "Seven", R.drawable.number_seven));
        numbers.add(new CustomWord("Kawinta", "Eight", R.drawable.number_eight));
        numbers.add(new CustomWord("Wo'e", "Nine", R.drawable.number_nine));
        numbers.add(new CustomWord("Na'aacha", "Ten", R.drawable.number_ten));


        CustomWordAdapter customWordAdapter = new CustomWordAdapter(this, numbers, R.color.category_numbers);

        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ""+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
