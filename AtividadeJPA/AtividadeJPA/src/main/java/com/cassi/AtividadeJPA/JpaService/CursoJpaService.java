package com.cassi.AtividadeJPA.JpaService;

import com.cassi.AtividadeJPA.JpaRepository.CursoJpaRepository;
import com.cassi.AtividadeJPA.JpaModel.Curso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoJpaService {

    private static final Logger logger = LoggerFactory.getLogger(CursoJpaService.class);

    @Autowired
    private CursoJpaRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void salvar(List<Curso> cursos) throws Exception {
        logger.info("Iniciando o salvamento de cursos...");

        for (Curso curso : cursos) {
            logger.debug("Validando curso: {}", curso.getTitulo());

            if (curso.getDuracaoHoras() < 0) {
                logger.error("Erro: duração negativa no curso: {}", curso.getTitulo());
                throw new Exception("Duração negativa não é permitida para o curso: " + curso.getTitulo());
            }
        }

        try {
            repository.saveAll(cursos);
            logger.info("Cursos salvos com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao salvar cursos. Efetuando rollback...", e);
            throw e;
        }
    }

    public Curso buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public List<Curso> listarComInstrutores() {
        return repository.findAllWithInstrutores();
    }

    public void atualizar(Curso curso) {
        repository.save(curso);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public List<Curso> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Curso> buscarPorDuracaoEntre(Double min, Double max) {
        return repository.findByDuracaoHorasBetween(min, max);
    }

    public List<Curso> buscarPorInstrutorId(Long instrutorId) {
        return repository.findByInstrutorId(instrutorId);
    }

    public List<Curso> buscarPorInstrutorIdEDuracaoMinima(Long instrutorId, Double minDuracao) {
        return repository.findByInstrutorAndMinDuracao(instrutorId, minDuracao);
    }
}