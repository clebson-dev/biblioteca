package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.EditoraRequestDTO;
import com.api.biblioteca.dto.EditoraResponseDTO;
import com.api.biblioteca.model.Editora;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EditoraMapper {
    Editora toEntity(EditoraRequestDTO dto);
    EditoraResponseDTO toResponseDTO(Editora entity);
}