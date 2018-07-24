package br.com.bandtec.boletimapi.entity;

import br.com.bandtec.boletimapi.enums.Resultado;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author José Yoshiriro
 */
@Entity
public class Boletim {

    private final double FREQUENCIA_MINIMA = 75;

    private final double MEDIA_MINIMA = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Length(min = 4, max = 50)
    @NotNull
    private String aluno;

    @Column(nullable = false, length = 50)
    @Length(min = 4, max = 50)
    @NotNull
    private String disciplina;

    @Column
    @Min(0) @Max(10)
    private double nota1;

    @Column
    @Min(0) @Max(10)
    private double nota2;

    @Column
    @Min(0) @Max(100)
    private double frequencia;

    @Column(nullable = false, length = 20)
    @Length(min = 3, max = 20)
    @NotNull
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Transient
    public double getMedia() {
        return (this.nota1 + this.nota2) / 2;
    }

    @Transient
    public String getResultado() {
        boolean aprovadoMedia = this.getMedia() >= MEDIA_MINIMA;
        boolean aprovadoFrequencia = this.frequencia >= FREQUENCIA_MINIMA;

        if (!aprovadoMedia && !aprovadoFrequencia) {
            return Resultado.REPROVADO_GERAL.getDescricao();
        }

        if (!aprovadoMedia) {
            return Resultado.REPROVADO_NOTA.getDescricao();
        }

        if (!aprovadoFrequencia) {
            return Resultado.REPROVADO_FREQUENCIA.getDescricao();
        }

        return Resultado.APROVADO.getDescricao();
    }

}
