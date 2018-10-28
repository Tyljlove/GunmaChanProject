package asu.gunma;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GunmaChan extends Game {
	// Temporary values
	public static int WIDTH = 480;
	public static int HEIGHT = 720;

	public static final String TITLE = "Gunma-chan Game";

	private SpriteBatch batch; // There should only be one of these in your game
	private Texture img;
	
	@Override
	public void create() {

		batch = new SpriteBatch();
		img = new Texture("super_gunma.png");
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1); // Does not need to happen each tick
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // DOES need to happen each tick
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
