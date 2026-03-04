package com.api.biblioteca.service;

import com.api.biblioteca.dto.AutorRequestDTO;
import com.api.biblioteca.dto.AutorResponseDTO;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.AutorMapper;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository repository;
    private final AutorMapper mapper;

    @Transactional(readOnly = true)
    public List<AutorResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public AutorResponseDTO salvar(AutorRequestDTO dto) {
        Autor autor = repository.save(mapper.toEntity(dto));
        return mapper.toResponseDTO(autor);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Autor não encontrado.");
        repository.deleteById(id);
    }
}