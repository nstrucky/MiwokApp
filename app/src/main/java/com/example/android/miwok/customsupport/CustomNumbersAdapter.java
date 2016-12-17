package com.example.android.miwok.customsupport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomNumbers;

import java.util.List;

/**
 * Created by root on 12/14/16.
 */

public class CustomNumbersAdapter extends ArrayAdapter {

    public CustomNumbersAdapter(Context context, int layout, List list) {
        super(context, layout, list);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_numbers, parent, false);
        }

        CustomNumbers currentWord = (CustomNumbers) getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView_numbers);
        TextView textView1 = (TextView) listItemView.findViewById(R.id.textView_1_numbers);
        TextView textView2 = (TextView) listItemView.findViewById(R.id.textView_2_numbers);

        imageView.setImageResource(currentWord.getNumberImageID());
        textView1.setText(currentWord.getmMiwokTranslation());
        textView2.setText(currentWord.getEnglishTranslation());

        return listItemView;
    }
}
