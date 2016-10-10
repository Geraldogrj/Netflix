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

/**
 * Classe responsável por definir os métodos comuns de Controllers.
 * @author Roberto Dantas
 *
 */
public abstract class Controller implements Initializable {
		
	/**
	 * Retorna o DAO genérico referente a classe que extends Model.
	 * @param clazz
	 * @return
	 */
	protected <T extends Model> Dao<T> getDAO(Class<T> clazz){
		return new Dao<T>(clazz);
	}
			
	/**
	 * Carrega um FXML com parametros.
	 * Observação: O FXML deve possuir um fx:controller definido.
	 * @param fxml
	 * @param params
	 * @throws IOException
	 */
	protected void abrirJanela(Intent intent) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(intent.getFXML()));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.onCreate(intent.getExtras());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
	}
	
	/**
	 * Fecha a janela que chamou a ação.
	 * @param event
	 */
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
		
	}
	
	public void onCreate(Bundle bundle){
		
	}
		

}
