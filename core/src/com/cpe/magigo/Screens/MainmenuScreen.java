package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.omg.CORBA.Current;

/**
 * Created by Toufu on 14/12/2559.
 */
public class MainmenuScreen implements Screen {
    private Game game;
    private Viewport viewport;
    private Stage stage;
    Texture Background;
    private SpriteBatch batch;
    private Music music;
    public MainmenuScreen(Game game){
        this.game=game;

        music = MagiGO.manager.get("soundtrack/Platscreen.ogg",Music.class);
        music.setLooping(true);
        music.play();
        viewport = new FitViewport(MagiGO.V_WIDTH,MagiGO.V_HEIGHT,new OrthographicCamera());
        Background = new Texture("scene/menu.png");
        stage = new Stage(viewport ,((MagiGO)game).batch);

        batch = new SpriteBatch();
    }
    @Override
    public void show() {

    }

    public void handleInput()
    {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            music.stop();
            game.setScreen(new PlayScreen((MagiGO)game));
            dispose();

        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Background,0,0);
        batch.end();
        stage.draw();
    }

    public void update()
    {
        handleInput();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);

    }
}
