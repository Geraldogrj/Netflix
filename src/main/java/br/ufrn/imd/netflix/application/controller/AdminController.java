/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.model.Usuario;
import static java.lang.Integer.parseInt;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.event.spi.SaveOrUpdateEvent;

/**
 *
 * @author Geraldo e Roberto
 */
public class AdminController extends Controller {
    
    public static final String FXML_ADMIN = "/fxml/admin_view.fxml";
    	
	@FXML 
	private Label lblUsuarioLogado;
        @FXML
        private TextField idUser;
        @FXML
        private TextField nomeUser;
        @FXML
        private TextField login;
        @FXML
        private TextField senha;

	@Override
	public void onCreate(Bundle bundle) {
		Usuario usuario = (Usuario) bundle.get("usuario");
		lblUsuarioLogado.setText(usuario.getNome());
	}

    public Label getLblUsuarioLogado() {
        return lblUsuarioLogado;
    }

    public void setLblUsuarioLogado(Label lblUsuarioLogado) {
        this.lblUsuarioLogado = lblUsuarioLogado;
    }

    public TextField getIdUser() {
        return idUser;
    }

    public void setIdUser(TextField idUser) {
        this.idUser = idUser;
    }

    public TextField getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(TextField nomeUser) {
        this.nomeUser = nomeUser;
    }

    public TextField getLogin() {
        return login;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public TextField getSenha() {
        return senha;
    }

    public void setSenha(TextField senha) {
        this.senha = senha;
    }
        
    public void cadastrar(){
        Dao<Usuario> dao = getDAO(Usuario.class);
        
        Usuario usuario = new Usuario();
        usuario.setId(parseInt(idUser.getText()));
        usuario.setNome(nomeUser.getText());
        usuario.setLogin(login.getText());
        usuario.setSenha(senha.getText());
        
        dao.saveOrUpdate(usuario);
    }
   
}
