package com.cpe.magigo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cpe.magigo.MagiGO;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Magigo";
		config.width = 1280;
		config.height = 768;
		new LwjglApplication(new MagiGO(), config);
	}
}
