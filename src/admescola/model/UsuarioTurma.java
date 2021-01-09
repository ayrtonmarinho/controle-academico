/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.model;

/**
 *
 * @author iTuhh Z
 */
public class UsuarioTurma {
    private Usuario aluno;
    private Disciplina disciplina;
    private Turma turma;

    public UsuarioTurma(Usuario aluno, Disciplina disciplina, Turma turma) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.turma = turma;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    
}
