package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import javafx.scene.image.ImageView;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author Geraldo
 */
@Entity
@Table(name = "Media")
public class Media implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column
    private String nome;
    private String descricao;
    private int ano;
    private int temporada;
    private int episodio;
    private Calendar duracao;
    private String categoria;
    private String diretor;
    private String protagonista;
    private int idade;
    private ImageView imagem;
    
    public Media(){
        
    }

    public Media(int id, String nome, String descricao, int ano, int temporada, int episodio, Calendar duracao, String categoria, String diretor, String protagonista, int idade, ImageView imagem) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getEpisodio() {
        return episodio;
    }

    public void setEpisodio(int episodio) {
        this.episodio = episodio;
    }

    public Calendar getDuracao() {
        return duracao;
    }

    public void setDuracao(Calendar duracao) {
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
    }
    
    
    
}
