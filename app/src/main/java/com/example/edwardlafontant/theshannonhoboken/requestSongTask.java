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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by edwardlafontant on 5/4/16.
 */
public class requestSongTask extends AsyncTask<String, Void, String> {

    Context ctx;
    requestSongTask(Context ctx){
        this.ctx = ctx;

    }



    @Override
    protected String doInBackground(String... params) {
        String songRequest_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/songRequestInput.php";




            String songName = params[0];
            String Artist = params[1];

            try {
                URL url = new URL(songRequest_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("songName", "UTF-8")+"="+URLEncoder.encode(songName, "UTF-8")+"&"+
                        URLEncoder.encode("Artist", "UTF-8")+"="+URLEncoder.encode(Artist, "UTF-8");
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

        if (result.equals("Song Request Sent!")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }



    }
}


