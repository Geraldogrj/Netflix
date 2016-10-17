package br.ufrn.imd.netflix.application.controller;


import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.ReplaceableViewController;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController extends ReplaceableViewController {
	
	public static final String FXML_MAIN = "/fxml/main_view.fxml";
	
	@FXML
	private Label lblUsuarioLogado;
	
	@FXML
	private BorderPane borderPane;
		
	@Override
	protected void onReplace(Parent parent) {
		borderPane.setCenter(parent);
	}
	
	@Override
	protected void onReplace(Bundle bundle) {
		if(bundle != null){
			if(bundle.get("usuario") != null){
				lblUsuarioLogado.setText(((Usuario) bundle.get("usuario")).getLogin());
			}
		}
	}
	
	
	

}
