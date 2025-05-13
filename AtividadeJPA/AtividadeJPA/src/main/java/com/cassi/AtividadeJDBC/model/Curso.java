package com.cassi.AtividadeJDBC.model;


public class Curso {

    private Long id;
    private String titulo;
    private Double duracaoHoras;
    private Long instrutorId;


    public Curso() {
    }


    public Curso(Long id, String titulo, Double duracaoHoras, Long instrutorId) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoHoras = duracaoHoras;
        this.instrutorId = instrutorId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(Double duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public Long getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Long instrutorId) {
        this.instrutorId = instrutorId;
    }
}
