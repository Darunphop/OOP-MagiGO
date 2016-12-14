package com.cpe.magigo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpe.magigo.Screens.PlayScreen;

public class MagiGO extends Game {
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 768;
	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short MAGIGO_BIT = 2;
	public static final short PLATFORM_BIT = 4;
	public static final short MAGIC_BIT = 8;
	public static final short MAGIGO_HEAD_BIT = 16;
	public static final short MAGIGO_LEG_BIT = 32;
	public static final short MAGIGO_JUMP_BIT = 64;
	public static final short OBJECT_BIT = 128;
	public static final short ENEMY_BIT = 256;
	public static final short CRYSTAL_BIT = 512;

	public SpriteBatch batch;


	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
