package com.example.edwardlafontant.theshannonhoboken;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwardlafontant on 5/4/16.
 */
public class RequestListView extends ArrayAdapter {

    List list = new ArrayList();

    public RequestListView(Context context, int resource) {
        super(context, resource);
    }


    public void add(Requests object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        RequestHolder requestHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            requestHolder = new RequestHolder();
            requestHolder.tx_song = (TextView) row.findViewById(R.id.tx_songname);
            requestHolder.tx_artist = (TextView) row.findViewById(R.id.tx_artistname);
            requestHolder.tx_date = (TextView) row.findViewById(R.id.tx_date);
            row.setTag(requestHolder);

        }
        else
        {
            requestHolder = (RequestHolder) row.getTag();
        }

        Requests requests = (Requests)this.getItem(position);
        requestHolder.tx_song.setText(requests.getSong());
        requestHolder.tx_artist.setText(requests.getArtist());
        requestHolder.tx_date.setText(requests.getDate());
        return row;

    }

    static class RequestHolder
    {
        TextView tx_song, tx_artist, tx_date;
    }
}
