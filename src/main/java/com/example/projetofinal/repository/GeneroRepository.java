package com.example.projetofinal.repository;

import com.example.projetofinal.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeneroRepository extends JpaRepository<Genero, UUID> {

}
