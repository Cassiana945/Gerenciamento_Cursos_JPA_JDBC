package com.cassi.AtividadeJPA.JpaRepository;

import com.cassi.AtividadeJPA.JpaModel.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoJpaRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE UPPER(c.titulo) LIKE UPPER(CONCAT('%', :titulo, '%'))")
    List<Curso> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    List<Curso> findByDuracaoHorasBetween(Double min, Double max);

    List<Curso> findByInstrutorId(Long instrutorId);

    @Query("SELECT c FROM Curso c JOIN FETCH c.instrutor WHERE c.instrutor.id = :instrutorId AND c.duracaoHoras >= :minDuracao")
    List<Curso> findByInstrutorAndMinDuracao(@Param("instrutorId") Long instrutorId,
                                             @Param("minDuracao") Double minDuracao);

    @Query("SELECT c FROM Curso c JOIN FETCH c.instrutor")
    List<Curso> findAllWithInstrutores();
}
