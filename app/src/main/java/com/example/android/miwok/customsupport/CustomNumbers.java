package com.example.android.miwok.customsupport;

/**
 * Created by root on 12/14/16.
 */

public class CustomNumbers {

    private String mMiwokTranslation;
    private String mEnglishTranslation;
    private int mNumberImageID;

    public CustomNumbers(String miwokTranslation, String englishTranslation, int numberImageID) {
        mMiwokTranslation = miwokTranslation;
        mEnglishTranslation = englishTranslation;
        mNumberImageID = numberImageID;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getEnglishTranslation() {
        return mEnglishTranslation;
    }

    public int getNumberImageID() {
        return mNumberImageID;
    }
}
