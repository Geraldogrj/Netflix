package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Geraldo e Roberto
 */
public class MediaController extends Controller {
    
     public static final String FXML_MEDIA = "/fxml/media_view.fxml";
     
     @FXML
     private Label lblUsuarioLogado;

    @Override
    public void onCreate(Bundle bundle) {
       Usuario usuario = (Usuario) bundle.get("usuario");
		lblUsuarioLogado.setText(usuario.getNome());
    }
     
     
     
}
