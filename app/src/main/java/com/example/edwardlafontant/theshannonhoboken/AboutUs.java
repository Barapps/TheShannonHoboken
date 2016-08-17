package com.example.edwardlafontant.theshannonhoboken;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutUs extends AppCompatActivity {

    final Context context = this;


    ImageButton homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button btn_dressCode = (Button)findViewById(R.id.btnDresscode);
        btn_dressCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Dress Code")
                        .setMessage("Dress to impress….because you never know who you will meet!  " +
                                "Management has the right to refuse entry")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }




        });

        homebtn = (ImageButton)findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUs.this, MainMenu.class);
                startActivity(intent);
            }
        });

}
    public void process(View view) {
        Intent intent = null;

        if (view.getId() == R.id.btnDirect)
        {
            intent = new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("google.navigation:q=106+1st+Street+Hoboken+New+Jersey"));
            startActivity(intent);
        }
    }

}
