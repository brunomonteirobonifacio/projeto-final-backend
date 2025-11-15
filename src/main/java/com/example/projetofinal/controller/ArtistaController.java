package com.example.projetofinal.controller;

import com.example.projetofinal.dto.artista.ArtistaCreateDTO;
import com.example.projetofinal.dto.artista.ArtistaDTO;
import com.example.projetofinal.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaDTO create(@RequestBody @Valid ArtistaCreateDTO dto) {
        return artistaService.create(dto);
    }

    @GetMapping
    public Page<ArtistaDTO> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String nomeFiltro
    ) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return artistaService.list(nomeFiltro, pageable);
    }

    @GetMapping("/{id}")
    public ArtistaDTO findById(@PathVariable UUID id) {
        return artistaService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ArtistaDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid ArtistaDTO dto
    ) {
        return artistaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        artistaService.delete(id);
    }
}
