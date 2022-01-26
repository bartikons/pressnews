package com.pressnews.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class ContentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "file")
    private String file;

    public ContentModel() {
    }

    public ContentModel(String title, String uploadFiles) {
        this.title = title;
        this.file = uploadFiles;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
