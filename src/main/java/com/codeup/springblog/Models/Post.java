package com.codeup.springblog.Models;

import javax.persistence.*;

public class Post {
    @Id @GeneratedValue
    private long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, length = 18500)
    private String body;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Post(){
    }

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }

    public Post(String title, long id){
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public Post(String title, String body, User user){
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body=body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
