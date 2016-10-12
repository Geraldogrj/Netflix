/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.model.Media;
import br.ufrn.imd.netflix.application.model.Usuario;
import static java.lang.Integer.parseInt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.event.spi.SaveOrUpdateEvent;

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

	@Override
	public void onCreate(Bundle bundle) {
		Usuario usuario = (Usuario) bundle.get("usuario");
		lblUsuarioLogado.setText(usuario.getNome());
	}

    public Label getLblUsuarioLogado() {
        return lblUsuarioLogado;
    }

    public void setLblUsuarioLogado(Label lblUsuarioLogado) {
        this.lblUsuarioLogado = lblUsuarioLogado;
    }

    public TextField getIdUser() {
        return idUser;
    }

    public void setIdUser(TextField idUser) {
        this.idUser = idUser;
    }

    public TextField getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(TextField nomeUser) {
        this.nomeUser = nomeUser;
    }

    public TextField getLogin() {
        return login;
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public TextField getSenha() {
        return senha;
    }

    public void setSenha(TextField senha) {
        this.senha = senha;
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public TextField getNome() {
        return nome;
    }

    public void setNome(TextField nome) {
        this.nome = nome;
    }

    public TextField getDescricao() {
        return descricao;
    }

    public void setDescricao(TextField descricao) {
        this.descricao = descricao;
    }

    public TextField getAno() {
        return ano;
    }

    public void setAno(TextField ano) {
        this.ano = ano;
    }

    public TextField getTemporada() {
        return temporada;
    }

    public void setTemporada(TextField temporada) {
        this.temporada = temporada;
    }

    public TextField getEpisodio() {
        return episodio;
    }

    public void setEpisodio(TextField episodio) {
        this.episodio = episodio;
    }

    public TextField getDuracao() {
        return duracao;
    }

    public void setDuracao(TextField duracao) {
        this.duracao = duracao;
    }

    public TextField getCategoria() {
        return categoria;
    }

    public void setCategoria(TextField categoria) {
        this.categoria = categoria;
    }

    public TextField getDiretor() {
        return diretor;
    }

    public void setDiretor(TextField diretor) {
        this.diretor = diretor;
    }

    public TextField getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(TextField protagonista) {
        this.protagonista = protagonista;
    }

    public TextField getIdade() {
        return idade;
    }

    public void setIdade(TextField idade) {
        this.idade = idade;
    }
    
    public void cadastrar(){
        try{
        Dao<Usuario> dao = getDAO(Usuario.class);
        
        Usuario usuario = new Usuario();
        usuario.setId(parseInt(idUser.getText()));
        usuario.setNome(nomeUser.getText());
        usuario.setLogin(login.getText());
        usuario.setSenha(senha.getText());
        
        dao.saveOrUpdate(usuario);
            abrirAlertaInfo("Sucesso", "Usuário cadastrado/atualizado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            abrirAlertaErro("Erro", "Não foi possível efetuar o cadastro");
        }
    }
   
    public void salvarMidia(){
       try{
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
        
        abrirAlertaInfo("Sucesso" , "Mídia cadastrada/atualizada com sucesso");
        
       } catch(Exception e) {
           e.printStackTrace();
           abrirAlertaErro("Erro", "Não foi possível cadastrar/atualizar");
       }
    }
    
    public void pesquisarPorID(){
        Dao<Media> dao = getDAO(Media.class);
        
        Media media = dao.findById(parseInt(id.getText()));
        
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
}
