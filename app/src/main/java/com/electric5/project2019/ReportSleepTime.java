package com.electric5.project2019;

public class ReportSleepTime {
    private String item_date;
    private String item_sleeptime;

    public String getDate(){
        return item_date;
    }

    public String getSleepTime(){
        return item_sleeptime;
    }

    public void setDate(String item_date){
        this.item_date = item_date;
    }

    public void setSleepTime(String item_sleeptime){
        this.item_sleeptime = item_sleeptime;
    }

    public ReportSleepTime(String date, String sleeptime){
        item_date = date;
        item_sleeptime = sleeptime;
    }
}
