package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class ApplicationRuntime {
	
	private static ApplicationRuntime singleton;
	
	protected IReplaceableViewController mainView;
			
	private ApplicationRuntime() {}
	
	public static ApplicationRuntime getInstance(){
		if(singleton != null)
			return singleton;
		else
			singleton = new ApplicationRuntime();
			return singleton;
	}
		
	public void loadView(IReplaceableViewController view) {
		this.mainView = view;
	}
		
	public void replaceMainView(Intent intent) throws IOException{
		ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
		progressIndicator.setMaxSize(150, 150);
		mainView.replaceView(progressIndicator);
		Task<Void> taskReplaceView = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(1000);
				Platform.runLater(() -> {
					try {
						mainView.replaceView(intent);
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
		mainView.replaceViewAndShow(intent);
	}
	
	public void showMainView(){
		mainView.show();
	}
	
	public void closeMainView(){
		mainView.close();
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
