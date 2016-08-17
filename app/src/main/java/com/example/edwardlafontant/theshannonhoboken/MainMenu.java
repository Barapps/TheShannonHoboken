package com.example.edwardlafontant.theshannonhoboken;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.edwardlafontant.myapplication.djmessage.myApi.MyApi;
import com.example.edwardlafontant.myapplication.djmessage.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MainMenu extends AppCompatActivity {

    ImageButton songRequest;
    final Context context = this;
    String Artistresult, songResult, result1, result2;
    EditText userInput1;
    EditText userInput2;
    String opponent;
    EditText opponent_name;
    Button addWin, rateus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        rateus = (Button)findViewById(R.id.btnRateus);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, rateusactivity.class);
                startActivity(intent);
            }
        });

        Button btn_aboutus = (Button) findViewById(R.id.btnaboutus);
        btn_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AboutUs.class);
                startActivity(intent);
            }

        });

        Button btn_drinkMenu = (Button) findViewById(R.id.btndrinkmenu);
        btn_drinkMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.drinkmenulayout, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setView(promptsView);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setNegativeButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });



        Button btn_events = (Button) findViewById(R.id.btnevents);
        btn_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Events.class);
                startActivity(intent);
            }

        });

        Button btn_myProfile = (Button) findViewById(R.id.btnmyprofile);
        btn_myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, myProfile.class);
                startActivity(intent);
            }

        });

        Button btn_scoreBoard = (Button) findViewById(R.id.btnscoreboard);
        btn_scoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Scoreboard.class);
                startActivity(intent);
            }

        });

        Button btn_pics = (Button) findViewById(R.id.btnpics);
        btn_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, PicShare.class);
                startActivity(intent);
            }

        });

        songRequest = (ImageButton) findViewById(R.id.songbtn);

        songRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.songreqprompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                 userInput1 = (EditText) promptsView
                        .findViewById(R.id.songNameInput);

                 userInput2 = (EditText) promptsView
                        .findViewById(R.id.artistNameInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        addWin = (Button)findViewById(R.id.btnwin);
        addWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.content_add_win_request, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                opponent_name = (EditText) promptsView
                        .findViewById(R.id.oppoNameInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }


        });



    }




    public void logOut(View view){

        String method = "logout";

        Backgroundtask backgroundtask = new Backgroundtask(this);
        backgroundtask.execute(method);

        Intent intent = new Intent(MainMenu.this, LoginRegister.class);
        startActivity(intent);
        finish();
    }

    public void sendRequest(View view)
    {

        result1 = userInput1.getText().toString();
        result2 = userInput2.getText().toString();
        if(result1.equals("") && result2.equals("")){

            Toast.makeText(MainMenu.this, "You must enter at least 1 field", Toast.LENGTH_LONG).show();
            return;
        }else {
            requestSongTask requestsongTask = new requestSongTask(this);
            requestsongTask.execute(result1, result2);
            Intent intent = new Intent(MainMenu.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }

    public void sendWinRequest(View view){

        opponent = opponent_name.getText().toString();

        addWin addWin = new addWin(this);
        addWin.execute(Backgroundtask.user, opponent);






    }


}
