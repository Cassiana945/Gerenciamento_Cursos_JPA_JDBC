package com.cassi.AtividadeJPA.JpaModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "duracao_horas", nullable = false)
    @Positive
    private Double duracaoHoras;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Instrutor instrutor;


    public Curso() {
    }


    public Curso(Long id, String titulo, Double duracaoHoras, Instrutor instrutor) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoHoras = duracaoHoras;
        this.instrutor = instrutor;
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

    public Instrutor getInstrutor() { return instrutor; }
    public void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }
}
