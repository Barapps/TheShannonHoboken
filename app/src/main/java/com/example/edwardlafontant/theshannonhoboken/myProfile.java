package com.example.edwardlafontant.theshannonhoboken;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

public class myProfile extends AppCompatActivity {

    static String fullName;
    TextView userInfo, nameName, emailView, teamView;
    Button updateInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        userInfo = (TextView) findViewById(R.id.userNameView);
        userInfo.setText("Username: " + Backgroundtask.user);


        BackgroundTaskProfile backgroundTaskProfile = new BackgroundTaskProfile();
        backgroundTaskProfile.execute();
        BackgroundTaskProfile2 backgroundTaskProfile2 = new BackgroundTaskProfile2();
        backgroundTaskProfile2.execute();
        BackgroundTaskProfile3 backgroundTaskProfile3 = new BackgroundTaskProfile3();
        backgroundTaskProfile3.execute();

        updateInfo = (Button)findViewById(R.id.updateInfobtn);
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myProfile.this, updateUserInfo.class);
                startActivity(intent);
            }
        });


    }


    public class BackgroundTaskProfile extends AsyncTask<String, Void, String> {

        String response1 = "";


        @Override
        protected String doInBackground(String... params) {

            String login_name = Backgroundtask.user;

            String profileName_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/getNamedata.php";



            try {
                URL url = new URL(profileName_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;


                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            fullName = result;

            nameName = (TextView)findViewById(R.id.nameprof);
            nameName.setText(fullName);


        }
    }

    public class BackgroundTaskProfile2 extends AsyncTask<String, Void, String> {


        String response2 = "";

        @Override
        protected String doInBackground(String... params) {

            String login_name = Backgroundtask.user;


            String profileEmail_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/getEmailData.php";



            try {
                URL url = new URL(profileEmail_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));

                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8");
                bufferedWriter2.write(data);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream2.close();

                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader2.readLine()) != null) {
                    response2 += line;


                }

                bufferedReader2.close();
                inputStream2.close();
                httpURLConnection2.disconnect();
                return response2;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }




        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            emailView = (TextView)findViewById(R.id.emailprof);
            emailView.setText(response2);
        }
    }


    public class BackgroundTaskProfile3 extends AsyncTask<String, Void, String> {

        String response3 = "";

        @Override
        protected String doInBackground(String... params) {

            String login_name = Backgroundtask.user;
            String profileTeam_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/getTeamData.php";

            try {

                URL url = new URL(profileTeam_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response3 += line;


                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response3;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            teamView = (TextView)findViewById(R.id.userTeamView);
            teamView.setText(response3);

        }
    }
}


