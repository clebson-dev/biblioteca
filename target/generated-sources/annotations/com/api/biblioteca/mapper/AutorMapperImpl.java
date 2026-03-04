package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.AutorRequestDTO;
import com.api.biblioteca.dto.AutorResponseDTO;
import com.api.biblioteca.model.Autor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T00:00:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setAutor( dto.getAutor() );
        autor.setPseudonimo( dto.getPseudonimo() );
        autor.setNacionalidade( dto.getNacionalidade() );
        autor.setEnderecoWeb( dto.getEnderecoWeb() );
        autor.setEmail( dto.getEmail() );
        autor.setTelefone( dto.getTelefone() );

        return autor;
    }

    @Override
    public AutorResponseDTO toResponseDTO(Autor entity) {
        if ( entity == null ) {
            return null;
        }

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO();

        autorResponseDTO.setId( entity.getId() );
        autorResponseDTO.setAutor( entity.getAutor() );

        return autorResponseDTO;
    }
}
