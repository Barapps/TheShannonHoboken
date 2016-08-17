package com.example.edwardlafontant.theshannonhoboken;

/**
 * Created by edwardlafontant on 5/4/16.
 */
public class Requests {
    private String song, artist, date;

    public Requests(String song, String artist, String date)
    {
        this.setSong(song);
        this.setArtist(artist);
        this.setDate(date);
    }


    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
