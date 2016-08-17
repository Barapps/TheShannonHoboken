package com.example.edwardlafontant.theshannonhoboken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText name, username, email, password, confirmpassword;
    String strname, strusername, strTeamName, stremail, strpassword, strpasswordconfirm;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    name  = (EditText)findViewById(R.id.Reg_SName);
    username  = (EditText)findViewById(R.id.Reg_UserName);
    email  = (EditText)findViewById(R.id.Reg_Email);
    password  = (EditText)findViewById(R.id.Reg_pass1);
    confirmpassword  = (EditText)findViewById(R.id.Reg_pass2);

    register = (Button)findViewById(R.id.btn_Reg);

    }

    public void userReg(View view)
    {
        strname = name.getText().toString();
        strusername= username.getText().toString();
        stremail= email.getText().toString();
        strpassword = password.getText().toString();
        strpasswordconfirm = confirmpassword.getText().toString();

        String method = "register";
        Backgroundtask backgroundTask = new Backgroundtask(this);

        if( TextUtils.isEmpty(strname)){

            name.setError( "Your Name is required!" );
           return;
        }

        if(TextUtils.isEmpty(strusername)) {

            username.setError("A Username is required!");
            return;
        }

        if( TextUtils.isEmpty(stremail)){

            email.setError( "An email address is required!" );
            return;

        }else{

            int isEmail = 0;
            for(int i = 0; i < stremail.length()-1; i++){

                if(stremail.charAt(i) == '@'){
                    isEmail++;
                }
            }

            if(isEmail != 1){

                email.setError( "This is not a valid email address" );
            }
        }

        if(!strpassword.matches(".*\\d+.*")&&
                !strpassword.matches(".*[a-z].*")||
                !strpassword.matches(".*[A-Z].*") ) {
            password.setError("Your password must include a number, uppercase letter, and a lowercase letter");
            return;
        }

        if (TextUtils.isEmpty(strpassword) || strpassword.length() < 6) {
            password.setError("You must have 6 characters in your password");
            return;
        }

        if (!strpassword.equals(strpasswordconfirm)) {
            password.setError("The passwords do not match.");
           return;
        }

        backgroundTask.execute(method, strname, strusername, stremail, strpassword);

        Intent intent = new Intent(Register.this, LoginRegister.class);
        finish();
        startActivity(intent);

    }

}
