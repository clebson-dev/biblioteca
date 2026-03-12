package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.AutorResponseDTO;
import com.api.biblioteca.dto.CategoriaResponseDTO;
import com.api.biblioteca.dto.EditoraResponseDTO;
import com.api.biblioteca.dto.LivroRequestDTO;
import com.api.biblioteca.dto.LivroResponseDTO;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.model.Categoria;
import com.api.biblioteca.model.Editora;
import com.api.biblioteca.model.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-05T02:46:32-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Override
    public Livro toEntity(LivroRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setCategoria( livroRequestDTOToCategoria( requestDTO ) );
        livro.setEditora( livroRequestDTOToEditora( requestDTO ) );
        livro.setAutores( integerListToAutorList( requestDTO.getIdAutores() ) );
        livro.setTitulo( requestDTO.getTitulo() );
        livro.setAnoPublicacao( requestDTO.getAnoPublicacao() );
        livro.setIsbn( requestDTO.getIsbn() );

        return livro;
    }

    @Override
    public LivroResponseDTO toResponseDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO();

        livroResponseDTO.setId( livro.getId() );
        livroResponseDTO.setTitulo( livro.getTitulo() );
        livroResponseDTO.setAnoPublicacao( livro.getAnoPublicacao() );
        livroResponseDTO.setIsbn( livro.getIsbn() );
        livroResponseDTO.setCategoria( categoriaToCategoriaResponseDTO( livro.getCategoria() ) );
        livroResponseDTO.setEditora( editoraToEditoraResponseDTO( livro.getEditora() ) );
        livroResponseDTO.setAutores( autorListToAutorResponseDTOList( livro.getAutores() ) );

        return livroResponseDTO;
    }

    protected Categoria livroRequestDTOToCategoria(LivroRequestDTO livroRequestDTO) {
        if ( livroRequestDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( livroRequestDTO.getIdCategoria() );

        return categoria;
    }

    protected Editora livroRequestDTOToEditora(LivroRequestDTO livroRequestDTO) {
        if ( livroRequestDTO == null ) {
            return null;
        }

        Editora editora = new Editora();

        editora.setId( livroRequestDTO.getIdEditora() );

        return editora;
    }

    protected List<Autor> integerListToAutorList(List<Integer> list) {
        if ( list == null ) {
            return null;
        }

        List<Autor> list1 = new ArrayList<Autor>( list.size() );
        for ( Integer integer : list ) {
            list1.add( mapAutorFromId( integer ) );
        }

        return list1;
    }

    protected CategoriaResponseDTO categoriaToCategoriaResponseDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setId( categoria.getId() );
        categoriaResponseDTO.setCategoria( categoria.getCategoria() );
        categoriaResponseDTO.setDescricao( categoria.getDescricao() );

        return categoriaResponseDTO;
    }

    protected EditoraResponseDTO editoraToEditoraResponseDTO(Editora editora) {
        if ( editora == null ) {
            return null;
        }

        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO();

        editoraResponseDTO.setId( editora.getId() );
        editoraResponseDTO.setEditora( editora.getEditora() );

        return editoraResponseDTO;
    }

    protected AutorResponseDTO autorToAutorResponseDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO();

        autorResponseDTO.setId( autor.getId() );
        autorResponseDTO.setAutor( autor.getAutor() );

        return autorResponseDTO;
    }

    protected List<AutorResponseDTO> autorListToAutorResponseDTOList(List<Autor> list) {
        if ( list == null ) {
            return null;
        }

        List<AutorResponseDTO> list1 = new ArrayList<AutorResponseDTO>( list.size() );
        for ( Autor autor : list ) {
            list1.add( autorToAutorResponseDTO( autor ) );
        }

        return list1;
    }
}
