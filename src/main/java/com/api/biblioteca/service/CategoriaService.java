package com.api.biblioteca.service;

import com.api.biblioteca.dto.CategoriaRequestDTO;
import com.api.biblioteca.dto.CategoriaResponseDTO;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.CategoriaMapper;
import com.api.biblioteca.model.Categoria;
import com.api.biblioteca.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Injeta as dependências automaticamente (substitui o @Autowired)
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(Integer id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + id));
        return mapper.toResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO salvar(CategoriaRequestDTO requestDTO) {
        Categoria categoria = mapper.toEntity(requestDTO);
        categoria = repository.save(categoria);
        return mapper.toResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO atualizar(Integer id, CategoriaRequestDTO requestDTO) {
        // 1. Verifica se existe
        Categoria categoriaExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + id));

        // 2. Atualiza os dados
        categoriaExistente.setCategoria(requestDTO.getCategoria());
        categoriaExistente.setDescricao(requestDTO.getDescricao());

        // 3. Guarda e devolve o DTO
        categoriaExistente = repository.save(categoriaExistente);
        return mapper.toResponseDTO(categoriaExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
