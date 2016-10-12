package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

import javax.persistence.NoResultException;

import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.core.Intent;
import br.ufrn.imd.netflix.application.model.Usuario;
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
    
    public static final String FXML_LOGIN = "/fxml/login_view.fxml";

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
    		Usuario usuario = dao.queryForOne("select u from Usuario u where u.login = ?0 and u.senha = ?1 ", 
                        txtLogin.getText(), txtSenha.getText());
            
    		Intent intent = new Intent();
    		intent.fxml(AdminController.FXML_ADMIN);
    		intent.putExtra("usuario", usuario);
    		
    		replaceViewAndShow(intent);
    		closeWindow(event);
    	}
    	catch (NoResultException e){
    		abrirAlertaInfo("N�o foi poss�vel logar", "Usu�rio ou Senha n�o encontrados...");
    	} 
    	catch (IOException e) {
			abrirAlertaErro("Erro Inesperado!", "N�o foi poss�vel abrir a janela de M�dia...");
			e.printStackTrace();
		}
    	
    }
             
}
