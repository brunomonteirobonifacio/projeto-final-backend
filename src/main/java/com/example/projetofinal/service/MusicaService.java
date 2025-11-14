package com.example.projetofinal.service;

import com.example.projetofinal.dto.MusicaCreateDTO;
import com.example.projetofinal.dto.MusicaDTO;
import com.example.projetofinal.dto.MusicaUpdateDTO;
import com.example.projetofinal.exception.NotFoundException;
import com.example.projetofinal.model.Artista;
import com.example.projetofinal.model.Genero;
import com.example.projetofinal.model.Musica;
import com.example.projetofinal.repository.ArtistaRepository;
import com.example.projetofinal.repository.GeneroRepository;
import com.example.projetofinal.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    public Page<MusicaDTO> list(String nomeFiltro, Pageable pageable) {
        Page<Musica> musicasPage = musicaRepository.findByNomeContainingIgnoreCase(nomeFiltro, pageable);

        return new PageImpl<>(
                musicasPage.getContent()
                        .stream()
                        .map(this::toDTO)
                        .toList(),
                musicasPage.getPageable(),
                musicasPage.getTotalElements()
        );
    }

    public MusicaDTO findById(UUID id) {
        return musicaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar a música com o ID informado"));
    }

    public MusicaDTO create(MusicaCreateDTO musicaDTO) {
        Musica musica = toEntity(musicaDTO);
        musica = musicaRepository.save(musica);

        return toDTO(musica);
    }

    private MusicaDTO toDTO(Musica musica) {
        MusicaDTO dto = new MusicaDTO();
        dto.setId(musica.getId());
        dto.setNome(musica.getNome());
        dto.setDuracaoEmSegundos(musica.getDuracaoEmSegundos());

        return dto;
    }

    public MusicaDTO update(UUID id, MusicaUpdateDTO dto) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar a música com o ID informado"));

        musica.setDuracaoEmSegundos(dto.getDuracaoEmSegundos());

        if (!musica.getGenero().getId().equals(dto.getGeneroId())) {
            Genero genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o gênero com o ID informado"));

            musica.setGenero(genero);
        }

        if (!musica.getArtista().getId().equals(dto.getArtistaId())) {
            Artista artista = artistaRepository.findById(dto.getArtistaId())
                    .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o artista com o ID informado"));

            musica.setArtista(artista);
        }

        Musica musicaUpdated = musicaRepository.save(musica);
        return toDTO(musicaUpdated);
    }

    private Musica toEntity(MusicaCreateDTO dto) {
        Musica musica = new Musica();
        musica.setNome(dto.getNome());
        musica.setDuracaoEmSegundos(dto.getDuracaoEmSegundos());

        Artista artista = artistaRepository.findById(dto.getArtistaId())
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o artista com o ID informado"));

        musica.setArtista(artista);

        Genero genero = generoRepository.findById(dto.getGeneroId())
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o gênero com o ID informado"));

        musica.setGenero(genero);

        return musica;
    }
}
