package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Sprites.Cha_gameover;
import javafx.stage.Stage;

/**
 * Created by Toufu on 14/12/2559.
 */
public class GameoverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    MagiGO game;
    private World world;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    Cha_gameover cha_gameover;
    public GameoverScreen(MagiGO game)
    {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MagiGO.V_WIDTH / MagiGO.PPM, MagiGO.V_HEIGHT / MagiGO.PPM,gamecam);
        cha_gameover = new Cha_gameover(this);
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MagiGO.V_WIDTH / MagiGO.PPM, MagiGO.V_HEIGHT / MagiGO.PPM,gamecam);
        world = new World(new Vector2(0,0) , true);
    }
    @Override
    public void show() {

    }

    public void update(float dt)
    {
        cha_gameover.update(dt);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        cha_gameover.draw(game.batch);
    }

    public World getWorld()
    {
        return world;
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
