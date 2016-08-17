package com.example.edwardlafontant.theshannonhoboken;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class addWinRequest extends AppCompatActivity {

    Button updateWin;

    EditText opponent_name;

    String opponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_win_request);

        updateWin = (Button)findViewById(R.id.updateWinBtn);
        opponent_name = (EditText)findViewById(R.id.oppoNameInput);


    }



    public void sendWinRequest(View view){

        opponent = opponent_name.getText().toString();

        addWin addWin = new addWin(this);
        addWin.execute(Backgroundtask.user, opponent);
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();





    }

}
