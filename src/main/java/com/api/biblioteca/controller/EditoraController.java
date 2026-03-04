package com.api.biblioteca.controller;

import com.api.biblioteca.dto.EditoraRequestDTO;
import com.api.biblioteca.dto.EditoraResponseDTO;
import com.api.biblioteca.service.EditoraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoras")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EditoraController {
    private final EditoraService service;

    @GetMapping
    public ResponseEntity<List<EditoraResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<EditoraResponseDTO> salvar(@Valid @RequestBody EditoraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}