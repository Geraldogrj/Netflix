package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

import br.ufrn.imd.netflix.application.core.ReplaceableViewController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController extends ReplaceableViewController {
	
	public static final String FXML_MAIN = "/fxml/main_view.fxml";
	
	@FXML
	private BorderPane borderPane;
	
	public MainController(String fxml) throws IOException {
		super(fxml);
	}
	
	@Override
	protected void onReplace(Parent parent) {
		borderPane.setCenter(parent);
	}
	

}
