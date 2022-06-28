package com.hnincherry.fingerprintapp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo implements Serializable {

    private Date date;
    private String text;

    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Memo() {
        this.date = new Date();

    }

    public Memo(long time, String text) {
        this.date = new Date(time);
        this.text = text;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public long getTime(){
        return date.getTime();
    }

    public void setText (String text){
        this.text = text ;
    }

    public String getText(){
        return this.text;
    }

}

