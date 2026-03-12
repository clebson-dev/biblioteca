package com.api.biblioteca.controller;

import com.api.biblioteca.dto.EmprestimoRequestDTO;
import com.api.biblioteca.dto.EmprestimoResponseDTO;
import com.api.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmprestimoController {

    private final EmprestimoService service;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(@Valid @RequestBody EmprestimoRequestDTO requestDTO) {
        EmprestimoResponseDTO novoEmprestimo = service.realizarEmprestimo(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{idEmprestimo}/devolucao/{idLivro}")
    public ResponseEntity<Map<String, String>> devolverLivro(
            @PathVariable Integer idEmprestimo,
            @PathVariable Integer idLivro) {

        String mensagem = service.devolverLivro(idEmprestimo, idLivro);
        return ResponseEntity.ok(Map.of("mensagem", mensagem));
    }
}