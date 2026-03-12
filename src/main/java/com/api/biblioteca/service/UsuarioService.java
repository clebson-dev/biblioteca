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
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UsuarioDTOs.Response> listarTodos() {
        return repository.findAllByAtivoTrue().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTOs.Response salvar(UsuarioDTOs.Request dto) {
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario usuario = mapper.toEntity(dto);
        usuario = repository.save(usuario);

        return mapper.toResponseDTO(usuario);
    }

    @Transactional
    public UsuarioDTOs.Response atualizar(Integer id, UsuarioDTOs.Request dto) {
        Usuario usuarioExistente = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setUsuario(dto.getUsuario());

        usuarioExistente.setSenha(passwordEncoder.encode(dto.getSenha()));

        usuarioExistente = repository.save(usuarioExistente);
        return mapper.toResponseDTO(usuarioExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        Usuario usuario = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado com o ID: " + id));

        usuario.setAtivo(false);
        repository.save(usuario);
    }
}