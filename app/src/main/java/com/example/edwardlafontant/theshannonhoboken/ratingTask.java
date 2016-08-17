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
 * Created by edwardlafontant on 5/8/16.
 */
public class ratingTask extends AsyncTask <String, Void, String> {

    Context ctx;
    ratingTask(Context ctx){
        this.ctx = ctx;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String user = params[0];
        String rating = params[1];

        String rate_url = "http://blue.cs.montclair.edu/~lafontan/TheShannonHoboken/storeRating.php";


        try {
            URL url = new URL(rate_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String data = URLEncoder.encode("user", "UTF-8")+"="+URLEncoder.encode(user, "UTF-8")+"&"+
                    URLEncoder.encode("rating", "UTF-8")+"="+URLEncoder.encode(rating, "UTF-8");
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
        if(result.equals("Thank you for rating us!"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this.ctx, MainMenu.class);
            ((Activity) ctx).finish();
            ctx.startActivity(intent);
        }else{
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

    }
}
