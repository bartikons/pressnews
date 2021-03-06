package com.pressnews.demo.dto;

import com.pressnews.demo.model.AuthorModel;

public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorDto() {
    }

    public AuthorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDto(AuthorModel authorModel) {
        this.id = authorModel.getId();
        this.firstName = authorModel.getFirstName();
        this.lastName = authorModel.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
