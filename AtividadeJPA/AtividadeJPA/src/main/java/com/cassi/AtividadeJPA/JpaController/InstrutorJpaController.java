package com.cassi.AtividadeJPA.JpaController;

import com.cassi.AtividadeJPA.JpaService.InstrutorJpaService;
import com.cassi.AtividadeJPA.JpaModel.Instrutor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JPA - Instrutores", description = "Endpoints usando implementação JPA")
@RestController
@RequestMapping("/api/jpa/instrutores")
public class InstrutorJpaController {

    @Autowired
    private InstrutorJpaService service;

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
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Instrutor instrutor) {
        service.atualizar(id, instrutor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
