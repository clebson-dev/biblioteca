package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.LivroRequestDTO;
import com.api.biblioteca.dto.LivroResponseDTO;
import com.api.biblioteca.model.Autor;
import com.api.biblioteca.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LivroMapper {

    @Mapping(target = "categoria.id", source = "idCategoria")
    @Mapping(target = "editora.id", source = "idEditora")
    @Mapping(target = "autores", source = "idAutores")
    Livro toEntity(LivroRequestDTO requestDTO);

    LivroResponseDTO toResponseDTO(Livro livro);

    default Autor mapAutorFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setId(id);
        return autor;
    }
}
