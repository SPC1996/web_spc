package com.keessi.web.entity;

import java.util.Date;
import java.util.Map;

public class News {
    private String id;
    private String classId;
    private String title;
    private String content;
    private String worker;
    private Date time;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static News transfer(Map<String,Object> map) {
        News news=new News();
        news.setId((String) map.get("id"));
        news.setClassId((String) map.get("classId"));
        news.setTitle((String) map.get("title"));
        news.setContent((String) map.get("content"));
        news.setWorker((String) map.get("worker"));
        news.setTime(new Date((Long) map.get("time")));
        news.setImage((String) map.get("image"));
        return news;
    }
}
