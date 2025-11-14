package com.example.projetofinal.dto.genero;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class GeneroDTO {
    private UUID id;

    @NotBlank
    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
