package br.ufrn.imd.netflix.application.controller;

import static java.lang.Integer.parseInt;

import java.util.List;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.model.Media;
import br.ufrn.imd.netflix.application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 *
 * @author Geraldo e Roberto
 */
public class AdminController extends Controller {

    public static final String FXML_ADMIN = "/fxml/admin_view.fxml";
    
    /** Dados de gerenciar usuario  */
    private Usuario userSelecionado;
    
    /** Dados de gerenciar usuario */
    @FXML
    private TextField userNome;
    @FXML
    private TextField userLogin;
    @FXML
    private TextField userSenha;
    @FXML
    private Button userNovo;
    @FXML
    private Button userSalvar;
    @FXML 
    private Button userRemover;
    @FXML
    private TableView<Usuario> userTableView;
    @FXML
    private TableColumn<Usuario, Object> userIdColumn;
    @FXML
    private TableColumn<Usuario, Object> userNomeColumn;
    @FXML
    private TableColumn<Usuario, Object> userLoginColumn;
    @FXML
    private TableColumn<Usuario, Object> userSenhaColumn;
           
    /** Dados da tabela gerenciar mídia */
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
        userTableView.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, selecionado) -> { acaoSelecionarUsuario(selecionado); });

        userIdColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("id"));
        userNomeColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("nome"));
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("login"));
        userSenhaColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("senha"));
        
        atualizarListagemUsuarios();
        
    }
    
    private void atualizarListagemUsuarios(){
    	Dao<Usuario> dao = getDAO(Usuario.class);  
        List<Usuario> usuarios = dao.findAll();
        userTableView.setItems(FXCollections.observableArrayList(usuarios));
        userTableView.refresh();
    }
    
    public void acaoSelecionarUsuario(Usuario usuario){
    	if(usuario != null){
	    	userSelecionado = usuario;
	    	userNome.setDisable(false);
	    	userLogin.setDisable(false);
	    	userSenha.setDisable(false);
	    	userNovo.setText("Cancelar");  
	    	userNovo.setOnAction(e -> { this.acaoCancelarUsuario(); });
	    	userSalvar.setDisable(false);
	    	userRemover.setDisable(false);
	    	
	    	userNome.setText(userSelecionado.getNome());
	    	userLogin.setText(userSelecionado.getLogin());
	    	userSenha.setText(userSelecionado.getSenha());
    	}
    }
    
    public void acaoNovoUsuario(){
    	userSelecionado = new Usuario();
    	userNome.setDisable(false);
    	userLogin.setDisable(false);
    	userSenha.setDisable(false);
    	userNovo.setText("Cancelar");  
    	userNovo.setOnAction(e -> { this.acaoCancelarUsuario(); });
    	userSalvar.setDisable(false);
    	userRemover.setDisable(false);
    }
    
    public void acaoCancelarUsuario(){
    	apagarCamposUsuario();
    	userSelecionado = null;
    	userNome.setDisable(true);
    	userLogin.setDisable(true);
    	userSenha.setDisable(true);
    	userNovo.setText("Novo");  
    	userNovo.setOnAction(e -> { this.acaoNovoUsuario(); });
    	userSalvar.setDisable(true);
    	userRemover.setDisable(true);
    	
    }

    @FXML
    public void acaoSalvarUsuario() {
        try {
            Dao<Usuario> dao = getDAO(Usuario.class);
            
            userSelecionado.setNome(userNome.getText());
            userSelecionado.setLogin(userLogin.getText());
            userSelecionado.setSenha(userSenha.getText());

            dao.saveOrUpdate(userSelecionado);
            userSelecionado = null;
            acaoCancelarUsuario();
            atualizarListagemUsuarios();
            abrirAlertaInfo("Sucesso", "Usuário cadastrado/atualizado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "Não foi possível efetuar o cadastro");
        }
    }
    
    public void acaoRemoverUsuario(){
    	if(userSelecionado != null && userSelecionado.getId() > 0){
	    	try {
	    		Dao<Usuario> dao = getDAO(Usuario.class);
	    		dao.delete(userSelecionado);
	    		acaoCancelarUsuario();
	    		atualizarListagemUsuarios();
	    		
	    	} catch (Exception e) {
	            e.printStackTrace();
	            abrirAlertaErro("Erro", "Não foi possível remover o cadastro");
	        }
    	}
    }

    public void acaoSalvarMidia() {
        try {
            Dao<Media> dao = getDAO(Media.class);

            Media media = new Media();
          //  media.setId(parseInt(id.getText()));
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

            abrirAlertaInfo("Sucesso", "Mídia cadastrada/atualizada com sucesso");
            apagarCampos();

        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "Não foi possível cadastrar/atualizar");
        }
    }

/*    public void pesquisarPorID() {
        try {
            Dao<Media> dao = getDAO(Media.class);
            
            Media media = dao.findById((id.getText() == null || "".equals(id.getText())) 
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
            	abrirAlertaErro("Erro", "Não foi possível achar o item pesquisado. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", e.getCause() + e.getMessage());
        }
    }*/

    public void acaoRemoverMidia() {
        try {
            Dao<Media> dao = getDAO(Media.class);
            Media media = dao.findById(parseInt(id.getText()));

            dao.delete(media);
            
            abrirAlertaInfo("Sucesso", "Mídia removida com sucesso");
            apagarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "Não foi possível remover a Mídia");
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
    
    public void apagarCamposUsuario(){
    	userNome.setText("");
    	userLogin.setText("");
    	userSenha.setText("");
    }
    
    public void setarImagem(){
        
    }
    
  

}
