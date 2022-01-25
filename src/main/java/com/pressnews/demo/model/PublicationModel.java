package com.pressnews.demo.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Article")
public class PublicationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    String name;
    @Column(name = "publication")
    Date publication;

    @Column(name = "publication")
    Timestamp timestamp;//zapisu dokumentu do warstwy persystencji (timestamp)

    @ManyToOne
    AuthorModel article; //(imię i nazwisko)

    @OneToOne
    @JoinColumn(name = "contents_id")
    ContentsModel contents;//(tytuł publication i treść publication)

    public PublicationModel() {
    }

    public PublicationModel(Long id, String name, Date publication, Timestamp timestamp, AuthorModel article, ContentsModel contents) {
        this.id = id;
        this.name = name;
        this.publication = publication;
        this.timestamp = timestamp;
        this.article = article;
        this.contents = contents;
    }

    public ContentsModel getContents() {
        return contents;
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

    public AuthorModel getArticle() {
        return article;
    }

    public void setArticle(AuthorModel article) {
        this.article = article;
    }

    public void setContents(ContentsModel contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publication=" + publication +
                ", timestamp=" + timestamp +
                ", article=" + article +
                ", contents=" + contents +
                '}';
    }
}
