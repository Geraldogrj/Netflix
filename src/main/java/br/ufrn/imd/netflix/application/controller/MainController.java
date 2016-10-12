package br.ufrn.imd.netflix.application.controller;

import java.io.IOException;

import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Intent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController extends Controller {
	
	public static final String FXML_MAIN = "/fxml/main_view.fxml";
	
	@FXML
	private BorderPane borderPane;
	
	/**
	 * Carrega um FXML com parametros.
	 * Observação: O FXML deve possuir um fx:controller definido.
	 * @param fxml
	 * @param params
	 * @throws IOException
	 */
	public void replaceView(Intent intent) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(intent.getFXML()));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.onCreate(intent.getExtras());
        borderPane.setCenter(root);
	}

}
