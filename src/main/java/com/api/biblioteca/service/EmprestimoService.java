package com.api.biblioteca.service;

import com.api.biblioteca.dto.EmprestimoRequestDTO;
import com.api.biblioteca.dto.EmprestimoResponseDTO;
import com.api.biblioteca.exception.RegraNegocioException;
import com.api.biblioteca.exception.ResourceNotFoundException;
import com.api.biblioteca.model.*;
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
        // 1. Validar utilizador e aluno
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Utilizador não encontrado."));
        Aluno aluno = alunoRepository.findById(request.getIdAluno())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));

        // 2. Validar se os livros existem
        List<Livro> livros = livroRepository.findAllById(request.getIdLivros());
        if (livros.size() != request.getIdLivros().size()) {
            throw new RegraNegocioException("Um ou mais livros solicitados não existem no acervo.");
        }

        // 3. Criar o cabeçalho do Empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setAluno(aluno);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(request.getDataDevolucaoPrevista());
        emprestimo = emprestimoRepository.save(emprestimo);

        // 4. Criar os itens do Empréstimo (A tabela ponte)
        Emprestimo finalEmprestimo = emprestimo;
        List<EmprestimoLivro> itens = livros.stream().map(livro -> {
            EmprestimoLivro item = new EmprestimoLivro();
            item.setEmprestimo(finalEmprestimo);
            item.setLivro(livro);
            item.setDataDevolucao(null); // Ainda não devolvido
            return emprestimoLivroRepository.save(item);
        }).collect(Collectors.toList());

        return mapearParaResponseDTO(emprestimo, itens);
    }

    @Transactional
    public String devolverLivro(Integer idEmprestimo, Integer idLivro) {
        // 1. Localizar o item específico
        EmprestimoLivro item = emprestimoLivroRepository.findByEmprestimoIdAndLivroId(idEmprestimo, idLivro)
                .orElseThrow(() -> new ResourceNotFoundException("O livro especificado não pertence a este empréstimo."));

        // 2. Verificar se já não foi devolvido antes
        if (item.getDataDevolucao() != null) {
            throw new RegraNegocioException("Este livro já foi registado como devolvido anteriormente.");
        }

        // 3. Efetuar devolução
        item.setDataDevolucao(LocalDate.now());
        emprestimoLivroRepository.save(item);

        // 4. Regra de Negócio: Calcular Multa
        LocalDate prevista = item.getEmprestimo().getDataDevolucaoPrevista();
        if (LocalDate.now().isAfter(prevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(prevista, LocalDate.now());
            float valorPorDia = 2.50f; // Idealmente isto viria da tabela 'parametro'
            float valorTotalMulta = diasAtraso * valorPorDia;

            Multa multa = new Multa();
            multa.setEmprestimoLivro(item);
            multa.setValor(valorTotalMulta);
            multa.setDataPagamento(LocalDate.now()); // Assumindo pagamento no ato para simplificar
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

    // Mapeamento manual para termos maior controlo sobre as coleções complexas
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
                itemDTO.setStatus(item.getDataDevolucao().isAfter(emprestimo.getDataDevolucaoPrevista()) ? "DEVOLVIDO COM ATRASO" : "DEVOLVIDO");
            } else {
                itemDTO.setStatus(LocalDate.now().isAfter(emprestimo.getDataDevolucaoPrevista()) ? "EM ATRASO" : "PENDENTE");
            }
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItens(itensDTO);
        return dto;
    }
}