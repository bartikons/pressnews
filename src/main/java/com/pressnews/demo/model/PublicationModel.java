package com.pressnews.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "publication")
public class PublicationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date_modification")
    Date date;

    @Column(name = "name")
    String name;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    Timestamp createAt;

    @ManyToOne
    AuthorModel author;

    @OneToOne
    @JoinColumn(name = "content_id")
    ContentModel content;

    public PublicationModel() {
    }

    public PublicationModel(String name, String date) {
        this.name = name;
        this.date = Date.valueOf(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return createAt;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public ContentModel getContent() {
        return content;
    }

    public void setContent(ContentModel contents) {
        this.content = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
