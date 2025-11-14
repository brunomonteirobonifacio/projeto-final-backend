package com.example.projetofinal.service;

import com.example.projetofinal.dto.GeneroDTO;
import com.example.projetofinal.exception.NotFoundException;
import com.example.projetofinal.model.Genero;
import com.example.projetofinal.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public Page<GeneroDTO> list(String nomeFiltro, Pageable pageable) {
        Page<Genero> generosPage = generoRepository.findByNomeContainingIgnoreCase(nomeFiltro, pageable);

        return new PageImpl<>(
                generosPage.getContent()
                        .stream()
                        .map(this::toDTO)
                        .toList(),
                generosPage.getPageable(),
                generosPage.getTotalElements()
        );
    }

    public GeneroDTO findById(UUID id) {
        return generoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new NotFoundException("Não foi possível encontrar o gênero com o ID informado")
                );
    }

    public GeneroDTO create(GeneroDTO dto) {
        Genero genero = toEntity(dto);
        genero = generoRepository.save(genero);
        return toDTO(genero);
    }

    public GeneroDTO update(UUID id, GeneroDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Não foi possível encontrar o gênero com o ID informado")
                );

        genero.setNome(dto.getNome());

        Genero generoUpdated = generoRepository.save(genero);
        return toDTO(generoUpdated);
    }

    public void delete(UUID id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Não foi possível encontrar o gênero com o ID informado")
                );

        generoRepository.delete(genero);
    }

    private GeneroDTO toDTO(Genero genero) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(genero.getId());
        dto.setNome(genero.getNome());
        return dto;
    }

    private Genero toEntity(GeneroDTO dto) {
        Genero genero = new Genero();
        genero.setNome(dto.getNome());
        return genero;
    }
}
