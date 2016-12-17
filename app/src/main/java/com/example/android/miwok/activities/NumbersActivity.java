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
import com.example.android.miwok.customsupport.CustomNumbers;
import com.example.android.miwok.customsupport.CustomNumbersAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ListView listView = (ListView) findViewById(R.id.list);
        // Create a list of words
        ArrayList<CustomNumbers> numbers = new ArrayList<CustomNumbers>();
        numbers.add(new CustomNumbers("Lutti", "One", R.drawable.number_one));
        numbers.add(new CustomNumbers("Otiiko", "Two", R.drawable.number_two));
        numbers.add(new CustomNumbers("Tolookosu", "Three", R.drawable.number_three));
        numbers.add(new CustomNumbers("Oyyisa", "Four", R.drawable.number_four));
        numbers.add(new CustomNumbers("Massokka", "Five", R.drawable.number_five));
        numbers.add(new CustomNumbers("Temmokka", "Six", R.drawable.number_six));
        numbers.add(new CustomNumbers("Kenekaku", "Seven", R.drawable.number_seven));
        numbers.add(new CustomNumbers("Kawinta", "Eight", R.drawable.number_eight));
        numbers.add(new CustomNumbers("Wo'e", "Nine", R.drawable.number_nine));
        numbers.add(new CustomNumbers("Na'aacha", "Ten", R.drawable.number_ten));


        CustomNumbersAdapter customNumbersAdapter = new CustomNumbersAdapter(this, R.layout.list_item_numbers, numbers);

        listView.setAdapter(customNumbersAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ""+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
