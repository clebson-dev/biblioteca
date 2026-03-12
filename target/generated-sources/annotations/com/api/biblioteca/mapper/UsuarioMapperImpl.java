package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.UsuarioDTOs;
import com.api.biblioteca.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-05T02:46:32-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDTOs.Request dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.getNome() );
        usuario.setUsuario( dto.getUsuario() );
        usuario.setSenha( dto.getSenha() );
        usuario.setPerfil( dto.getPerfil() );

        return usuario;
    }

    @Override
    public UsuarioDTOs.Response toResponseDTO(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTOs.Response response = new UsuarioDTOs.Response();

        response.setId( entity.getId() );
        response.setNome( entity.getNome() );
        response.setUsuario( entity.getUsuario() );
        response.setPerfil( entity.getPerfil() );
        response.setAtivo( entity.getAtivo() );

        return response;
    }
}
