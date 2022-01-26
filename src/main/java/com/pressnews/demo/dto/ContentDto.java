package com.pressnews.demo.dto;

import com.pressnews.demo.model.ContentModel;

public class ContentDto {
    private String title;

    public ContentDto(ContentModel contents) {
        this.title = contents.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
