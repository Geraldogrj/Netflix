package br.ufrn.imd.netflix.application.core;

import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class ReplaceableViewController extends Controller implements IReplaceableViewController {

	protected Stage stage;
		
	@Override
	public void replaceView(Parent parent) {
		onReplace(parent);
	}
	
	protected abstract void onReplace(Parent parent);
	
	
}
