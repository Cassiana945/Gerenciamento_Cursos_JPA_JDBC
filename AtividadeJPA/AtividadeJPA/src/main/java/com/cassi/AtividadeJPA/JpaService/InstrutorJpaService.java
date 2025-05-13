package com.cassi.AtividadeJPA.JpaService;

import com.cassi.AtividadeJPA.JpaRepository.InstrutorJpaRepository;
import com.cassi.AtividadeJPA.JpaModel.Instrutor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorJpaService {

    @Autowired
    private InstrutorJpaRepository repository;

    public Instrutor salvar(Instrutor instrutor) {
        return repository.save(instrutor);
    }

    public List<Instrutor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Instrutor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Instrutor atualizar(Long id, Instrutor instrutor) {
        Instrutor existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrutor não encontrado"));
        existente.setNome(instrutor.getNome());
        existente.setEmail(instrutor.getEmail());
        return repository.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
