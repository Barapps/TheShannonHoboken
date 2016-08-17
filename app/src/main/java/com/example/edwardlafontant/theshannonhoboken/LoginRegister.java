package com.example.edwardlafontant.theshannonhoboken;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class LoginRegister extends AppCompatActivity {

    EditText UserName, Password, email_name;
    String login_Name, login_password;
    final Context context = this;
    String user_email;
    Button passwordrecovertxt;

    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        VideoView vid = (VideoView)findViewById(R.id.theshannonvideo);
        vid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.theshannonappintro);

        vid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        vid.start();

        UserName = (EditText)findViewById(R.id.loginusername);
        Password = (EditText)findViewById(R.id.loginpass);

        Button btn_reg = (Button) findViewById(R.id.regBtn);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginRegister.this, Register.class);
                startActivity(intent);
            }


        });

        passwordrecovertxt = (Button)findViewById(R.id.forgotPassword);
        passwordrecovertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.passreoverylayout, null);

                 alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                email_name = (EditText) promptsView
                        .findViewById(R.id.recoveryEmail);

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

    public void userLogin(View view)
    {
        Toast.makeText(LoginRegister.this, "Connecting...", Toast.LENGTH_SHORT).show();
        login_Name = UserName.getText().toString();
        login_password = Password.getText().toString();

        if(login_Name.equals("DJ") && login_password.equals("dj")){

            Intent intent = new Intent(LoginRegister.this, djMain.class);
            startActivity(intent);
        }

            String method = "login";
        Backgroundtask backgroundtask = new Backgroundtask(this);
        backgroundtask.execute(method, login_Name, login_password);

        }

    public void recoverPass(View view){

        user_email = email_name.getText().toString();
        passwordRecovery passwordRecovery = new passwordRecovery(this);
        passwordRecovery.execute(user_email);
        Intent intent = new Intent(this, LoginRegister.class);
        this.finish();
        this.startActivity(intent);


    }




}
