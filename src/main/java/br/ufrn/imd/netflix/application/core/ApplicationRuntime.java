package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
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
		mainView.replaceView(intent);
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
