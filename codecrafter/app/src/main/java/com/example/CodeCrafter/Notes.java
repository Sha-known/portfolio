package com.example.CodeCrafter;

import java.util.Date;

public class Notes {
    private String title;
    private String content;
    private Date timeStamp;

    public  Notes() {

    }
    public Notes(String title, String content, Date timeStamp) {
        this.title = title;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public Notes(String title, String content) {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
