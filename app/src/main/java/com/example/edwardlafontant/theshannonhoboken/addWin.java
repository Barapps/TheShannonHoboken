package com.example.edwardlafontant.theshannonhoboken;

import android.app.Activity;
import android.app.AlertDialog;
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
public class addWin extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;

    Context ctx;
    addWin(Context ctx){
        this.ctx = ctx;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Update Win Stats");
    }

    @Override
    protected String doInBackground(String... params) {

        String win_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/recordWin.php";


        String login_name = params[0];
        String opponent_name = params[1];

        try {
            URL url = new URL(win_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String data = URLEncoder.encode("login_name", "UTF-8")+"="+URLEncoder.encode(login_name, "UTF-8")+"&"+
                    URLEncoder.encode("opponent_name", "UTF-8")+"="+URLEncoder.encode(opponent_name, "UTF-8");
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

        if(result.equals("Team Name does not exist. Please try again.")){

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

            return;

        }

        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this.ctx, MainMenu.class);
        ctx.startActivity(intent);
        ((Activity) ctx).finish();


    }
}
