package com.example.jacques.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String niveau;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPosition = position;


        Activity lecontext = this;
        Intent defineIntent = new Intent(lecontext, LoadGrid.class);
        vGrille grilleSelectioner = null;
        try {
             grilleSelectioner = creerGrille(itemPosition);
        } catch (IOException e) {
            e.printStackTrace();
        }
        defineIntent.putExtra("vGrille",grilleSelectioner);
        lecontext.startActivity(defineIntent);

    }

    private vGrille creerGrille(int itemPosition) throws IOException {
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        AssetManager assetManager = getAssets();

        try {
            InputStream input;
            input = assetManager.open("Niveaux/"+niveau);

            lecteurAvecBuffer = new BufferedReader( new BufferedReader(new InputStreamReader(input)));
        } catch (FileNotFoundException exc) {
            System.out.println(exc);


        }
        int compteur = 0;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
            if (compteur==itemPosition){
                break;
            }
        compteur++;
        }
        lecteurAvecBuffer.close();

        vGrille grille = new vGrille(1,1,0);

        //Ancienne méthode lorsque j'utilisait le tableau imbriqué
       /* for (int i=0;i<9;i++){
            for (int y=0;y<9;y++){

                int nombre  =  Character.getNumericValue(ligne.charAt(y+(i*9)));
                boolean caseOrigine = true;
                if (nombre==0)
                {
                    caseOrigine=false;
                }
                Case newCase = new Case(caseOrigine, nombre);
                grille.addCase(i,y,newCase);
            }
        }*/

        for (int i=0;i<81;i++){


                int nombre  =  Character.getNumericValue(ligne.charAt(i));
                boolean caseOrigine = true;
                if (nombre==0)
                {
                    caseOrigine=false;
                }
                Case newCase = new Case(caseOrigine, nombre);
                grille.addCase(newCase);

        }

        return grille;
    }

    public String toString() {
        return " TOTO";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bun = this.getIntent().getExtras();

        setContentView(R.layout.activity_liste);

        TextView text = (TextView) findViewById(R.id.textRes);
        text.setText(bun.getString("key"));
        niveau = bun.getString("key")+".txt";



        ListView liste = (ListView) findViewById(R.id.maliste);


        ArrayList<vGrille> vgrille = new ArrayList<vGrille>();
        for(int i=1;i<=100;i++) {

            vgrille.add(new vGrille(i, 1, 0));
        }
        MyAdapter adapter = new MyAdapter(this, vgrille);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Liste Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
