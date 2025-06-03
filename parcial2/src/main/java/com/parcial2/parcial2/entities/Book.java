package com.parcial2.parcial2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false)
    @NotBlank
    private String author;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String isbn;

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(nullable = false)
    @NotBlank
    private String language;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    @NotBlank
    private String genre;
}
