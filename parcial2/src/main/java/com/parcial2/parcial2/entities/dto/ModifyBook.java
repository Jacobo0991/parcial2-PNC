package com.parcial2.parcial2.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ModifyBook {
    @NotNull
    @NotBlank
    private String id;

    private String title;

    private String language;
}
