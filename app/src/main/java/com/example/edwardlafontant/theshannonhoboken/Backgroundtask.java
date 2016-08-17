package com.example.edwardlafontant.theshannonhoboken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by edwardlafontant on 4/9/16.
 */
public class Backgroundtask  extends AsyncTask<String, Integer, String> {

    static ProgressBar progressBar;
    TextView txt;
    static Integer count =1;

    AlertDialog alertDialog;

    static String user;

    String logMethod = null;

    Context ctx;
    Backgroundtask(Context ctx){
        this.ctx = ctx;



    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/Register.php";
        String login_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/Login.php";
        String logout_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/Logout.php";


        String method = params[0];
        if (method.equals("register")) {

            String name = params[1];
            String user_name = params[2];
            String email = params[3];
            String pass = params[4];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setDoOutput(true);
                OutputStream OS = httpurlconnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpurlconnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null)
                {
                    response+= line;

                }
                bufferedReader.close();
                inputStream.close();
                httpurlconnection.disconnect();
                logMethod = "registered";
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (method.equals("login"))
        {
            String login_name = params[1];
            String login_password = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("login_name", "UTF-8")+"="+URLEncoder.encode(login_name, "UTF-8")+"&"+
                        URLEncoder.encode("login_password", "UTF-8")+"="+URLEncoder.encode(login_password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null)
                {
                    response+= line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                logMethod = "loggedin";
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (method.equals("logout")) {

            try {
                URL url = new URL(logout_url);
                HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
                httpurlconnection.setRequestMethod("POST");
                httpurlconnection.setDoOutput(true);
                OutputStream OS = httpurlconnection.getOutputStream();
                OS.close();
                InputStream inputStream = httpurlconnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null)
                {
                    response+= line;

                }
                bufferedReader.close();
                inputStream.close();
                httpurlconnection.disconnect();
                logMethod = "loggedout";
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        progressBar.setProgress(values[0]);

    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals(result) && logMethod.equals("registered"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this.ctx, LoginRegister.class);
            ((Activity) ctx).finish();
            ctx.startActivity(intent);
            user = null;

        }

        if(result.equals("Username or Password is incorrect. Please try again.")){

            //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

            return;
        }

        if(result.equals(result) && logMethod.equals("loggedin"))
        {
            Toast.makeText(ctx, "Welome!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this.ctx, MainMenu.class);
            ((Activity) ctx).finish();
            ctx.startActivity(intent);
            user = result;
        }

        if(result.equals("Username already exists. Please choose a different Username.")){

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

            return;
        }

        if(result.equals("You have successfully logged out!")&& logMethod.equals("loggedout"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this.ctx, LoginRegister.class);
            ctx.startActivity(intent);
            logMethod = null;
            user = null;

        }


    }


}

