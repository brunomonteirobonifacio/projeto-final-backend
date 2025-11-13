package com.example.projetofinal.repository;

import com.example.projetofinal.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistaRepository extends JpaRepository<Artista, UUID> {
}
