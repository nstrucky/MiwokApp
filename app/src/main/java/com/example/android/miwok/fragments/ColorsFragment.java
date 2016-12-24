package com.example.android.miwok.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.customsupport.CustomWord;
import com.example.android.miwok.customsupport.CustomWordAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    ArrayList<CustomWord> colors;
    AudioManager mAudioManager;
    MediaPlayer mediaPlayer;

    public ColorsFragment() {
        // Required empty public constructor
    }

    AudioManager.OnAudioFocusChangeListener mAudioFocusListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    switch (focusChange) {

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            mediaPlayer.pause();
                            //pause audio
                            break;

                        case AudioManager.AUDIOFOCUS_GAIN:
                            //resume playing
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                            releaseMediaPlayer();
                            break;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentRootView = inflater.inflate(R.layout.fragment_word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ListView listView = (ListView) fragmentRootView.findViewById(R.id.list_in_fragment);

        colors = new ArrayList<>();
        colors.add(new CustomWord("Weṭeṭṭi", "Red", R.drawable.color_red, R.raw.color_red));
        colors.add(new CustomWord("Chokokki", "Green", R.drawable.color_green, R.raw.color_green));
        colors.add(new CustomWord("Takaakki", "Brown", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new CustomWord("Topoppi", "Gray", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new CustomWord("kululli", "black", R.drawable.color_black, R.raw.color_black));
        colors.add(new CustomWord("kelelli", "white", R.drawable.color_white, R.raw.color_white));
        colors.add(new CustomWord("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new CustomWord("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(getActivity(), colors, R.color.category_colors);
        listView.setAdapter(customWordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                CustomWord customWord = colors.get(position);
                Log.v("ColorsActivity", "Current Word: " + customWord);

                int result = mAudioManager.requestAudioFocus(mAudioFocusListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), customWord.getAudioResourceID());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }
            }
        });

        return fragmentRootView;
    }

    @Override
    public void onStop() {
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
