package com.api.biblioteca.service;

import com.api.biblioteca.dto.UsuarioDTOs;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.UsuarioMapper;
import com.api.biblioteca.model.Usuario;
import com.api.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder; // Injetado automaticamente pelo Lombok

    @Transactional(readOnly = true)
    public List<UsuarioDTOs.Response> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTOs.Response salvar(UsuarioDTOs.Request dto) {
        // Encripta a senha ANTES de converter para Entidade e salvar na base de dados
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario usuario = mapper.toEntity(dto);
        usuario = repository.save(usuario);

        return mapper.toResponseDTO(usuario);
    }
}