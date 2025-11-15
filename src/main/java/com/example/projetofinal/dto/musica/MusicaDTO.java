package com.example.projetofinal.dto.musica;

import com.example.projetofinal.dto.artista.ArtistaDTO;
import com.example.projetofinal.dto.genero.GeneroDTO;

import java.util.UUID;

public class MusicaDTO {
    private UUID id;
    private String nome;
    private Integer duracaoEmSegundos;
    private GeneroDTO genero;
    private ArtistaDTO artista;

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

    public Integer getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(Integer duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public GeneroDTO getGenero() {
        return genero;
    }

    public void setGenero(GeneroDTO genero) {
        this.genero = genero;
    }

    public ArtistaDTO getArtista() {
        return artista;
    }

    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }
}
