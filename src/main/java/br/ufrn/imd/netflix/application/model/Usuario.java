package br.ufrn.imd.netflix.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufrn.imd.netflix.application.core.Model;

@Entity
@Table(name="usuario")
public class Usuario extends Model {

	private static final long serialVersionUID = 1L;

	@Column
	private String login;
	
	@Column
	private String senha;
        
	@Column
	private String nome;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
	    return nome;
	}
	
	public void setNome(String nome) {
	    this.nome = nome;
	}
	
	
	
}
