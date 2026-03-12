package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.CategoriaRequestDTO;
import com.api.biblioteca.dto.CategoriaResponseDTO;
import com.api.biblioteca.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    Categoria toEntity(CategoriaRequestDTO requestDTO);

    CategoriaResponseDTO toResponseDTO(Categoria categoria);
}
