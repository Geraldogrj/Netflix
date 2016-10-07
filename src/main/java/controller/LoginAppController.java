package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    public void logar(){
        System.out.println("Clicou!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
