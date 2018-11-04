package com.gunmachan.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import asu.gunma.GunmaChan;

public class DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// This creates Windowed Borderless mode
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.width = GunmaChan.WIDTH;
		config.height = GunmaChan.HEIGHT;
		config.title = GunmaChan.TITLE;
		// Setting this to true creates Fullscreen mode
		//config.fullscreen = false;
		new LwjglApplication(new GunmaChan(), config);
	}
}
