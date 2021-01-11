package com.example.anrdoid.newsappstage1;

/**
 * Created by Labtop on 18/02/18.
 */

public class News {
    private String Title;
    private String section;
    private String Date;
    private String Url;
    private String pillar;
    private String Type;


    public News(String Title, String section, String Date, String Url, String pillar,String Type) {
        this.Title = Title;
        this.section = section;
        this.Date = Date;
        this.Url = Url;
        this.pillar = pillar;
        this.Type=Type;

    }
    public News(String Title, String section, String Date, String Url,String Type) {
        this.Title = Title;
        this.section = section;
        this.Date = Date;
        this.Url = Url;
        this.Type=Type;

    }
    public String getUrl() {
        return Url;
    }
    public String getType() { return Type; }
    public String getpillar() {
        return pillar;
    }
    public String getTitle() {
        return Title;
    }
    public String getSection() {
        return section;
    }
    public String getDate() {
        return Date;
    }
}
