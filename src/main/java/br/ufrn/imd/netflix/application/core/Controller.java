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
import javafx.scene.control.TextField;
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
	protected Controller openWindow(Intent intent) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(intent.getFXML()));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.onCreate(intent.getExtras());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        return controller;
	}
			
	protected <T extends Object> WorkIndicatorDialog<T> getWorkingDialog(String message, ActionEvent event, Class<T> clazz){
		return new WorkIndicatorDialog<T>(((Node) event.getSource()).getScene().getWindow(), message);
	}
	
	protected ApplicationRuntime getRuntime(){
		return ApplicationRuntime.getInstance();
	}
			
	protected void abrirAlertaInfo(String title, String contentText){
		ApplicationRuntime.getInstance().abrirAlertaInfo(title, contentText);
	}
	
	protected void abrirAlertaErro(String title, String contentText){
		ApplicationRuntime.getInstance().abrirAlertaErro(title, contentText);
	}
	
	protected void abrirAlertaWarn(String title, String contentText){
		ApplicationRuntime.getInstance().abrirAlertaWarn(title, contentText);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void onCreate(Bundle bundle){
		
	}
	
	public void setDisableCampos(TextField[] fields, boolean disabled){
		for (int i = 0; i < fields.length; i++){
			fields[i].setDisable(disabled);	
		}
	}
	
	public void resetTexts(TextField[] fields){
		for (int i = 0; i < fields.length; i++){
			fields[i].setText("");
		}
	}
		

}
