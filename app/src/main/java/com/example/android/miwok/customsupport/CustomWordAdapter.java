package com.example.android.miwok.customsupport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.activities.ColorsActivity;
import com.example.android.miwok.activities.FamilyActivity;
import com.example.android.miwok.activities.NumbersActivity;

import java.util.List;

/**
 * Created by root on 12/14/16.
 */

public class CustomWordAdapter extends ArrayAdapter {

    public CustomWordAdapter(Context context, List list) {
        super(context, 0, list);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        CustomWord currentWord = (CustomWord) getItem(position);

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView_listItem);

        if (!currentWord.hasImageView()) {
            imageView.setImageResource(R.drawable.family_mother);
            imageView.setVisibility(ImageView.GONE);
//            LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.linearlayout_textviews);
//            LinearLayout linearLayout1 = (LinearLayout) listItemView.findViewById(R.id.linearlayout_parent);
//            ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
//            params.width = linearLayout1.getLayoutParams().width;

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
