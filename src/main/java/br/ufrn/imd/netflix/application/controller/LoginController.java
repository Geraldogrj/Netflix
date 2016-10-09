package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

import javax.persistence.NoResultException;

import br.ufrn.imd.netflix.application.model.Usuario;
import br.ufrn.imd.netflix.core.Controller;
import br.ufrn.imd.netflix.core.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Geraldo e Roberto
 */
public class LoginController extends Controller {

    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private ImageView imgNetflix;
       
    @FXML
    public void logar(ActionEvent event){
    	
    	Dao<Usuario> dao = getDAO(Usuario.class);
    	
    	try{
    		dao.queryForOne("select u from Usuario u where u.login = ?0 and u.senha = ?1 ", txtLogin.getText(), txtSenha.getText());
    		abrirJanela("/fxml/MediaOverview.fxml");
    		fecharJanela(event);
    	}
    	catch (NoResultException e){
    		abrirAlertaInfo("Não foi possível Logar", "Usuário ou Senha não encontrados...");
    	} 
    	catch (IOException e) {
			abrirAlertaErro("Erro Inesperado!", "Não foi possível abrir a janela de Mídia...");
		}
    	
    }
             
}
