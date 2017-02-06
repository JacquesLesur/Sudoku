package com.example.jacques.sudoku;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class vGrille implements Parcelable{
    //un tableau double entrée aurrait été mieux mais super galère à sérialiser :/
    List<Case> cases = new ArrayList<>();

    int num, level, done;
    public vGrille(int num,int level,int done){
        this.num=num;
        this.level=level;
        this.done=done;

    }
    @Override
    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeTypedList( cases);
        dest.writeInt(num);
        dest.writeInt(level);
        dest.writeInt(done);
    }

    public static final Parcelable.Creator<vGrille> CREATOR = new Parcelable.Creator<vGrille>()
    {
        @Override
        public vGrille createFromParcel(Parcel source)
        {
            return new vGrille(source);
        }

        @Override
        public vGrille[] newArray(int size)
        {
            return new vGrille[size];
        }
    };

    public vGrille(Parcel in) {
        cases = new ArrayList<>();
        in.readTypedList(cases, Case.CREATOR);
        this.num = in.readInt();
        this.level = in.readInt();
        this.done = in.readInt();
    }



    public void addCase(Case newCase) {
        cases.add(newCase);
    }


    /*public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        int itemPosition = position;

    }*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);
    }*/
}
