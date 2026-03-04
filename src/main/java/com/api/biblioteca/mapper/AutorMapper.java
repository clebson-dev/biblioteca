package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.AutorRequestDTO;
import com.api.biblioteca.dto.AutorResponseDTO;
import com.api.biblioteca.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutorMapper {
    Autor toEntity(AutorRequestDTO dto);
    AutorResponseDTO toResponseDTO(Autor entity);
}