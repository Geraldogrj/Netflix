/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Intent;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Geraldo
 */
public class AdminController extends Controller {
    
    public static final String FXML_ADMIN = "/fxml/admin_view.fxml";
    	
	@FXML 
	private Label lblUsuarioLogado;
	
	@FXML
	private Button btnBuscar;

	@Override
	public void onCreate(Bundle bundle) {
		Usuario usuario = (Usuario) bundle.get("usuario");
		lblUsuarioLogado.setText(usuario.getLogin());
	}
	
	@FXML
	public void carregaLogin() throws IOException{
		Intent intent = new Intent().fxml(LoginController.FXML_LOGIN);
		replaceView(intent);
	}
        
   
}
