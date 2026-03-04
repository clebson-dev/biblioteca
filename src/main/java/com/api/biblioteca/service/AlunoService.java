package com.api.biblioteca.service;

import com.api.biblioteca.dto.AlunoRequestDTO;
import com.api.biblioteca.dto.AlunoResponseDTO;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.AlunoMapper;
import com.api.biblioteca.model.Aluno;
import com.api.biblioteca.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final AlunoMapper mapper;

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorId(Integer id) {
        return mapper.toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado.")));
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
        Aluno aluno = repository.save(mapper.toEntity(dto));
        return mapper.toResponseDTO(aluno);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Aluno não encontrado.");
        repository.deleteById(id);
    }
}