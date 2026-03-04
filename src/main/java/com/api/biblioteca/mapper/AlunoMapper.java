package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.AlunoRequestDTO;
import com.api.biblioteca.dto.AlunoResponseDTO;
import com.api.biblioteca.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlunoMapper {
    Aluno toEntity(AlunoRequestDTO dto);
    AlunoResponseDTO toResponseDTO(Aluno entity);
}