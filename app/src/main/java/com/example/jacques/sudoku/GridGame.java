package com.example.jacques.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import static android.R.attr.key;
import static android.view.MotionEvent.ACTION_DOWN;

public class GridGame extends View implements View.OnTouchListener{
    private static final String TAG = "Sudoku";
    private float width=50; // width of one tile
    private float height=50; // height of one tile
    private int selX; // X index of selection
    private int selY; // Y index of selection
    private final Rect selRect = new Rect();
    LoadGrid loadGrid;

    public GridGame(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.setOnTouchListener(this);
        loadGrid = (LoadGrid) context;

       // vGrille test = getIntent().getExtras().getParcelable("vGrille");
    }


    @Override
    public void onDraw(Canvas canvas){
        // Draw the background...
        Paint background = new Paint();
        background.setColor(Color.rgb(131, 166, 151));
        background.setStyle(Paint.Style.FILL);
        background.setTextSize(40);
        Paint newCase = new Paint();
        newCase.setColor(Color.GRAY);
        newCase.setTextSize(40);
        Paint oldCase = new Paint();
        oldCase.setColor(Color.BLACK);
        oldCase.setStyle(Paint.Style.STROKE);
        oldCase.setTextSize(40);
        Paint paint = oldCase;



        //création de la grille
        int height = 70;

        //ligne plus épaisse, purement éstétique
        canvas.drawLine( 280 , 70, 280, 700, newCase);
        canvas.drawLine( 490 , 70, 490, 700, newCase);
        canvas.drawLine( 70 , 280, 700, 280, newCase);
        canvas.drawLine( 70 , 490, 700, 490, newCase);
        for(int i = 0; i<9;i++)
        {
            for(int z = 0; z<9;z++){
                int x =height*i+70;
                int y =height*z+70;
                Case caseEnCours = loadGrid.grille.cases.get((i*9)+z);
                int chiffreInt = caseEnCours.chiffre;
                String chiffre = String.valueOf(chiffreInt);

                if (caseEnCours.selectionner == true){
                    //background.setColor(Color.BLUE);
                    paint=background;
                   // background.setColor(Color.GRAY);
                }
                canvas.drawRect( y , x, y+height, x+height, paint);

                if (chiffreInt !=0) {
                    if (caseEnCours.origine) {
                         paint= oldCase;

                    }
                    else
                    {
                        paint= newCase;

                    }
                    canvas.drawText(chiffre, y + 28, x + 50, paint);
                }
                paint = oldCase;


                //canvas.drawRect();
            }
        }

        //création des chiffres à sélectionner
        for(int i=0; i<loadGrid.listChifresSelectionner.size();i++){
            if (loadGrid.listChifresSelectionner.get(i).selectionner) {
                canvas.drawRect((i * height) + 70, 800, (i * height) + 70 + height, 800 + height, oldCase);
                canvas.drawText(String.valueOf(loadGrid.listChifresSelectionner.get(i).chiffre), (i * height) + 70 + 28, 800 + 50, oldCase);
            }
        }

        // Draw the board...
        // Define colors for the grid lines

        Paint hilite = new Paint();
        hilite.setColor(Color.BLUE);
        Paint light = new Paint();
        light.setColor(Color.LTGRAY);
        // Draw the minor grid lines
        /*for (int i = 0; i < 9; i++) {
            //canvas.drawLine(100, i * height, getWidth(), i * height, light);
            canvas.drawLine(100,100+( i * height + 1), getWidth(), i * (height),
                    hilite);
            //canvas.drawLine(i * width+100, 0, i * width, getHeight(), light);
            canvas.drawLine(i * width , 100, i * width + 1, getHeight(),
                    hilite);
        }*/


        /*float test[] = {10,10};
        Canvas cnva = new Canvas();

        canvas.drawLines(test,100,9,light);/*
        // Draw the major grid lines
        /*for (int i = 0; i < 9; i++) {
            if (i % 3 != 0)
                continue;
            canvas.drawLine(0, i * height, getWidth(), i * height, dark);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
                    hilite);
            canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(),
                    hilite);
        }*/

        // Draw the numbers...
        // Define color and style for numbers
        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(Color.RED);
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextSize(height * 0.75f);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);


        // Draw the selection...
        Log.d(TAG, "selRect=" + selRect);
        Paint selected = new Paint();
        selected.setColor(Color.DKGRAY);
        canvas.drawRect(selRect, selected);

        Paint hint = new Paint();
        int c[] = { };
        Rect r = new Rect();


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                int x = ((int)(event.getX()/70))-1;
                int y = ((int)(event.getY()/70))-1;
                loadGrid.caseSelect(x,y);
                this.invalidate();



                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        this.invalidate();

        return true;
    }
}