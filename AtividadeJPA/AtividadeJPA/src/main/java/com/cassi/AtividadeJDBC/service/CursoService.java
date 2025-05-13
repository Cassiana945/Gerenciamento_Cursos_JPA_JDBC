package com.cassi.AtividadeJDBC.service;

import com.cassi.AtividadeJDBC.model.Curso;
import com.cassi.AtividadeJDBC.repository.CursoJdbcRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {

    @Autowired
    private CursoJdbcRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void salvar(List<Curso> cursos) throws Exception {
        for (Curso curso : cursos) {
            if (curso.getDuracaoHoras() < 0) {
                throw new Exception("Duração negativa não é permitida para o curso: " + curso.getTitulo());
            }
        }
        repository.save(cursos);
    }


    public Curso buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public void atualizar(Curso curso) {
        repository.update(curso);
    }

    public void deletar(Long id) {
        repository.delete(id);
    }

    public List<Curso> buscarCursosPorInstrutor(Long instrutorId){
        return repository.buscarCursosPorInstrutor(instrutorId);
    }

    public List<Curso> buscarComFiltros(String titulo, Double minHoras, Double maxHoras, Long instrutorId) {
        return repository.buscarComFiltros(titulo, minHoras, maxHoras, instrutorId);
    }


}
