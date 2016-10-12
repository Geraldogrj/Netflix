package br.ufrn.imd.netflix.application;

import java.io.IOException;

import br.ufrn.imd.netflix.application.controller.LoginController;
import br.ufrn.imd.netflix.application.controller.MainController;
import br.ufrn.imd.netflix.application.core.Intent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NetflixApp extends Application {
	
	private static MainController mainController;
	private static Stage mainStage;
	
    @Override
    public void start(Stage stage) {
        try { 
        	loadLogin(stage);
        	loadMain();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
   public static void show(){
	  if(!mainStage.isShowing())  mainStage.show();
   }
   
   public static void close(){
	   mainStage.close();
   }
    
   public static void replaceView(Intent intent) throws IOException{
	   mainController.replaceView(intent);
   }
   
   public static void replaceViewAndShow(Intent intent) throws IOException{
	   mainController.replaceView(intent);
	   show();
   }
       
   private void loadLogin(Stage primaryStage) throws IOException{
	   Parent root = FXMLLoader.load(getClass().getResource(LoginController.FXML_LOGIN));
       Scene scene = new Scene(root,400,300);
       
       primaryStage.setResizable(false);
       primaryStage.setTitle("Efetuar Login");
       primaryStage.setScene(scene);
       primaryStage.show();
   }
    
   private void loadMain() throws IOException{
	   FXMLLoader loader = new FXMLLoader(getClass().getResource(MainController.FXML_MAIN));
       Parent root = loader.load();
       mainController = (MainController) loader.getController();
       Scene scene = new Scene(root);
       mainStage = new Stage();
       mainStage.setScene(scene);
   }

   public static void main(String[] args) throws Exception {
        launch(args);
    }
   
}
