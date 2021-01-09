/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.scene.control.Button;

/**
 *
 * @author iTuhh Z
 */
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private final String cpf;
    private String telefone;
    private String email;
    private String rg;
    private String cidade;
    private String bairro;
    private String uf;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private LocalDate nascimento;
    private Turma[] turmas;
    private String sexo;
    

    public Usuario(String nome, String cpf, String telefone, String email, String cidade, String bairro, String uf, String rua, String numero, String complemento, String cep, LocalDate nascimento, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.turmas = new Turma[10];
       
    }

    //Construtor com RG
    public Usuario(String nome, String cpf, String telefone, String email, String rg, String cidade, String bairro, String uf, String rua, String numero, String complemento, String cep, LocalDate nascimento, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.turmas = new Turma[10];
        
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {
        return rg;
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Turma[] getTurmas() {
        return turmas;
    }

    public void setTurmas(Turma[] turmas) {
        this.turmas = turmas;
    }

}
