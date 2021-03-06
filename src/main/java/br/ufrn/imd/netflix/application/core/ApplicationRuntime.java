package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ApplicationRuntime {
	
	private static ApplicationRuntime singleton = new ApplicationRuntime();
	
	/** Atributo que armazena a stage janela principal */
	protected Stage mainViewStage;
	
	/** 
	 * Atributo que armazena o controlador da janela principal
	 * Respons�vel por realizar as trocas de conte�dos de dentro. 
	 */
	protected IReplaceableViewController mainViewController;
			
	private ApplicationRuntime() {}
	
	public static ApplicationRuntime getInstance(){
		return singleton;
	}
	
	public void setMainViewBundle(Bundle bundle) {
		mainViewController.replaceBundle(bundle);
	}
	
	/** Carrega a janela principal */
	public void loadView(String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = loader.load();
		mainViewController = loader.getController();
		mainViewStage = new Stage();
		mainViewStage.setScene(new Scene(root));
	}
	
	/** Carrega a janela principal com conte�do */
	public void loadView(String fxml, Bundle bundle) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = loader.load();
		mainViewController = loader.getController();
		Controller controller = (Controller) mainViewController;
		controller.onCreate(bundle);
		mainViewStage = new Stage();
		mainViewStage.setScene(new Scene(root));
	}
		
	/** Substitui o conte�do da janela principal */
	public void replaceMainView(Intent intent) throws IOException{
		ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
		progressIndicator.setMaxSize(150, 150);
		mainViewController.replaceView(progressIndicator);
		Task<Void> taskReplaceView = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(1000);
				Platform.runLater(() -> {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(intent.getFXML()));
						Parent root = loader.load();
						if(!intent.getExtras().isEmpty()){
							Controller controller = loader.getController();
							controller.onCreate(intent.getExtras());
						}
						mainViewStage.setTitle(intent.getTitle());
						mainViewController.replaceView(root);
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				});
				return null;
			}
		};
		
		taskReplaceView.setOnFailed(event -> System.err.println(event.getSource().getException().getMessage()));
							
		new Thread(taskReplaceView).start();
		
	}
	
	/** Substitui o conte�do da janela principal e exibe a janela caso esteja escondida */
	public void replaceMainViewAndShow(Intent intent) {
		try {
			replaceMainView(intent);
		} 
		catch (IOException e) {
			abrirAlertaErro("Erro Inesperado!", "N�o foi poss�vel abrir a janela de M�dia... \n" + e.getMessage());
			e.printStackTrace();
		}
		showMainView();
	}
	
	/** Abre popup de Informacao */
	protected void abrirAlertaInfo(String title, String contentText){
		abrirAlerta(AlertType.INFORMATION, title, contentText);
	}
	
	/** Abre popup de Erro */
	protected void abrirAlertaErro(String title, String contentText){
		abrirAlerta(AlertType.ERROR, title, contentText);
	}
	
	/** Abre popup de Warning */
	protected void abrirAlertaWarn(String title, String contentText){
		abrirAlerta(AlertType.WARNING, title, contentText);
	}
	
	private void abrirAlerta(AlertType alertType, String title, String contentText){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.show();
	}
	
	public void showMainView(){
		if(!mainViewStage.isShowing()) mainViewStage.show();
	}
	
	public void closeMainView(){
		if(mainViewStage.isShowing()) mainViewStage.close();
	}
	
	/**
	 * Fecha a janela que chamou a a��o.
	 * @param event
	 */
	public void closeWindow(ActionEvent event){
		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 stage.close();
	}
	

}
