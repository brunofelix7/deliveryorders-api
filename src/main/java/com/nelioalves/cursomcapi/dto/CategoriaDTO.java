package com.nelioalves.cursomcapi.dto;

import com.nelioalves.cursomcapi.domain.Categoria;
import java.io.Serializable;

/**
 * Classe de exibicao de dados personalizados para o meu usuario.
 * Utilizado para exibir os dados que eu quero trafegar em determinadas operacoes
 */
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO() {

    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
