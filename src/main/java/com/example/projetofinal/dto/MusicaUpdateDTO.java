package com.example.projetofinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class MusicaUpdateDTO {
    @NotBlank(message = "É necessário informar o nome da música")
    private String nome;

    @NotNull(message = "É necessário informar a duração em segundos da música")
    @Positive(message = "Duração da música não pode ser negativa")
    private Integer duracaoEmSegundos;

    @NotNull(message = "É necessário informar o artista da música")
    private UUID artistaId;

    @NotNull(message = "É necessário informar o gênero da música")
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
