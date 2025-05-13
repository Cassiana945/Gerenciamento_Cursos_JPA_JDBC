package com.cassi.AtividadeJPA.JpaController;

import com.cassi.AtividadeJPA.JpaService.CursoJpaService;
import com.cassi.AtividadeJPA.JpaModel.Curso;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JPA - Cursos", description = "Endpoints usando implementação JPA")
@RestController
@RequestMapping("/api/jpa/cursos")
public class CursoJpaController {

    @Autowired
    private CursoJpaService service;
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody List<Curso> cursos){
        try {
            service.salvar(cursos);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return  ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/com-instrutores")
    public ResponseEntity<List<Curso>> listarComInstrutores() {
        return ResponseEntity.ok(service.listarComInstrutores());
    }

    @GetMapping("/id")
    public Curso buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);

    }

    @PutMapping("/id")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Curso curso){
        curso.setId(id);
        service.atualizar(curso);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Curso>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @GetMapping("/buscar/duracao")
    public ResponseEntity<List<Curso>> buscarPorDuracao(@RequestParam Double min,
                                                        @RequestParam Double max) {
        return ResponseEntity.ok(service.buscarPorDuracaoEntre(min, max));
    }

    @GetMapping("/buscar/instrutor")
    public ResponseEntity<List<Curso>> buscarPorInstrutor(@RequestParam Long instrutorId) {
        return ResponseEntity.ok(service.buscarPorInstrutorId(instrutorId));
    }

    @GetMapping("/buscar/filtro")
    public ResponseEntity<List<Curso>> buscarPorInstrutorEDuracao(@RequestParam Long instrutorId,
                                                                  @RequestParam Double minDuracao) {
        return ResponseEntity.ok(service.buscarPorInstrutorIdEDuracaoMinima(instrutorId, minDuracao));
    }
}
