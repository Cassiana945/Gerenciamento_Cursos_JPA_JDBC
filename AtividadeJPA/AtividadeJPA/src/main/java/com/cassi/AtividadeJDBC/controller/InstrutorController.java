package com.cassi.AtividadeJDBC.controller;

import com.cassi.AtividadeJDBC.model.Instrutor;
import com.cassi.AtividadeJDBC.service.InstrutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JDBC - Instrutores", description = "Endpoints usando implementação JDBC")
@RestController
@RequestMapping("/api/jdbc/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Instrutor instrutor) {
        service.salvar(instrutor);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<Instrutor>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Instrutor instrutor) {
        instrutor.setId(id);
        service.atualizar(instrutor);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
