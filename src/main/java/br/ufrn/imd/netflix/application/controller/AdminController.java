/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author geral_001
 */
public class AdminController extends Controller {
    
    @FXML
    private Label lblUsuarioLogado;
    
    private Usuario usuarioLogado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUsuarioLogado.setText(usuarioLogado.getLogin());
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
    
}
