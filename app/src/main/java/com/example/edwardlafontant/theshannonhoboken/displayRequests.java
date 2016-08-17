package com.example.edwardlafontant.theshannonhoboken;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class displayRequests extends AppCompatActivity {

    String json_string, song,artist,date;
    String songDelete;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RequestListView requestListView;
    ListView listView;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requests);
        listView = (ListView)findViewById(R.id.listview);

        requestListView = new RequestListView(this,R.layout.row_layout);
        listView.setAdapter(requestListView);
        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            count = 0;
            //String song,artist,date;

            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);

                song = JO.getString("songName");
                artist = JO.getString("Artist");
                date = JO.getString("DATETIME");

                Requests requests = new Requests(song, artist, date);
                requestListView.add(requests);

                count++;


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void deleteReq(View view){

        Toast.makeText(displayRequests.this, songDelete+" Deleted", Toast.LENGTH_SHORT).show();

    }
}
