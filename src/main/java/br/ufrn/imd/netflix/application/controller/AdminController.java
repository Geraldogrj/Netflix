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
    @FXML    private TextField userNome;
    @FXML    private TextField userLogin;
    @FXML    private TextField userSenha;
    @FXML    private Button userNovo;
    @FXML    private Button userSalvar;
    @FXML    private Button userRemover;
    @FXML    private TableView<Usuario> userTableView;
    @FXML    private TableColumn<Usuario, Object> userIdColumn;
    @FXML    private TableColumn<Usuario, Object> userNomeColumn;
    @FXML    private TableColumn<Usuario, Object> userLoginColumn;
    @FXML    private TableColumn<Usuario, Object> userSenhaColumn;
    
    /** Dados de gerenciar mídia  */
    private Media mediaSelecionada;
           
    /** Dados da tabela gerenciar mídia */
    @FXML    private TextField mediaNome;
    @FXML    private TextField mediaDescricao;
    @FXML    private TextField mediaAno;
    @FXML    private TextField mediaTemporada;
    @FXML    private TextField mediaEpisodio;
    @FXML    private TextField mediaDuracao;
    @FXML    private TextField mediaCategoria;
    @FXML    private TextField mediaDiretor;
    @FXML    private TextField mediaProtagonista;
    @FXML	 private TextField mediaClassificacao;
    @FXML    private ImageView mediaIconSearch;
    @FXML    private Button mediaNovo;
    @FXML    private Button mediaSalvar;
    @FXML    private Button mediaRemover;
    @FXML	 private TableView<Media> mediaTableView;
    @FXML    private TableColumn<Media, Object> mediaIdColumn;
    @FXML    private TableColumn<Media, Object> mediaNomeColumn;
    @FXML    private TableColumn<Media, Object> mediaAnoColumn;
    
    private TextField[] FIELDS_USUARIO;
    private TextField[] FIELDS_MEDIA;
         
    @Override
    public void onCreate(Bundle bundle) {  
    	/** listagem de usuarios */
        userTableView.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, selecionado) -> { acaoSelecionarUsuario(selecionado); });

        userIdColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("id"));
        userNomeColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("nome"));
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("login"));
        userSenhaColumn.setCellValueFactory(new PropertyValueFactory<Usuario,Object>("senha"));
        
        /** listagem de medias */
        mediaTableView.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, selecionado) -> { acaoSelecionarMedia(selecionado); });
        
        mediaIdColumn.setCellValueFactory(new PropertyValueFactory<Media,Object>("id"));
        mediaNomeColumn.setCellValueFactory(new PropertyValueFactory<Media,Object>("nome"));
        mediaAnoColumn.setCellValueFactory(new PropertyValueFactory<Media,Object>("ano"));
        
        atualizarListagemUsuarios();
        atualizarListagemMedia();
        
        FIELDS_USUARIO = new TextField[]{userNome, userLogin, userSenha};
        
        FIELDS_MEDIA = new TextField[]{mediaNome, mediaDescricao, mediaAno, mediaTemporada, 
        		mediaEpisodio, mediaDuracao, mediaCategoria, mediaDiretor, mediaProtagonista, mediaClassificacao};

        
    }
    
    private void atualizarListagemUsuarios(){
    	Dao<Usuario> dao = getDAO(Usuario.class);  
        List<Usuario> usuarios = dao.findAll();
        userTableView.setItems(FXCollections.observableArrayList(usuarios));
        userTableView.refresh();
    }
    
	private void atualizarListagemMedia() {
		Dao<Media> dao = getDAO(Media.class);
		List<Media> medias = dao.findAll();
		mediaTableView.setItems(FXCollections.observableArrayList(medias));
		mediaTableView.refresh();
	}
    
    public void acaoSelecionarUsuario(Usuario usuario){
    	if(usuario != null){
	    	userSelecionado = usuario;
	    	setDisableCampos(FIELDS_USUARIO,false);
	    	userNovo.setText("Cancelar");  
	    	userNovo.setOnAction(e -> { this.acaoCancelarUsuario(); });
	    	userSalvar.setDisable(false);
	    	userRemover.setDisable(false);
	    	
	    	userNome.setText(userSelecionado.getNome());
	    	userLogin.setText(userSelecionado.getLogin());
	    	userSenha.setText(userSelecionado.getSenha());
    	}
    }
    
    public void acaoSelecionarMedia(Media media){
    	if(media != null){
	    	mediaSelecionada = media;
	    	setDisableCampos(FIELDS_MEDIA, false);
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
    	setDisableCampos(FIELDS_USUARIO, false);
    	userNovo.setText("Cancelar");  
    	userNovo.setOnAction(e -> { this.acaoCancelarUsuario(); });
    	userSalvar.setDisable(false);
    	userRemover.setDisable(false);
    }
    
    public void acaoNovoMedia() {
		mediaSelecionada = new Media();
		setDisableCampos(FIELDS_MEDIA, false);
		mediaNovo.setText("Cancelar");
		mediaNovo.setOnAction(e -> { this.acaoCancelarMedia(); });
		mediaSalvar.setDisable(false);
		mediaRemover.setDisable(false);
	}
    
    public void acaoCancelarUsuario(){
    	apagarCamposUsuario();
    	userSelecionado = null;
    	setDisableCampos(FIELDS_USUARIO,true);
    	userNovo.setText("Novo");  
    	userNovo.setOnAction(e -> { this.acaoNovoUsuario(); });
    	userSalvar.setDisable(true);
    	userRemover.setDisable(true);
    }
    
    
    private void acaoCancelarMedia() {
		apagarCamposMedia();
		mediaSelecionada = null;
		setDisableCampos(FIELDS_MEDIA, true);
		mediaNovo.setText("Novo");
		mediaNovo.setOnAction(e -> { this.acaoNovoMedia(); });
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
    
    public void acaoSalvarMedia() {
        try {
            Dao<Media> dao = getDAO(Media.class);

            mediaSelecionada.setNome(mediaNome.getText());
            mediaSelecionada.setDescricao(mediaDescricao.getText());
            mediaSelecionada.setAno(parseInt(mediaAno.getText()));
            mediaSelecionada.setTemporada(parseInt(mediaTemporada.getText()));
            mediaSelecionada.setEpisodio(parseInt(mediaEpisodio.getText()));
            mediaSelecionada.setDuracao(parseInt(mediaDuracao.getText()));
            mediaSelecionada.setCategoria(mediaCategoria.getText());
            mediaSelecionada.setDiretor(mediaDiretor.getText());
            mediaSelecionada.setProtagonista(mediaProtagonista.getText());
            mediaSelecionada.setIdade(parseInt(mediaClassificacao.getText()));

            dao.saveOrUpdate(mediaSelecionada);
            acaoCancelarMedia();
            atualizarListagemMedia();
            abrirAlertaInfo("Sucesso", "Mídia cadastrada/atualizada com sucesso");
            

        } catch (Exception e) {
            e.printStackTrace();
            abrirAlertaErro("Erro", "Não foi possível cadastrar/atualizar");
        }
    }

    public void acaoRemoverMedia() {
    	if(mediaSelecionada != null && mediaSelecionada.getId() > 0){
	    	try {
	            Dao<Media> dao = getDAO(Media.class);	
	            dao.delete(mediaSelecionada);
	            acaoCancelarMedia();
	            atualizarListagemMedia();
	            abrirAlertaInfo("Sucesso", "Mídia removida com sucesso");
	            apagarCamposMedia();
	        } catch (Exception e) {
	            e.printStackTrace();
	            abrirAlertaErro("Erro", "Não foi possível remover a Mídia");
	        }
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
    
    public void apagarCamposMedia(){
    	resetTexts(FIELDS_MEDIA);
    }
    
    public void apagarCamposUsuario(){
    	resetTexts(FIELDS_USUARIO);
    }
        
  

}
