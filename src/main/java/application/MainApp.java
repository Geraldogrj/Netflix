package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginApp.fxml"));
            Scene scene = new Scene(root,400,300);
            
            primaryStage.setResizable(false);
            primaryStage.setTitle("Efetuar Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
        }
    }

   public static void main(String[] args) throws Exception {
        launch(args);
    }
}
