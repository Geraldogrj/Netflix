package br.ufrn.imd.netflix.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ufrn.imd.netflix.application.core.Model;
import javafx.scene.image.ImageView;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "media")
public class Media extends Model {

    private static final long serialVersionUID = 1L;
    
    @Column
    private String nome;
    @Column
    private String descricao;
    @Column
    private Integer ano;
    @Column
    private Integer temporada;
    @Column
    private Integer episodio;
    @Column
    private Integer duracao;
    @Column
    private String categoria;
    @Column
    private String diretor;
    @Column
    private String protagonista;
    @Column
    private Integer idade;
    @Column
    private String imagem;
    
    public Media(){
        
    }

    public Media(Integer id, String nome, String descricao, Integer ano, Integer temporada, Integer episodio, Integer duracao, String categoria, String diretor, String protagonista, Integer idade, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
        this.temporada = temporada;
        this.episodio = episodio;
        this.duracao = duracao;
        this.categoria = categoria;
        this.diretor = diretor;
        this.protagonista = protagonista;
        this.idade = idade;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Integer episodio) {
        this.episodio = episodio;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    
    
}
