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
public class FamilyFragment extends Fragment {

    AudioManager mAudioManager;
    MediaPlayer mediaPlayer;

    public FamilyFragment() {
        // Required empty public constructor
    }

    AudioManager.OnAudioFocusChangeListener mAudioFocusListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_LOSS:
                            releaseMediaPlayer();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            mediaPlayer.pause();
                            break;

                        case AudioManager.AUDIOFOCUS_GAIN:
                            mediaPlayer.start();
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

        CustomWordAdapter customWordAdapter = new CustomWordAdapter(getActivity(), family, R.color.category_family);

        listView.setAdapter(customWordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                CustomWord customWord = family.get(position);
                Log.v("FamilyActivity", "Current Word: " + customWord);

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

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
