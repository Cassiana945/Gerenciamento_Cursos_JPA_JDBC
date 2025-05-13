package com.cassi.AtividadeJDBC.controller;

import com.cassi.AtividadeJDBC.model.Curso;
import com.cassi.AtividadeJDBC.service.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JDBC - Cursos", description = "Endpoints usando implementação JDBC")
@RestController
@RequestMapping("/api/jdbc/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody List<Curso> cursos) {
        try {
            service.salvar(cursos);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        service.atualizar(curso);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cursos/instrutor/{instrutorId}")
    public ResponseEntity<List<Curso>> buscarCursosPorInstrutor(@PathVariable Long instrutorId) {
        List<Curso> cursos = service.buscarCursosPorInstrutor(instrutorId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Curso>> buscarComFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Double minHoras,
            @RequestParam(required = false) Double maxHoras,
            @RequestParam(required = false) Long instrutorId) {

        List<Curso> cursos = service.buscarComFiltros(titulo, minHoras, maxHoras, instrutorId);
        return ResponseEntity.ok(cursos);
    }

}
