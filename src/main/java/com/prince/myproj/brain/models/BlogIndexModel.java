package com.prince.myproj.brain.models;

/**
 * Created by gagaprince on 16-1-2.
 */
public class BlogIndexModel {
    private String title;
    private String content;
    private String link;
    private String img;

    public String getContent() {
        return content==null?"":content;
    }

    public String getImg() {
        return img==null?"":img;
    }

    public String getLink() {
        return link==null?"":link;
    }

    public String getTitle() {
        return title==null?"":title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
