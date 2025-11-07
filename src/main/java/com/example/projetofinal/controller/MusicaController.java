package com.example.projetofinal.controller;

import com.example.projetofinal.dto.MusicaCreateDTO;
import com.example.projetofinal.dto.MusicaDTO;
import com.example.projetofinal.dto.MusicaUpdateDTO;
import com.example.projetofinal.model.Musica;
import com.example.projetofinal.service.MusicaService;
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
@RequestMapping("/musica")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Musica> list(
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

        return musicaService.list(nomeFiltro, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicaDTO findById(@RequestParam UUID id) {
        return musicaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicaDTO save(@RequestBody @Valid MusicaCreateDTO dto) {
        return musicaService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MusicaDTO update(@RequestParam UUID id, @RequestBody @Valid MusicaUpdateDTO dto) {
        return musicaService.update(id, dto);
    }
}
