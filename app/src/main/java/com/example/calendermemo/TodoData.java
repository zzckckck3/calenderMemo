package com.example.calendermemo;

public class TodoData {
    String title;
    String contents;
    boolean isDone;

    public TodoData(String title, String contents, boolean isDone){
        this.title = title;
        this.contents = contents;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
