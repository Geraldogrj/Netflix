package br.ufrn.imd.netflix.application.controller;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.model.Media;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Geraldo e Roberto
 */
public class MediaController extends Controller {

    public static final String FXML_MEDIA = "/fxml/media_view.fxml";
    public static final int QUANTIDADE_PAGINA = 12;
    public static final int LARGURA_IMG = 100;
    public static final int ALTURA_IMG = 100;

    @FXML
    private ImageView img01;
    @FXML
    private GridPane gridMedia;

    public int paginaAtual;

    @Override
    public void onCreate(Bundle bundle) {
        //    Usuario usuario = (Usuario) bundle.get("usuario");
//       lblUsuarioLogado.setText(usuario.getNome());
        paginaAtual = 0;
        carregarMedia();
    }

    private void carregarMedia() {

        Dao<Media> dao = getDAO(Media.class);
        List<ImageView> pictures = new ArrayList<>();
        //Seta a quantidade de itens por página
        dao.setPageSize(QUANTIDADE_PAGINA);
        List<Media> medias = dao.findAll(paginaAtual);
        Iterator<Media> it = medias.iterator();
        //Iteração sobre as mídias para obter a ImageView
        while(it.hasNext()){
            Media media = it.next();
            File file = new File(media.getImagem());
            FileInputStream inputStream;
            try {
                        inputStream = new FileInputStream(file);
                        Image image = new Image(inputStream);
                        ImageView imageView = new ImageView(image);
                        pictures.add(imageView);
            } catch (Exception e){
                
            }
        }
       //Configurando o GridPane
        gridMedia.setPadding(new Insets(50,50,50,50));
        gridMedia.setHgap(20);
        gridMedia.setVgap(100);
        //Os itens serão inseridos a partir do ponto (0,0) incrementando
        //as linhas para depois incrementar as colunas
        int imageCol = 0;
        int imageRow = 0;
        //Iterando sobre as figuras para adicionar no GridPane
        for (ImageView picture : pictures) {   
            picture.setFitWidth(LARGURA_IMG);
            picture.setFitHeight(ALTURA_IMG);
            gridMedia.add(picture, imageCol, imageRow);
            
            imageCol++;
            //Teste para saber se já chegou ao fim de 4 colunas.
            if (imageCol > 3){
                imageCol = 0;
                imageRow++;
            }
        }
    }
}
