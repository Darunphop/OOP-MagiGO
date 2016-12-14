package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Sprites.Magic.Magic;
import javafx.stage.Stage;

/**
 * Created by Toufu on 14/12/2559.
 */
public class GameoverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GameoverScreen(Game game)
    {
        this.game = game;
        viewport = new FitViewport(MagiGO.V_WIDTH,MagiGO.V_HEIGHT,new OrthographicCamera());
       // stage = new Stage(viewport,((MagiGO)game).batch);
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

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

    }
}
