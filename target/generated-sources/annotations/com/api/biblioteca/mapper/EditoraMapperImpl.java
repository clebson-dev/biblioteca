package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.EditoraRequestDTO;
import com.api.biblioteca.dto.EditoraResponseDTO;
import com.api.biblioteca.model.Editora;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T00:00:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class EditoraMapperImpl implements EditoraMapper {

    @Override
    public Editora toEntity(EditoraRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Editora editora = new Editora();

        editora.setEditora( dto.getEditora() );
        editora.setCnpj( dto.getCnpj() );
        editora.setEmail( dto.getEmail() );
        editora.setTelefone( dto.getTelefone() );
        editora.setCep( dto.getCep() );
        editora.setEstado( dto.getEstado() );
        editora.setCidade( dto.getCidade() );
        editora.setBairro( dto.getBairro() );
        editora.setEndereco( dto.getEndereco() );
        editora.setNacionalidade( dto.getNacionalidade() );
        editora.setEnderecoWeb( dto.getEnderecoWeb() );

        return editora;
    }

    @Override
    public EditoraResponseDTO toResponseDTO(Editora entity) {
        if ( entity == null ) {
            return null;
        }

        EditoraResponseDTO editoraResponseDTO = new EditoraResponseDTO();

        editoraResponseDTO.setId( entity.getId() );
        editoraResponseDTO.setEditora( entity.getEditora() );

        return editoraResponseDTO;
    }
}
