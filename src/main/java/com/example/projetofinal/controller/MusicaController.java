package com.example.projetofinal.controller;

import com.example.projetofinal.dto.musica.MusicaCreateDTO;
import com.example.projetofinal.dto.musica.MusicaDTO;
import com.example.projetofinal.dto.musica.MusicaUpdateDTO;
import com.example.projetofinal.service.MusicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<MusicaDTO> list(
            @PageableDefault(sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nomeFiltro
    ) {
        return musicaService.list(nomeFiltro, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MusicaDTO findById(@PathVariable UUID id) {
        return musicaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicaDTO save(@RequestBody @Valid MusicaCreateDTO dto) {
        return musicaService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MusicaDTO update(@PathVariable UUID id, @RequestBody @Valid MusicaUpdateDTO dto) {
        return musicaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        musicaService.delete(id);
    }
}
