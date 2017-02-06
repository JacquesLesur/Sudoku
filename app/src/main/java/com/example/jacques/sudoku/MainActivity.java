package com.example.jacques.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Activity lecontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lecontext = this;
        Button buttonN1 = (Button)findViewById(R.id.buttonN1);
        buttonN1.setOnClickListener(this);
        Button buttonN2 = (Button)findViewById(R.id.buttonN2);
        buttonN2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       int idbutton = v.getId();
        Bundle bun = new Bundle();
        Intent defineIntent = new Intent(lecontext, ListeActivity.class);



        switch (idbutton) {
            case R.id.buttonN1:


                bun.putString("key", "1");
                break;
                
            case R.id.buttonN2:
                bun.putString("key", "2");
                break;

        }
        defineIntent.putExtras(bun);
        lecontext.startActivity(defineIntent);

    }
}
