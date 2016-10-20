package br.ufrn.imd.netflix.application;

import java.io.IOException;

import br.ufrn.imd.netflix.application.controller.LoginController;
import br.ufrn.imd.netflix.application.core.Hibernate;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Transaction;

public class NetflixApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            /**
             * Carrega Hibernate
             */
            Hibernate.getSessionFactory();

        //    cadastrarAdmin();

            /**
             * Carrega Login
             */
            showLogin(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cadastrarAdmin() {
        Transaction tx = Hibernate.getSessionFactory().getCurrentSession().beginTransaction();
        Usuario usuario = new Usuario();
        usuario.setNome("Administrador");
        usuario.setLogin("admin");
        usuario.setSenha("123");

        Hibernate.getSessionFactory().getCurrentSession().save(usuario);

        tx.commit();

    }

    private void showLogin(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(LoginController.FXML_LOGIN));
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Efetuar Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
