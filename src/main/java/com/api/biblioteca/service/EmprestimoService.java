package com.api.biblioteca.service;

import com.api.biblioteca.dto.EmprestimoRequestDTO;
import com.api.biblioteca.dto.EmprestimoResponseDTO;
import com.api.biblioteca.exception.RegraNegocioException;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.model.*;
import com.api.biblioteca.model.enums.StatusEmprestimo;
import com.api.biblioteca.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoLivroRepository emprestimoLivroRepository;
    private final MultaRepository multaRepository;
    private final LivroRepository livroRepository;
    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public EmprestimoResponseDTO realizarEmprestimo(EmprestimoRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado."));
        Aluno aluno = alunoRepository.findById(request.getIdAluno())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));

        List<Livro> livros = livroRepository.findAllById(request.getIdLivros());
        if (livros.size() != request.getIdLivros().size()) {
            throw new RegraNegocioException("Um ou mais livros solicitados não existem no acervo.");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setAluno(aluno);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(request.getDataDevolucaoPrevista());
        emprestimo = emprestimoRepository.save(emprestimo);

        Emprestimo finalEmprestimo = emprestimo;
        List<EmprestimoLivro> itens = livros.stream().map(livro -> {
            EmprestimoLivro item = new EmprestimoLivro();
            item.setEmprestimo(finalEmprestimo);
            item.setLivro(livro);
            item.setDataDevolucao(null);
            return emprestimoLivroRepository.save(item);
        }).collect(Collectors.toList());

        return mapearParaResponseDTO(emprestimo, itens);
    }

    @Transactional
    public String devolverLivro(Integer idEmprestimo, Integer idLivro) {

        EmprestimoLivro item = emprestimoLivroRepository.findByEmprestimoIdAndLivroId(idEmprestimo, idLivro)
                .orElseThrow(() -> new ResourceNotFoundException("O livro especificado não pertence a este empréstimo."));

        if (item.getDataDevolucao() != null) {
            throw new RegraNegocioException("Este livro já foi registado como devolvido anteriormente.");
        }

        item.setDataDevolucao(LocalDate.now());
        emprestimoLivroRepository.save(item);

        LocalDate prevista = item.getEmprestimo().getDataDevolucaoPrevista();
        if (LocalDate.now().isAfter(prevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(prevista, LocalDate.now());
            float valorPorDia = 2.50f;
            float valorTotalMulta = diasAtraso * valorPorDia;

            Multa multa = new Multa();
            multa.setEmprestimoLivro(item);
            multa.setValor(valorTotalMulta);
            multa.setDataPagamento(LocalDate.now());
            multaRepository.save(multa);

            return "Livro devolvido com " + diasAtraso + " dias de atraso. Multa gerada: €" + String.format("%.2f", valorTotalMulta);
        }

        return "Livro devolvido dentro do prazo com sucesso. Sem multas.";
    }

    @Transactional(readOnly = true)
    public EmprestimoResponseDTO buscarPorId(Integer id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado."));
        List<EmprestimoLivro> itens = emprestimoLivroRepository.findByEmprestimoId(id);

        return mapearParaResponseDTO(emprestimo, itens);
    }

    private EmprestimoResponseDTO mapearParaResponseDTO(Emprestimo emprestimo, List<EmprestimoLivro> itensLivro) {
        EmprestimoResponseDTO dto = new EmprestimoResponseDTO();
        dto.setId(emprestimo.getId());
        dto.setNomeUsuario(emprestimo.getUsuario().getNome());
        dto.setNomeAluno(emprestimo.getAluno().getNome());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataDevolucaoPrevista(emprestimo.getDataDevolucaoPrevista());

        List<EmprestimoResponseDTO.ItemEmprestimoDTO> itensDTO = itensLivro.stream().map(item -> {
            EmprestimoResponseDTO.ItemEmprestimoDTO itemDTO = new EmprestimoResponseDTO.ItemEmprestimoDTO();
            itemDTO.setIdLivro(item.getLivro().getId());
            itemDTO.setTituloLivro(item.getLivro().getTitulo());
            itemDTO.setDataDevolucao(item.getDataDevolucao());

            if (item.getDataDevolucao() != null) {
                itemDTO.setStatus(item.getDataDevolucao().isAfter(emprestimo.getDataDevolucaoPrevista())
                        ? StatusEmprestimo.DEVOLVIDO_COM_ATRASO
                        : StatusEmprestimo.DEVOLVIDO);
            } else {
                itemDTO.setStatus(LocalDate.now().isAfter(emprestimo.getDataDevolucaoPrevista())
                        ? StatusEmprestimo.EM_ATRASO
                        : StatusEmprestimo.PENDENTE);
            }
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItens(itensDTO);
        return dto;
    }
}