
package com.example.myapplication.Bean;

import java.io.Serializable;

/**
 * @author WTL
 * @version 1.0.0
 * @date 2022/11/17
 * @describe 该类仅作为记事本的实体类
 */
public class Note implements Serializable {
    private String title;
    private String content;
    private String createdTime;
    private String id;

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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
