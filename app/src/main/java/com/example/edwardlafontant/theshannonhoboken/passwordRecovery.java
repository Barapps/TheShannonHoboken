package com.example.edwardlafontant.theshannonhoboken;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

/**
 * Created by edwardlafontant on 5/7/16.
 */
public class passwordRecovery extends AsyncTask<String, Void, String> {

    Context ctx;
    passwordRecovery(Context ctx){
        this.ctx = ctx;



    }
    static String user_email;


    @Override
    protected String doInBackground(String... params) {
        String update_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/passwordRecovery.php";


         user_email = params[0];

        try {
            URL url = new URL(update_url);
            HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setDoOutput(true);
            OutputStream OS = httpurlconnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream = httpurlconnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String res = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                res += line;

            }
            bufferedReader.close();
            inputStream.close();
            httpurlconnection.disconnect();
            return res;

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


        //if (result.equals("Your passward has been emailed to you.")) {
       //     Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
       //     Intent intent = new Intent(this.ctx, LoginRegister.class);
      //      ctx.startActivity(intent);
      //  } else{
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
//}

