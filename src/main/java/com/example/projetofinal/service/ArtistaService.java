package com.example.projetofinal.service;

import com.example.projetofinal.dto.artista.ArtistaCreateDTO;
import com.example.projetofinal.dto.artista.ArtistaDTO;
import com.example.projetofinal.exception.NotFoundException;
import com.example.projetofinal.model.Artista;
import com.example.projetofinal.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public ArtistaDTO create(ArtistaCreateDTO dto) {
        Artista artista = toEntity(dto);
        artista = artistaRepository.save(artista);
        return toDTO(artista);
    }

    public Page<ArtistaDTO> listAll(Pageable pageable) {
        Page<Artista> page = artistaRepository.findAll(pageable);

        List<ArtistaDTO> dtos = page.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    public ArtistaDTO findById(UUID id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o artista com o ID informado"));

        return toDTO(artista);
    }

    public ArtistaDTO update(UUID id, ArtistaDTO dto) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o artista com o ID informado"));

        artista.setNome(dto.getNome());
        artista = artistaRepository.save(artista);

        return toDTO(artista);
    }

    public void delete(UUID id) {
        if (!artistaRepository.existsById(id)) {
            throw new NotFoundException("Não foi possível encontrar o artista com o ID informado");
        }
        artistaRepository.deleteById(id);
    }

    private ArtistaDTO toDTO(Artista artista) {
        ArtistaDTO dto = new ArtistaDTO();
        dto.setId(artista.getId());
        dto.setNome(artista.getNome());
        return dto;
    }

    private Artista toEntity(ArtistaCreateDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.getNome());
        return artista;
    }
}
