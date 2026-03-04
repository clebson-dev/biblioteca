package com.api.biblioteca.service;

import com.api.biblioteca.dto.EditoraRequestDTO;
import com.api.biblioteca.dto.EditoraResponseDTO;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.EditoraMapper;
import com.api.biblioteca.model.Editora;
import com.api.biblioteca.repository.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EditoraService {
    private final EditoraRepository repository;
    private final EditoraMapper mapper;

    @Transactional(readOnly = true)
    public List<EditoraResponseDTO> listarTodas() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public EditoraResponseDTO salvar(EditoraRequestDTO dto) {
        Editora editora = repository.save(mapper.toEntity(dto));
        return mapper.toResponseDTO(editora);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Editora não encontrada.");
        repository.deleteById(id);
    }
}