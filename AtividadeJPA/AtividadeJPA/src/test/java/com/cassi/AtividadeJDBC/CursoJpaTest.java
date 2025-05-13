package com.cassi.AtividadeJDBC;

import com.cassi.AtividadeJPA.JpaModel.*;
import com.cassi.AtividadeJPA.JpaRepository.CursoJpaRepository;
import com.cassi.AtividadeJPA.JpaRepository.InstrutorJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CursoJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CursoJpaRepository cursoRepository;

    @Autowired
    private InstrutorJpaRepository instrutorRepository;

    private Instrutor instrutor;

    @BeforeEach
    void setUp() {
        instrutor = new Instrutor();
        instrutor.setNome("João Silva");
        instrutor.setEmail("joao@email.com");
        instrutor = instrutorRepository.save(instrutor);
    }

    @Test
    void salvarCurso() {
        Curso curso = new Curso();
        curso.setTitulo("Design");
        curso.setDuracaoHoras(40.0);
        curso.setInstrutor(instrutor);

        Curso cursoSalvo = cursoRepository.save(curso);

        assertNotNull(cursoSalvo.getId());
        assertEquals("Design", cursoSalvo.getTitulo());
        assertEquals(40.0, cursoSalvo.getDuracaoHoras());
        assertEquals(instrutor.getId(), cursoSalvo.getInstrutor().getId());
    }

    @Test
    void ExcecaoQuandoSalvarCursoSemInstrutor() {
        Curso curso = new Curso();
        curso.setTitulo("Curso sem instrutor");
        curso.setDuracaoHoras(10.0);

        assertThrows(DataIntegrityViolationException.class, () -> {
            cursoRepository.saveAndFlush(curso);
        });
    }

    @Test
    void BuscarCursoPorId() {
        Curso curso = new Curso(null, "Java Básico", 20.0, instrutor);
        curso = cursoRepository.save(curso);

        Curso encontrado = cursoRepository.findById(curso.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Java Básico", encontrado.getTitulo());
    }

    @Test
    void ListarTodosOsCursosComInstrutores() {
        cursoRepository.save(new Curso(null, "Alfaiataria", 20.0, instrutor));
        cursoRepository.save(new Curso(null, "Dev Jogos", 40.0, instrutor));

        List<Curso> cursos = cursoRepository.findAllWithInstrutores();

        assertEquals(2, cursos.size());
        assertNotNull(cursos.get(0).getInstrutor());
    }

    @Test
    void BuscarCursosPorTituloIgnoreCase() {
        cursoRepository.save(new Curso(null, "Ferramentas Visuais", 40.0, instrutor));
        cursoRepository.save(new Curso(null, "IA", 30.0, instrutor));

        List<Curso> cursos = cursoRepository.findByTituloContainingIgnoreCase("ferramentas");

        assertEquals(1, cursos.size());
        assertEquals("Ferramentas Visuais", cursos.get(0).getTitulo());
    }

    @Test
    void BuscarCursosPorFaixaDeDuracao() {
        cursoRepository.save(new Curso(null, "Curso 1", 20.0, instrutor));
        cursoRepository.save(new Curso(null, "Curso 2", 40.0, instrutor));
        cursoRepository.save(new Curso(null, "Curso 3", 60.0, instrutor));

        List<Curso> cursos = cursoRepository.findByDuracaoHorasBetween(30.0, 50.0);

        assertEquals(1, cursos.size());
        assertEquals("Curso 2", cursos.get(0).getTitulo());
    }

    @Test
    void BuscarCursosPorInstrutor() {
        Instrutor outroInstrutor = instrutorRepository.save(new Instrutor("Maria", "maria@email.com", null));

        cursoRepository.save(new Curso(null, "Curso 1", 20.0, instrutor));
        cursoRepository.save(new Curso(null, "Curso 2", 40.0, outroInstrutor));

        List<Curso> cursos = cursoRepository.findByInstrutorId(instrutor.getId());

        assertEquals(1, cursos.size());
        assertEquals("Curso 1", cursos.get(0).getTitulo());
    }

    @Test
    void BuscarCursosPorInstrutorEMinDuracao() {
        cursoRepository.save(new Curso(null, "Curso 1", 20.0, instrutor));
        cursoRepository.save(new Curso(null, "Curso 2", 40.0, instrutor));

        List<Curso> cursos = cursoRepository.findByInstrutorAndMinDuracao(instrutor.getId(), 30.0);

        assertEquals(1, cursos.size());
        assertEquals("Curso 2", cursos.get(0).getTitulo());
    }

}
