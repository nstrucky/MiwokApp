package com.example.android.miwok.customsupport;



public class CustomWord {

    private final String mMiwokTranslation;
    private final String mEnglishTranslation;
    private int mImageResourceID;

    /**
     * Created by root on 12/14/16.
     *
     * @param mMiwokTranslation is the Miwok version of the word.
     *
     */
    public CustomWord(String miwokTranslation, String englishTranslation) {
        mMiwokTranslation = miwokTranslation;
        mEnglishTranslation = englishTranslation;

    }

    public CustomWord(String miwokTranslation, String englishTranslation, int numberImageID) {
        this(miwokTranslation, englishTranslation);
        mImageResourceID = numberImageID;
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
}
