package com.example.edwardlafontant.theshannonhoboken;

import android.app.Activity;
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
 * Created by edwardlafontant on 5/13/16.
 */
public class updatePasswordTask extends AsyncTask<String, Void, String> {


    Context ctx;
    updatePasswordTask(Context ctx){
        this.ctx = ctx;

    }


    @Override
    protected String doInBackground(String... params) {
        String update_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/UpdatePassword.php";


        String current_User = params[0];
        String user_password = params[1];


        try {
            URL url = new URL(update_url);
            HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setDoOutput(true);
            OutputStream OS = httpurlconnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("current_User", "UTF-8")+"="+URLEncoder.encode(current_User, "UTF-8")+"&"+
                    URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8");
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

        if (result.equals("Your password was updated successfully!")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this.ctx, myProfile.class);
            ((Activity) ctx).finish();
            ctx.startActivity(intent);
        } else{
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}
