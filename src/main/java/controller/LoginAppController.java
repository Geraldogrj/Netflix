package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Geraldo e Roberto
 */
public class LoginAppController implements Initializable {

    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogar;
    @FXML
    private ImageView imgNetflix;
   
    @FXML
    public void logar(ActionEvent event){
        try {
            abrirTela(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void abrirTela(ActionEvent event) throws IOException{
        Parent media = FXMLLoader.load(getClass().getResource("/fxml/MediaOverview.fxml"));
        Scene mediaScene = new Scene(media);
        Stage mediaTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //TODO: Não estou conseguindo gerar a tela centralizada!!!
        mediaTela.setTitle("Media Overview");
        mediaTela.setScene(mediaScene);
        mediaTela.show();
    }
}
