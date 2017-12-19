package com.example.ianorellana.reigndesigntest;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ianorellana on 18-12-17.
 */

@SuppressWarnings("Serializable")

public class Objeto implements Serializable{
    private String story_title;
    private String title;
    private String author;
    private String created_at;
    private String story_url;
    private String url;

    public Objeto(String story_title, String author, String created_at, String story_url,
                  String title, String url){
        setStory_title(story_title);
        setAuthor(author);
        setCreated_at(created_at);
        setStory_url(story_url);
        setTitle(title);
        setUrl(url);
    }


    public void setStory_title(String story_title){this.story_title=story_title;}
    public String getStory_title(){return story_title;}

    public void setAuthor(String author){this.author=author;}
    public String getAuthor(){return author;}

    public void setCreated_at(String created_at){this.created_at=created_at;}
    public String getCreated_at(){
        String[] separator = created_at.split("T");
        String[] numbers = separator[0].split("-");
        return numbers[1]+"/"+numbers[2]+"/"+numbers[0];
    }

    public void setStory_url(String story_url){this.story_url=story_url;}
    public String getStory_url(){return story_url;}

    public void setTitle(String title){this.title=title;}
    public String getTitle(){return title;}

    public void setUrl(String url){this.url=url;}
    public String getUrl(){return url;}
}
