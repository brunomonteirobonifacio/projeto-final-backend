package com.example.projetofinal.dto.artista;

import jakarta.validation.constraints.NotBlank;

public class ArtistaCreateDTO {

    @NotBlank(message = "É necessário informar o nome do artista")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
