/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.imd.netflix.application.controller;

import static java.lang.Integer.parseInt;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.model.Media;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Geraldo e Roberto
 */
public class AdminController extends Controller {

    public static final String FXML_ADMIN = "/fxml/admin_view.fxml";
    
    @FXML
    private Label lblUsuarioLogado;
    @FXML
    private TextField idUser;
    @FXML
    private TextField nomeUser;
    @FXML
    private TextField login;
    @FXML
    private TextField senha;
    @FXML
    private TextField id;
    @FXML
    private TextField nome;
    @FXML
    private TextField descricao;
    @FXML
    private TextField ano;
    @FXML
    private TextField temporada;
    @FXML
    private TextField episodio;
    @FXML
    private TextField duracao;
    @FXML
    private TextField categoria;
    @FXML
    private TextField diretor;
    @FXML
    private TextField protagonista;
    @FXML
    private TextField idade;
    @FXML
    private ImageView icoSearch;

    @Override
    public void onCreate(Bundle bundle) {
        Usuario usuario = (Usuario) bundle.get("usuario");
        lblUsuarioLogado.setText(usuario.getNome());
    }

    public void cadastrar() {
        try {
            Dao<Usuario> dao = getDAO(Usuario.class);

            Usuario usuario = new Usuario();
            usuario.setNome(nomeUser.getText());
            usuario.setLogin(login.getText());
            usuario.setSenha(senha.getText());

            dao.saveOrUpdate(usuario);
            abrirAlertaInfo("Sucesso", "Usu�rio cadastrado/atualizado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "N�o foi poss�vel efetuar o cadastro");
        }
    }

    public void salvarMidia() {
        try {
            Dao<Media> dao = getDAO(Media.class);

            Media media = new Media();
            media.setId(parseInt(id.getText()));
            media.setNome(nome.getText());
            media.setDescricao(descricao.getText());
            media.setAno(parseInt(ano.getText()));
            media.setTemporada(parseInt(temporada.getText()));
            media.setEpisodio(parseInt(episodio.getText()));
            media.setDuracao(parseInt(duracao.getText()));
            media.setCategoria(categoria.getText());
            media.setDiretor(diretor.getText());
            media.setProtagonista(protagonista.getText());
            media.setIdade(parseInt(idade.getText()));

            dao.saveOrUpdate(media);

            abrirAlertaInfo("Sucesso", "M�dia cadastrada/atualizada com sucesso");
            apagarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "N�o foi poss�vel cadastrar/atualizar");
        }
    }

    public void pesquisarPorID() {
        try {
            Dao<Media> dao = getDAO(Media.class);
            
            Media media = dao.findById((id.getText() != null || "".equals(id.getText())) 
            		? 0 : Integer.valueOf(id.getText()));
            
            if(media != null){
	            id.setText(media.getId().toString());
	            nome.setText(media.getNome());
	            descricao.setText(media.getDescricao());
	            ano.setText(media.getAno().toString());
	            temporada.setText(media.getTemporada().toString());
	            episodio.setText(media.getEpisodio().toString());
	            duracao.setText(media.getDuracao().toString());
	            categoria.setText(media.getCategoria());
	            diretor.setText(media.getDiretor());
	            protagonista.setText(media.getProtagonista());
	            idade.setText(media.getIdade().toString());
            }
            else {
            	abrirAlertaErro("Erro", "N�o foi poss�vel achar o item pesquisado. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", e.getCause() + e.getMessage());
        }
    }

    public void removerMidia() {
        try {
            Dao<Media> dao = getDAO(Media.class);
            Media media = dao.findById(parseInt(id.getText()));

            dao.delete(media);
            
            abrirAlertaInfo("Sucesso", "M�dia removida com sucesso");
            apagarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "N�o foi poss�vel remover a M�dia");
        }
    }
    
    public void apagarCampos(){
        id.setText("");
        nome.setText("");
        descricao.setText("");
        ano.setText("");
        temporada.setText("");
        episodio.setText("");
        duracao.setText("");
        diretor.setText("");
        protagonista.setText("");
        idade.setText("");
        categoria.setText("");
    }
    

}
