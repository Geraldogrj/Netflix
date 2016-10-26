package br.ufrn.imd.netflix.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.netflix.application.core.Bundle;
import br.ufrn.imd.netflix.application.core.Controller;
import br.ufrn.imd.netflix.application.core.Dao;
import br.ufrn.imd.netflix.application.gui.MediaControl;
import br.ufrn.imd.netflix.application.model.Media;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

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
    private GridPane gridMedia;

    public int paginaAtual;

    @Override
    public void onCreate(Bundle bundle) {
        paginaAtual = 0;
        carregarMedia();
    }

    private void carregarMedia() {

        Dao<Media> dao = getDAO(Media.class);
        List<ImageView> pictures = new ArrayList<>();
        //Seta a quantidade de itens por p�gina
        dao.setPageSize(QUANTIDADE_PAGINA);
        List<Media> medias = dao.findAll(paginaAtual);
        Iterator<Media> it = medias.iterator();
        //Itera��o sobre as m�dias para obter a ImageView
        while (it.hasNext()) {
            Media media = it.next();
            File file = new File(media.getImagem());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);

                imageView.setOnMouseClicked(e -> {
                    abrirMedia(media);
                });

                pictures.add(imageView);
            } catch (Exception e) {

            }
        }
        //Configurando o GridPane
        gridMedia.setPadding(new Insets(50, 50, 50, 50));
        gridMedia.setHgap(20);
        gridMedia.setVgap(100);
        //Os itens ser�o inseridos a partir do ponto (0,0) incrementando
        //as linhas para depois incrementar as colunas
        int imageCol = 0;
        int imageRow = 0;
        //Iterando sobre as figuras para adicionar no GridPane
        for (ImageView picture : pictures) {
            picture.setFitWidth(LARGURA_IMG);
            picture.setFitHeight(ALTURA_IMG);
            gridMedia.add(picture, imageCol, imageRow);

            imageCol++;
            //Teste para saber se j� chegou ao fim de 4 colunas.
            if (imageCol > 3) {
                imageCol = 0;
                imageRow++;
            }
        }
    }

    private void abrirMedia(Media media) {
        File file = new File(media.getVideo());
        javafx.scene.media.Media mediaFx = new javafx.scene.media.Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(mediaFx);
        MediaView mediaView = new MediaView(mediaPlayer);
        

        /*final DoubleProperty width = mediaView.fitWidthProperty();
        final DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));*/

        // mediaView.setPreserveRatio(true);

        //StackPane root = new StackPane();
        //root.getChildren().add(mediaView);
        
        Group root = new Group();
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        

        final Scene scene = new Scene(root, 540, 241);
        
        scene.setRoot(mediaControl);
//        scene.setFill(Color.BLACK);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(media.getNome());
        stage.setFullScreen(true);
        stage.show();
        
        mediaPlayer.play();

    }
}
