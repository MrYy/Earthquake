package com.example.ge.earthquake;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ge on 2015/10/11.
 */
public class Quake {
    private Date date;
    private String detail;
    private Location location;
    private double magnitude;
    private String link;

    public Quake(Date date, String detail, Location location, double magnitude, String link) {
        this.date = date;
        this.detail = detail;
        this.location = location;
        this.magnitude = magnitude;
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public String getDetail() {
        return detail;
    }

    public String getLink() {
        return link;
    }

    public Location getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");
        String dataString = simpleDateFormat.format(date);
        return dataString+":"+magnitude+" "+detail;

    }


}
