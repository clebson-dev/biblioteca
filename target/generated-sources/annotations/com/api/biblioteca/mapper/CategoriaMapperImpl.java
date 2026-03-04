package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.CategoriaRequestDTO;
import com.api.biblioteca.dto.CategoriaResponseDTO;
import com.api.biblioteca.model.Categoria;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T00:00:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public Categoria toEntity(CategoriaRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setCategoria( requestDTO.getCategoria() );
        categoria.setDescricao( requestDTO.getDescricao() );

        return categoria;
    }

    @Override
    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setId( categoria.getId() );
        categoriaResponseDTO.setCategoria( categoria.getCategoria() );
        categoriaResponseDTO.setDescricao( categoria.getDescricao() );

        return categoriaResponseDTO;
    }
}
