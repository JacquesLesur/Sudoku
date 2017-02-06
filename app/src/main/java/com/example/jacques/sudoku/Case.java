package com.example.jacques.sudoku;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jacques on 03/02/2017.
 */
public class Case implements Parcelable {
    boolean origine =false;
    boolean selectionner;
    int chiffre;
    public Case(){

        origine=false;
        chiffre=0;
    }
    public Case(boolean origine, int chiffre){
        this.origine=origine;
        this.chiffre=chiffre;
    }

    public Case(boolean origine, int chiffre, boolean selectionner){
        this.origine=origine;
        this.chiffre=chiffre;
        this.selectionner=selectionner;
    }


    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeByte((byte) (origine ? 1 : 0));
        dest.writeByte((byte) (selectionner ? 1 : 0));

        dest.writeInt(chiffre);

    }

    public static final Parcelable.Creator<Case> CREATOR = new Parcelable.Creator<Case>()
    {
        @Override
        public Case createFromParcel(Parcel source)
        {
            return new Case(source);
        }

        @Override
        public Case[] newArray(int size)
        {
            return new Case[size];
        }
    };

    public Case(Parcel in) {
        this.origine =  in.readByte() != 0;
        this.selectionner =  in.readByte() != 0;
        this.chiffre = in.readInt();

    }
}
