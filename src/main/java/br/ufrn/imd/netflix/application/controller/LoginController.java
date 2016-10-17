package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

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

		WorkIndicatorDialog<Usuario> task = getWorkingDialog("Carregando usuario...", event, Usuario.class);
		
		task.execute(() -> {
			return dao.queryForOne("select u from Usuario u where u.login = ?0 and u.senha = ?1 ", 
                    txtLogin.getText(), txtSenha.getText()); 
		});
		
		task.onFinish((result) -> {
			if (result != null){
				
				/** Captura o usuario que foi buscado no banco de dados */
    			Usuario usuario = result;
    			Intent intent = new Intent();
    			
    			/** Define qual sera o conteudo janela principal */
    			if("admin".equals(usuario.getLogin())){
    				intent.title("Administracao");
    				intent.fxml(AdminController.FXML_ADMIN);
    			}
    			else {
    				intent.title("Netflix");
    				intent.fxml(MediaController.FXML_MEDIA);
    			}
    			
    			/** informacoes para a janela principal */
    			Bundle bundle = new Bundle();
    			bundle.putExtra("usuario", usuario);
    			
    			/** Carrega a janela principal e seta o conte�do */
    			try {
					getRuntime().loadView(MainController.FXML_MAIN);
					getRuntime().replaceMainView(intent);
					getRuntime().setMainViewBundle(bundle);
					getRuntime().showMainView();
				} 
    			catch (IOException e) {				
    				abrirAlertaErro("Erro", "Erro ao carregar janela principal...");
				}

			}
			else {
				abrirAlertaErro("Usuario Nao Encontrado...", "Usuario nao identificado...");
			}
		});   		
   
    }
             
}
