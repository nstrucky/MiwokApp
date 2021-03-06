package com.example.android.miwok.customsupport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

/**
 * Created by root on 12/14/16.
 */

public class CustomWordAdapter extends ArrayAdapter {

    private int mColorResourceID;

    public CustomWordAdapter(Context context, List list, int colorResourceID) {
        super(context, 0, list);
        mColorResourceID = colorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        CustomWord currentWord = (CustomWord) getItem(position);

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

        FrameLayout frameLayout = (FrameLayout) listItemView.findViewById(R.id.framelayout_list_item);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        frameLayout.setBackgroundColor(color);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView_listItem);

        if (!currentWord.hasImageView()) {
            imageView.setImageResource(R.drawable.family_mother);
            imageView.setVisibility(ImageView.GONE);

        } else {
            imageView.setVisibility(ImageView.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceID());
        }

        TextView textView1 = (TextView) listItemView.findViewById(R.id.textView_1_numbers);
        TextView textView2 = (TextView) listItemView.findViewById(R.id.textView_2_numbers);

        textView1.setText(currentWord.getmMiwokTranslation());
        textView2.setText(currentWord.getEnglishTranslation());


        return listItemView;
    }
}
