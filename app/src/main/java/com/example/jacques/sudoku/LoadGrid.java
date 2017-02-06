package com.example.jacques.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LoadGrid extends AppCompatActivity {

    vGrille grille = null;
    Case tabCase[][] = new Case[9][9];
    List<Case> listChifresSelectionner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grille = getIntent().getExtras().getParcelable("vGrille");
        creationCaseChiffre();
        miseEnTableau();
        setContentView(R.layout.activity_load_grid);




    }

    private void creationCaseChiffre() {
        for (int i = 1; i<=9; i++){
            listChifresSelectionner.add(new Case(true,i,true));
        }
    }

    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }
        v.invalidate();

        return true;
    }
    public vGrille test() {
        return grille;
    }


    public void caseSelect(int x, int y) {

        //on déssélectionne les autres cases


        if (x>=0 && x<=8 && y>=0 && y<=8) {
            for (Case caseEnCours : grille.cases) {
                caseEnCours.selectionner = false;

            }
            for (Case caseEnCours : listChifresSelectionner) {
                caseEnCours.selectionner = true;

            }
            int numCaseSelect = (y * 9) + x;
            Case caseSelect = grille.cases.get(numCaseSelect);
            if (!caseSelect.origine) {
                caseSelect.selectionner = true;

                int tabChiffres[] = new int[9];

                //les chiffres déjà présent dans la ligne ou colone
                for (int i = 0; i < 9; i++) {
                    //si le chiffre de la colone est différent de 0,
                    //on ne peut pas le sélectionner, donc on ne l'affiche pas
                    if (tabCase[y][i].chiffre != 0) {
                        listChifresSelectionner.get(tabCase[y][i].chiffre - 1).selectionner = false;
                    }
                    if (tabCase[i][x].chiffre != 0) {
                        listChifresSelectionner.get(tabCase[i][x].chiffre -1).selectionner = false;
                    }
                }

                //vérifier nombre dans le groupe de cases
                int modX = (x) /3;
                int modY = (y) /3;
                int xCase = (modX*3);
                int yCase = (modY*3);
                for (int i = xCase ; i<xCase+3;i++){
                    for (int z = yCase ; z<yCase+3;z++){
                        if (tabCase[z][i].chiffre != 0) {
                            listChifresSelectionner.get(tabCase[z][i].chiffre - 1).selectionner = false;
                        }

                    }

                }


            }
        }
        else if(y==10){
            for (Case caseEnCours : grille.cases) {
                if (caseEnCours.selectionner && listChifresSelectionner.get(x).selectionner){
                    caseEnCours.chiffre = x+1;
                }

            }
        }
    }
    private void miseEnTableau(){
        int x2=0;
        int y2= 0;
        for (Case caseEnCours : grille.cases){
            caseEnCours.selectionner=false;
            //mise dans un teableau double entré pour simplifier les calcules
            tabCase[x2][y2]= caseEnCours;
            if (y2<8){
                y2++;
            }
            else {
                y2=0;
                x2++;
            }
        }
    }

}
