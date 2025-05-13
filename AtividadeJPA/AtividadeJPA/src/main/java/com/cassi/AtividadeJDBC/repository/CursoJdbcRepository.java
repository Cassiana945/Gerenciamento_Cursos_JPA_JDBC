package com.cassi.AtividadeJDBC.repository;

import com.cassi.AtividadeJDBC.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(List<Curso> cursos) {
        String sql = "INSERT INTO curso (titulo, duracao_horas, instrutor_id) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Curso curso = cursos.get(i);
                ps.setString(1, curso.getTitulo());
                ps.setDouble(2, curso.getDuracaoHoras());
                ps.setLong(3, curso.getInstrutorId());
            }

            @Override
            public int getBatchSize() {
                return cursos.size();
            }
        });
    }

    public Curso findById(Long id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToCurso, id);
    }

    public List<Curso> findAll() {
        String sql = "SELECT * FROM curso";
        return jdbcTemplate.query(sql, this::mapRowToCurso);
    }

    public void update(Curso curso) {
        String sql = "UPDATE curso SET titulo = ?, duracao_horas = ?, instrutor_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId(), curso.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Curso> buscarCursosPorInstrutor(Long instrutorId) {
        String sql = "SELECT c.* FROM curso c JOIN instrutor i ON c.instrutor_id = i.id WHERE i.id = ?";
        return jdbcTemplate.query(sql, new Object[]{instrutorId}, this::mapRowToCurso);
    }

    private Curso mapRowToCurso(ResultSet rs, int rowNum) throws SQLException {
        return new Curso(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getDouble("duracao_horas"),
                rs.getLong("instrutor_id")
        );
    }
    public List<Curso> buscarComFiltros(String titulo, Double minHoras, Double maxHoras, Long instrutorId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM curso WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (titulo != null && !titulo.isBlank()) {
            sql.append(" AND LOWER(titulo) LIKE ?");
            params.add("%" + titulo.toLowerCase() + "%");
        }
        if (minHoras != null) {
            sql.append(" AND duracao_horas >= ?");
            params.add(minHoras);
        }
        if (maxHoras != null) {
            sql.append(" AND duracao_horas <= ?");
            params.add(maxHoras);
        }
        if (instrutorId != null) {
            sql.append(" AND instrutor_id = ?");
            params.add(instrutorId);
        }

        return jdbcTemplate.query(sql.toString(), this::mapRowToCurso, params.toArray());
    }




}

