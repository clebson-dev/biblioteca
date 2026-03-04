package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.CategoriaRequestDTO;
import com.api.biblioteca.dto.CategoriaResponseDTO;
import com.api.biblioteca.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

// Define que esta classe será injetada pelo Spring (como os Services)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    // Converte de DTO de Entrada para Entidade (Usado no POST/PUT)
    Categoria toEntity(CategoriaRequestDTO requestDTO);

    // Converte de Entidade para DTO de Saída (Usado no GET)
    CategoriaResponseDTO toResponseDTO(Categoria categoria);
}
