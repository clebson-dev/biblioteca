package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.UsuarioDTOs;
import com.api.biblioteca.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTOs.Request dto);
    UsuarioDTOs.Response toResponseDTO(Usuario entity);
}