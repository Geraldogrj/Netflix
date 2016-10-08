package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.ModuloConexao;

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
    
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
   
    @FXML
    public void logar(ActionEvent event){
        testarConexao();
        String sql = "select * from tbuser where login=? and senha=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtLogin.getText());
			pst.setString(2, txtSenha.getText());
			rs = pst.executeQuery();

			if (rs.next()) {
                            abrirTela(event);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Não foi possível logar");
				alert.setContentText("Usuário ou Senha não encontados");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void abrirTela(ActionEvent event) throws IOException{
        String tela = "/fxml/MediaOverview.fxml";
        Parent media = FXMLLoader.load(getClass().getResource(tela));
        Scene mediaScene = new Scene(media);
        Stage mediaTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //TODO: Não estou conseguindo gerar a tela centralizada!!!
        mediaTela.setTitle("Media Overview");
        mediaTela.setScene(mediaScene);
        mediaTela.show();
    }
    
    private void testarConexao() {
		conexao = ModuloConexao.conector();

		if (conexao != null) {
                    System.out.println("Conectado");
		} else {
                    System.out.println("Não conectado");
		}
	}
}
