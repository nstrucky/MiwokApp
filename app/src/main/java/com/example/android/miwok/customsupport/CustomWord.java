package com.example.android.miwok.customsupport;



public class CustomWord {

    private final String mMiwokTranslation;
    private final String mEnglishTranslation;
    private int mImageResourceID;
    private boolean mHasImageView;
    private int mAudioResourceID;
    /**
     * Created by root on 12/14/16.
     *
     * @param  is the Miwok version of the word.
     *
     */
    public CustomWord(String miwokTranslation, String englishTranslation, int audioResourceID) {
        mMiwokTranslation = miwokTranslation;
        mEnglishTranslation = englishTranslation;
        mAudioResourceID = audioResourceID;
        mHasImageView = false;

    }

    public CustomWord(String miwokTranslation, String englishTranslation, int numberImageID, int audioResourceID) {
        this(miwokTranslation, englishTranslation, audioResourceID);
        mImageResourceID = numberImageID;
        mHasImageView = true;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getEnglishTranslation() {
        return mEnglishTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public boolean hasImageView() {
        return mHasImageView;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }
}
