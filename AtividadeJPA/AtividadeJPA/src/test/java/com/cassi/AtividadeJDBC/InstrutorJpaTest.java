package com.cassi.AtividadeJDBC;

import com.cassi.AtividadeJPA.JpaModel.*;
import com.cassi.AtividadeJPA.JpaRepository.CursoJpaRepository;
import com.cassi.AtividadeJPA.JpaRepository.InstrutorJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class InstrutorJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InstrutorJpaRepository instrutorRepository;

    @Autowired
    private CursoJpaRepository cursoRepository;

    @Test
    void SalvarInstrutor() {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Maria Silva");
        instrutor.setEmail("maria@email.com");

        Instrutor instrutorSalvo = instrutorRepository.save(instrutor);

        assertNotNull(instrutorSalvo.getId());
        assertEquals("Maria Silva", instrutorSalvo.getNome());
        assertEquals("maria@email.com", instrutorSalvo.getEmail());
    }

    @Test
    void LancarExcecaoQuandoEmailDuplicado() {
        instrutorRepository.save(new Instrutor("João Carlos", "joao@email.com", null));

        Instrutor instrutor2 = new Instrutor();
        instrutor2.setNome("João Almeida");
        instrutor2.setEmail("joao@email.com");

        assertThrows(DataIntegrityViolationException.class, () -> {
            instrutorRepository.saveAndFlush(instrutor2);
        });
    }

    @Test
    @Transactional
    void CarregarCursosDoInstrutorLazy() {

        Instrutor instrutor = new Instrutor();
        instrutor.setNome("Ana");
        instrutor.setEmail("ana@email.com");
        instrutor = instrutorRepository.save(instrutor);

        Curso curso1 = new Curso();
        curso1.setTitulo("Curso 1");
        curso1.setDuracaoHoras(20.0);
        curso1.setInstrutor(instrutor);
        cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setTitulo("Curso 2");
        curso2.setDuracaoHoras(30.0);
        curso2.setInstrutor(instrutor);
        cursoRepository.save(curso2);

        entityManager.flush();
        entityManager.clear();

        Instrutor instrutorDoBanco = instrutorRepository.findById(instrutor.getId()).orElseThrow();


        assertFalse(instrutorDoBanco.getCursos().isEmpty());
        assertEquals(2, instrutorDoBanco.getCursos().size());
    }

    @Test
    void BuscarInstrutorPorId() {
        Instrutor instrutor = instrutorRepository.save(new Instrutor("Carlos", "carlos@email.com", null));

        Instrutor encontrado = instrutorRepository.findById(instrutor.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Carlos", encontrado.getNome());
    }

    @Test
    void ListarTodosInstrutores() {
        instrutorRepository.save(new Instrutor("Instrutor 1", "instrutor1@email.com", null));
        instrutorRepository.save(new Instrutor("Instrutor 2", "instrutor2@email.com", null));

        List<Instrutor> instrutores = instrutorRepository.findAll();

        assertEquals(2, instrutores.size());
    }
}


