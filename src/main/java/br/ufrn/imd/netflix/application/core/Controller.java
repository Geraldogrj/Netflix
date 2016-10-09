package br.ufrn.imd.netflix.application.core;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {
	
	protected <T extends Model> Dao<T> getDAO(Class<T> clazz){
		return new Dao<T>(clazz);
	}
		
	protected void abrirJanela(String fxml) throws IOException{
	        Parent root = FXMLLoader.load(getClass().getResource(fxml));
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setTitle("Media Overview");
	        stage.setScene(scene);
	        stage.show();
	}
	
	protected void fecharJanela(ActionEvent event){
		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 stage.close();
	}
	
	protected void abrirAlertaInfo(String title, String contentText){
		abrirAlerta(AlertType.INFORMATION, title, contentText);
	}
	
	protected void abrirAlertaErro(String title, String contentText){
		abrirAlerta(AlertType.ERROR, title, contentText);
	}
	
	protected void abrirAlertaWarn(String title, String contentText){
		abrirAlerta(AlertType.WARNING, title, contentText);
	}
	
	private void abrirAlerta(AlertType alertType, String title, String contentText){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
	}
	
	
	
	

}
