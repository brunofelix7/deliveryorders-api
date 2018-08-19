package com.nelioalves.cursomcapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "produtos")
public class Produto implements Serializable {
    /*
     * A serialização significa salvar o estado atual dos objetos em arquivos em formato binário
     * para o seu computador, sendo assim esse estado poderá ser recuperado posteriormente recriando
     * o objeto em memória assim como ele estava no momento da sua serialização.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    //	Do outro lado da associacao ja foram buscados os objetos, agora ele nao busca mais
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto() {

    }

    public Produto(Integer id, String nome, Double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    
    /*
     * Retorna meus pedidos com base na minha lista de Pedidos associados a ele
     * Metodos get sao serializados, por isso devem ser ignorados tambem
     * 
     */
    @JsonIgnore
    public List<Pedido> getPedidos() {
    	List<Pedido> lista = new ArrayList<>();
    	//	Para cada item de pedido x que existir na minha lista de itens, adiciono o Pedido associado a ele na minha lista
    	for(ItemPedido x : itens){
    		lista.add(x.getPedido());
    	}
    	return lista;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

    /*
     * Compara dois objetos pelo conteúdo, não pelo ponteiro de memória
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * Compara dois objetos pelo conteúdo, não pelo ponteiro de memória
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", categorias=" + categorias + ", itens="
				+ itens + "]";
	}

}
