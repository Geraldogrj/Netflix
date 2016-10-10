package br.ufrn.imd.netflix.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NetflixApp extends Application {
	
    private static final String FXML_LOGIN = "/fxml/loginView.fxml";

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FXML_LOGIN));
            Scene scene = new Scene(root,400,300);
            
            primaryStage.setResizable(false);
            primaryStage.setTitle("Efetuar Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
    }

   public static void main(String[] args) throws Exception {
        launch(args);
    }
   
}
