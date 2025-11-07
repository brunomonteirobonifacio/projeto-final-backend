package com.example.projetofinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class MusicaUpdateDTO {
    @NotBlank
    private String nome;

    @NotNull
    @Size
    private Integer duracaoEmSegundos;

    @NotNull
    private UUID artistaId;

    @NotNull
    private UUID generoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(Integer duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public UUID getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(UUID artistaId) {
        this.artistaId = artistaId;
    }

    public UUID getGeneroId() {
        return generoId;
    }

    public void setGeneroId(UUID generoId) {
        this.generoId = generoId;
    }
}
