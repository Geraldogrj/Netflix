package br.ufrn.imd.netflix.application;

import java.io.IOException;

import br.ufrn.imd.netflix.application.controller.LoginController;
import br.ufrn.imd.netflix.application.controller.MainController;
import br.ufrn.imd.netflix.application.core.ApplicationRuntime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NetflixApp extends Application {
			
    @Override
    public void start(Stage stage) {
        try { 
        	getRuntime().loadView(MainController.FXML_MAIN);
        	loadLogin(stage);
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
                   
   private void loadLogin(Stage primaryStage) throws IOException{
	   Parent root = FXMLLoader.load(getClass().getResource(LoginController.FXML_LOGIN));
       Scene scene = new Scene(root,400,300);
       
       primaryStage.setResizable(false);
       primaryStage.setTitle("Efetuar Login");
       primaryStage.setScene(scene);
       primaryStage.show();
   }
   
   private ApplicationRuntime getRuntime(){
	   return ApplicationRuntime.getInstance();
   }
    
   public static void main(String[] args) throws Exception {
        launch(args);
    }
   
}
