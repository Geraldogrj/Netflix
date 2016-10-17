package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.core.Intent;
import br.ufrn.imd.netflix.application.core.WorkIndicatorDialog;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    public void logar(ActionEvent event){
    	
    	Dao<Usuario> dao = getDAO(Usuario.class);

		WorkIndicatorDialog<Usuario> task = getWorkingDialog("Carregando usuário...", event, Usuario.class);
		
		task.execute(() -> {
			return dao.queryForOne("select u from Usuario u where u.login = ?0 and u.senha = ?1 ", 
                    txtLogin.getText(), txtSenha.getText()); 
		});
		
		task.onFinish((result) -> {
			if (result != null){
    			Usuario usuario = result;
    			Intent intent = new Intent();
    			
    			if("admin".equals(usuario.getLogin())){
    				intent.fxml(AdminController.FXML_ADMIN);
    			}
    			else {
    				intent.fxml(MediaController.FXML_MEDIA);
    			}
    			
    			Bundle bundle = new Bundle();
    			bundle.putExtra("usuario", usuario);
				getRuntime().replaceMainViewAndShow(intent);
				getRuntime().setMainViewBundle(bundle);
 	    		getRuntime().closeWindow(event);
			}
			else {
				abrirAlertaErro("Usuário Não Encontrado...", "Usuário não identificado...");
			}
		});   		
   
    }
             
}
