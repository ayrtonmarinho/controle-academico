/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.model;

import java.io.Serializable;
import javafx.scene.control.Button;

/**
 *
 * @author iTuhh Z
 */
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String codigo;
    private String nome;
    private String cargaHoraria;
    private String creditos;
    private String periodo;
    private boolean alocada;
    private String registrada;
   

    public Disciplina(String codigo, String nome, String cargaHoraria, String creditos, String periodo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.creditos = creditos;
        this.periodo = periodo;
        this.alocada = false;
        this.registrada = "Não";
        
        
    }

    public String getCodigo(){
        return codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public boolean isAlocada() {
        return alocada;
    }

    public void setAlocada(boolean alocada) {
        this.alocada = alocada;
        if(this.alocada==true){
            this.registrada = "Sim";
        }else{
            this.registrada = "Não";
        }
    }

    public String getRegistrada() {
        return registrada;
    }

    public void setRegistrada(String registrada) {
        this.registrada = registrada;
    }

   
   

}
