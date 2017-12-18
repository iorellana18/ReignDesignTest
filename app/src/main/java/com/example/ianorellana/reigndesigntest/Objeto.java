package com.example.ianorellana.reigndesigntest;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ianorellana on 18-12-17.
 */

@SuppressWarnings("Serializable")

public class Objeto implements Serializable{
    private String story_title;
    private String author;
    private String created_at;
    private String story_url;

    public Objeto(String story_title, String author, String created_at, String story_url){
        setStory_title(story_title);
        setAuthor(author);
        setCreated_at(created_at);
        setStory_url(story_url);
    }


    public void setStory_title(String story_title){this.story_title=story_title;}
    public String getStory_title(){return story_title;}

    public void setAuthor(String author){this.author=author;}
    public String getAuthor(){return author;}

    public void setCreated_at(String created_at){this.created_at=created_at;}
    public String getCreated_at(){return created_at;}

    public void setStory_url(String story_url){this.story_url=story_url;}
    public String getStory_url(){return story_url;}

}
