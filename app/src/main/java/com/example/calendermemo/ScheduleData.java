package com.example.calendermemo;

public class ScheduleData {
    private int id;
    private String title;
    private String start_date;
    private String finish_date;
    private String start_time;
    private String finish_time;
    private String location;

    public ScheduleData(int id, String title, String start_date, String finish_date, String start_time, String finish_time, String location){
        this.id = id;
        this.title = title;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public String getLocation() {
        return location;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

}
