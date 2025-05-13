package com.cassi.AtividadeJDBC.service;

import com.cassi.AtividadeJDBC.model.Instrutor;
import com.cassi.AtividadeJDBC.repository.InstrutorJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorJdbcRepository repository;

    public void salvar(Instrutor instrutor) {
        repository.save(instrutor);
    }

    public Instrutor buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Instrutor> listarTodos() {
        return repository.findAll();
    }

    public void atualizar(Instrutor instrutor) {
        repository.update(instrutor);
    }

    public void deletar(Long id) {
        repository.delete(id);
    }
}
