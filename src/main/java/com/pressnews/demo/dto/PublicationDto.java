package com.pressnews.demo.dto;

import com.pressnews.demo.model.PublicationModel;

import java.sql.Date;
import java.sql.Timestamp;

public class PublicationDto {
    private Long id;
    String name;
    Date date;
    Timestamp timestamp;//zapisu dokumentu do warstwy persystencji (timestamp)
    AuthorDto author; //(imię i nazwisko)
    ContentDto contents;

    public PublicationDto() {
    }

    public PublicationDto(PublicationModel publicationModel) {
        this.id = publicationModel.getId();
        this.name = publicationModel.getName();
        this.date = publicationModel.getDate();
        this.timestamp = publicationModel.getTimestamp();
        this.author = new AuthorDto(publicationModel.getAuthor());
        this.contents = new ContentDto(publicationModel.getContent());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public ContentDto getContents() {
        return contents;
    }

    public void setContents(ContentDto contents) {
        this.contents = contents;
    }
}
