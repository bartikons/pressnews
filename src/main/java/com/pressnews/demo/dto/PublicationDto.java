package com.pressnews.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class PublicationDto {
    private Long id;
    String name;
    Date publication;
    Timestamp timestamp;//zapisu dokumentu do warstwy persystencji (timestamp)
    AuthorDto article; //(imię i nazwisko)
    ContentsDto contents;

    public PublicationDto() {
    }

    public PublicationDto(String name, Date publication, Timestamp timestamp, AuthorDto article, ContentsDto contents) {
        this.name = name;
        this.publication = publication;
        this.timestamp = timestamp;
        this.article = article;
        this.contents = contents;
    }

    public PublicationDto(Long id, String name, Date publication, Timestamp timestamp, AuthorDto article, ContentsDto contents) {
        this.id = id;
        this.name = name;
        this.publication = publication;
        this.timestamp = timestamp;
        this.article = article;
        this.contents = contents;
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

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public AuthorDto getArticle() {
        return article;
    }

    public void setArticle(AuthorDto article) {
        this.article = article;
    }

    public ContentsDto getContents() {
        return contents;
    }

    public void setContents(ContentsDto contents) {
        this.contents = contents;
    }
}
