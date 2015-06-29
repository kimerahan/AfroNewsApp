package com.app.com.a23labs.android.afronewsapp;

/**
 * Created by kh3n on 15/06/2015.
 */
public class Devotional {
     //int  rank;
    String title;
   String body;
   // String population;
   String artlink;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getArtlink() {
        return artlink;
    }
   //                int rank
    public Devotional(String title, String body, String artlink) {

        this.title = title;
        this.body = body;
       // this.population = population;
        this.artlink = artlink;
    }
}
