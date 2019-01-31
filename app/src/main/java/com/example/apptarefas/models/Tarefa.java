package com.example.apptarefas.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Tarefa {


    @Id
    private long id;
    private String descricao, categoria;
    private int prioridade;
    private boolean realizada;


    public Tarefa(){

    }

    public Tarefa(String descricao, String categoria, int prioridade, boolean realizada) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.realizada = realizada;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }


}
