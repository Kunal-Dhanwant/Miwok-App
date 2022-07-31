package com.example.mivok;

public class Word {

    private String mdefaultTranslation;
    private String mMivokTranslation;
    private int mimage = HAS_IMAGE;
    private int mMediaResourseID;



    private static final int HAS_IMAGE = -1;

    public Word(String defaulttranslation,String hinditranslation,int mMediaplayer){
        this.mdefaultTranslation = defaulttranslation;
        this.mMivokTranslation = hinditranslation;
        this.mMediaResourseID = mMediaplayer;
    }
    public Word(String defaulttranslation,String hinditranslation,int image,int mediyaplayed){
        this.mdefaultTranslation = defaulttranslation;
        this.mMivokTranslation = hinditranslation;
        this.mimage = image;
        this.mMediaResourseID = mediyaplayed;
    }

    public String getMdefaultTranslation() {
        return mdefaultTranslation;
    }

    public String getmMivokTranslation() {
        return mMivokTranslation;
    }

    public int getMimage() {
        return mimage;
    }

    public boolean hasimage(){
        return mimage != HAS_IMAGE;
    }

    public int getmMediaResourseID() {
        return mMediaResourseID;
    }
}
