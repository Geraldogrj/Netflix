package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ReplaceableViewController extends Controller implements IReplaceableViewController {

	protected Stage stage;
	
	public ReplaceableViewController(String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.setController(this);
		Parent root = loader.load();
		stage = new Stage();
		stage.setScene(new Scene(root));
	}

	public ReplaceableViewController(String fxml, Bundle bundle) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.setController(this);
		Parent root = loader.load();
        this.onCreate(bundle);
		stage = new Stage();
		stage.setScene(new Scene(root));
	}
	
	@Override
	public final void replaceView(Intent intent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(intent.getFXML()));
        Parent root = loader.load();
        Controller controller = loader.getController();
        if(intent.getExtras() != null)
        	controller.onCreate(intent.getExtras());
       	onReplace(root);
	}
	
	@Override
	public final void replaceViewAndShow(Intent intent) throws IOException {
		replaceView(intent);
		if(!stage.isShowing()) stage.show();
	}
	
	@Override
	public void show() {
		if(!stage.isShowing()) stage.show();
	}
	
	@Override
	public void close() {
		if(stage.isShowing()) stage.close();
	}
	
	protected abstract void onReplace(Parent parent);
	
	
}
