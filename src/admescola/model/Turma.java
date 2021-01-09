/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author iTuhh Z
 */
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String codigo;
    private Disciplina disciplina;
    private String horario;
    private final int qtdMaxima;
    private Usuario professor;
    private ArrayList<Usuario> alunos;
    private int qtdMatriculados;
    private String anoLetivo;

    public Turma(String codigo, Disciplina codigoDisciplina, String horario, String anoLetivo) {
        this.codigo = codigo;
        this.disciplina = codigoDisciplina;
        this.horario = horario;
        this.qtdMaxima = 30;
        this.qtdMatriculados = 0;
        this.anoLetivo = anoLetivo;
        this.alunos = new ArrayList();
    }

    public void qtdMatriculados() {
        this.alunos.size();
    }

    public String getCodigo() {
        return codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getCodigoDisciplina() {
        return disciplina.getCodigo();
    }

    public String getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(String anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getHorario() {
        return horario;
    }

    public int getQtdMaxima() {
        return qtdMaxima;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setQtdMatriculados(int qtdMatriculados) {
        this.qtdMatriculados = qtdMatriculados;
    }

    public int getQtdMatriculados() {
        return qtdMatriculados;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public String getCpfProfessor() {
        return professor.getCpf();
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public ArrayList<Usuario> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Usuario> alunos) {
        this.alunos = alunos;
    }

    public boolean isFull() {
        if (this.qtdMatriculados == this.qtdMaxima) {
            return true;
        }
        return false;
    }

    public String qtdMaxToString() {
        return Integer.toString(qtdMaxima);
    }

    public String qtdMatriculadosToString() {
        return Integer.toString(qtdMatriculados);
    }

}
