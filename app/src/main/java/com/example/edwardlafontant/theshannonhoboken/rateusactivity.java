package com.example.edwardlafontant.theshannonhoboken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class rateusactivity extends AppCompatActivity {


    RatingBar ratingbar;
    Button button;
    String rating, user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateusactivity);

        ratingbar=(RatingBar)findViewById(R.id.ratingBar);

        button=(Button)findViewById(R.id.button1);



    }


    public void rateUs(View view){

        rating=String.valueOf(ratingbar.getRating());

        ratingTask ratingTask = new ratingTask(this);
        ratingTask.execute(Backgroundtask.user, rating);

    }

}

