package br.ufrn.imd.netflix.application.core;

import java.io.IOException;

public interface IReplaceableViewController {
	
	public void replaceView(Intent intent) throws IOException;
	public void replaceViewAndShow(Intent intent) throws IOException;
	public void show();
	public void close();

}
