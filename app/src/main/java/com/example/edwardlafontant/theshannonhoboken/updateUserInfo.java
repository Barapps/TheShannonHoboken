package com.example.edwardlafontant.theshannonhoboken;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class updateUserInfo extends AppCompatActivity {

    Button updateEmail, updateName, updatePassword;
    ImageButton homebtn;
    EditText emailEntry, nameEntry, passEntry;
    String user_email, user_name, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        emailEntry = (EditText)findViewById(R.id.emailUpdate);
        nameEntry = (EditText)findViewById(R.id.nameUpdate);
        updateEmail = (Button)findViewById(R.id.updateEmailbutton);
        updateName = (Button)findViewById(R.id.updateNamebutton);
        passEntry = (EditText)findViewById(R.id.passUpdate);
        updatePassword = (Button)findViewById(R.id.updatePassbutton);


        homebtn = (ImageButton) findViewById(R.id.homeBtnupdate);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateUserInfo.this, MainMenu.class);
                startActivity(intent);
            }
        });




    }

    public void updateEmail(View view)
    {

        user_email = emailEntry.getText().toString();
        updateEmailTask updateEmailTask = new updateEmailTask(this);
        updateEmailTask.execute(Backgroundtask.user, user_email);
    }

    public void updateName(View view)
    {

        user_name = nameEntry.getText().toString();
        updateNameTask updateNameTask = new updateNameTask(this);
        updateNameTask.execute(Backgroundtask.user, user_name);
    }

    public void updatePass(View view) {

        user_password = passEntry.getText().toString();

        if (!user_password.matches(".*\\d+.*") &&
                !user_password.matches(".*[a-z].*") ||
                !user_password.matches(".*[A-Z].*")) {
            passEntry.setError("Your password must include a number, uppercase letter, and a lowercase letter");
            return;
        }
        if (TextUtils.isEmpty(user_password) || user_password.length() < 6) {
            passEntry.setError("You must have 6 characters in your password");
            return;
        }

        updatePasswordTask updatePasswordTask = new updatePasswordTask(this);
        updatePasswordTask.execute(Backgroundtask.user, user_password);
    }

}
