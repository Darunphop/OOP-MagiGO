package com.cpe.magigo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpe.magigo.Screens.GameoverScreen;
import com.cpe.magigo.Screens.MainmenuScreen;
import com.cpe.magigo.Screens.PlayScreen;
import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;

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
	public static final short MAGIC_OB_BIT = 64;
	public static final short OBJECT_BIT = 128;
	public static final short ENEMY_BIT = 256;
	public static final short CRYSTAL_BIT = 512;
	public static final short BOSS_BIT = 1024;

	public SpriteBatch batch;
	public static AssetManager manager;


	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("soundtrack/Main.ogg",Music.class);
		manager.load("soundtrack/Platscreen.ogg",Music.class);
		manager.load("soundtrack/Gameover.ogg",Music.class);
		manager.load("soundtrack/Casting.ogg",Music.class);
		manager.load("soundtrack/Mon_die.ogg",Music.class);
		manager.finishLoading();
		setScreen(new MainmenuScreen(this));
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
