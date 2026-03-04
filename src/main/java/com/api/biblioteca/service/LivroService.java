package com.api.biblioteca.service;

import com.api.biblioteca.dto.LivroRequestDTO;
import com.api.biblioteca.dto.LivroResponseDTO;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.mapper.LivroMapper;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.model.Categoria;
import com.api.biblioteca.model.Editora;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.repository.AutorRepository;
import com.api.biblioteca.repository.CategoriaRepository;
import com.api.biblioteca.repository.EditoraRepository;
import com.api.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;
    private final EditoraRepository editoraRepository;
    private final AutorRepository autorRepository;
    private final LivroMapper mapper;

    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorId(Integer id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com o ID: " + id));
        return mapper.toResponseDTO(livro);
    }

    @Transactional
    public LivroResponseDTO salvar(LivroRequestDTO requestDTO) {
        // 1. Validar se as chaves estrangeiras existem na base de dados
        Categoria categoria = categoriaRepository.findById(requestDTO.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada (ID: " + requestDTO.getIdCategoria() + ")"));

        Editora editora = editoraRepository.findById(requestDTO.getIdEditora())
                .orElseThrow(() -> new ResourceNotFoundException("Editora não encontrada (ID: " + requestDTO.getIdEditora() + ")"));

        List<Autor> autores = autorRepository.findAllById(requestDTO.getIdAutores());
        if (autores.isEmpty() || autores.size() != requestDTO.getIdAutores().size()) {
            throw new ResourceNotFoundException("Um ou mais Autores fornecidos não existem na base de dados.");
        }

        // 2. Converter DTO para Entidade e associar os objetos reais validados
        Livro livro = mapper.toEntity(requestDTO);
        livro.setCategoria(categoria);
        livro.setEditora(editora);
        livro.setAutores(autores);

        // 3. Gravar e devolver DTO de resposta
        livro = livroRepository.save(livro);
        return mapper.toResponseDTO(livro);
    }

    @Transactional
    public LivroResponseDTO atualizar(Integer id, LivroRequestDTO requestDTO) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com o ID: " + id));

        Categoria categoria = categoriaRepository.findById(requestDTO.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada (ID: " + requestDTO.getIdCategoria() + ")"));

        Editora editora = editoraRepository.findById(requestDTO.getIdEditora())
                .orElseThrow(() -> new ResourceNotFoundException("Editora não encontrada (ID: " + requestDTO.getIdEditora() + ")"));

        List<Autor> autores = autorRepository.findAllById(requestDTO.getIdAutores());
        if (autores.isEmpty() || autores.size() != requestDTO.getIdAutores().size()) {
            throw new ResourceNotFoundException("Um ou mais Autores fornecidos não existem na base de dados.");
        }

        livroExistente.setTitulo(requestDTO.getTitulo());
        livroExistente.setAnoPublicacao(requestDTO.getAnoPublicacao());
        livroExistente.setIsbn(requestDTO.getIsbn());
        livroExistente.setCategoria(categoria);
        livroExistente.setEditora(editora);
        livroExistente.setAutores(autores);

        livroExistente = livroRepository.save(livroExistente);
        return mapper.toResponseDTO(livroExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro não encontrado com o ID: " + id);
        }
        livroRepository.deleteById(id);
    }
}
