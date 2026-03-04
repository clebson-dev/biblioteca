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

    // Dizemos ao MapStruct como mapear os campos que têm nomes ou tipos diferentes
    @Mapping(target = "categoria.id", source = "idCategoria")
    @Mapping(target = "editora.id", source = "idEditora")
    @Mapping(target = "autores", source = "idAutores")
    Livro toEntity(LivroRequestDTO requestDTO);

    // Na resposta, os nomes coincidem (categoria -> CategoriaResponseDTO),
    // logo o MapStruct faz a magia automaticamente sem precisar de @Mapping.
    LivroResponseDTO toResponseDTO(Livro livro);

    // Método auxiliar (Default) para ensinar o MapStruct a converter
    // um Integer (da lista idAutores) num objeto Autor apenas com o ID.
    default Autor mapAutorFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setId(id);
        return autor;
    }
}
