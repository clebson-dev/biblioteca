package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.AlunoRequestDTO;
import com.api.biblioteca.dto.AlunoResponseDTO;
import com.api.biblioteca.model.Aluno;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T00:00:49-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class AlunoMapperImpl implements AlunoMapper {

    @Override
    public Aluno toEntity(AlunoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Aluno aluno = new Aluno();

        aluno.setNome( dto.getNome() );
        aluno.setCpf( dto.getCpf() );
        aluno.setTelefone( dto.getTelefone() );
        aluno.setEmail( dto.getEmail() );
        aluno.setCep( dto.getCep() );
        aluno.setEstado( dto.getEstado() );
        aluno.setCidade( dto.getCidade() );
        aluno.setEndereco( dto.getEndereco() );
        aluno.setBairro( dto.getBairro() );

        return aluno;
    }

    @Override
    public AlunoResponseDTO toResponseDTO(Aluno entity) {
        if ( entity == null ) {
            return null;
        }

        AlunoResponseDTO alunoResponseDTO = new AlunoResponseDTO();

        alunoResponseDTO.setId( entity.getId() );
        alunoResponseDTO.setNome( entity.getNome() );
        alunoResponseDTO.setCpf( entity.getCpf() );
        alunoResponseDTO.setTelefone( entity.getTelefone() );
        alunoResponseDTO.setEmail( entity.getEmail() );
        alunoResponseDTO.setCep( entity.getCep() );
        alunoResponseDTO.setEstado( entity.getEstado() );
        alunoResponseDTO.setCidade( entity.getCidade() );
        alunoResponseDTO.setEndereco( entity.getEndereco() );
        alunoResponseDTO.setBairro( entity.getBairro() );

        return alunoResponseDTO;
    }
}
