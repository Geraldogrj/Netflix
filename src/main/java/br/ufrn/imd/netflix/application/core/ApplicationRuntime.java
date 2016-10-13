package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class ApplicationRuntime {
	
	private static ApplicationRuntime singleton = new ApplicationRuntime();
	
	protected Stage mainViewStage;
	protected IReplaceableViewController mainViewController;
			
	private ApplicationRuntime() {}
	
	public static ApplicationRuntime getInstance(){
		return singleton;
	}
	

		
	public void loadView(String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = loader.load();
		mainViewController = loader.getController();
		mainViewStage = new Stage();
		mainViewStage.setScene(new Scene(root));
	}
		
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
	
	public void replaceMainViewAndShow(Intent intent) throws IOException{
		replaceMainView(intent);
		showMainView();
	}
	
	public void showMainView(){
		if(!mainViewStage.isShowing()) mainViewStage.show();
	}
	
	public void closeMainView(){
		if(mainViewStage.isShowing()) mainViewStage.close();
	}
	
	/**
	 * Fecha a janela que chamou a ação.
	 * @param event
	 */
	public void closeWindow(ActionEvent event){
		 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		 stage.close();
	}
	

}
