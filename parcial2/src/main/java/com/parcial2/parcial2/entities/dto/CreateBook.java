package com.parcial2.parcial2.entities.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateBook {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String author;

    @NotNull
    @NotBlank
    private String isbn;

    @NotNull
    private Integer publicationYear;

    @NotNull
    @NotBlank
    private String language;

    @NotNull
    private Integer pages;

    @NotNull
    @NotBlank
    private String genre;
}
