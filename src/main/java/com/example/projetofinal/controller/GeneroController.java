package com.example.projetofinal.controller;

import com.example.projetofinal.dto.genero.GeneroCreateDTO;
import com.example.projetofinal.dto.genero.GeneroDTO;
import com.example.projetofinal.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<GeneroDTO> list(
            @PageableDefault(sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nomeFiltro
    ) {
        return generoService.list(nomeFiltro, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneroDTO findById(@PathVariable UUID id) {
        return generoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GeneroDTO save(@RequestBody @Valid GeneroCreateDTO dto) {
        return generoService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GeneroDTO update(@PathVariable UUID id, @RequestBody @Valid GeneroDTO dto) {
        return generoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        generoService.delete(id);
    }
}
