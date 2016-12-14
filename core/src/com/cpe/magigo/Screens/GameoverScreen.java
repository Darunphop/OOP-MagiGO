package com.cpe.magigo.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cpe.magigo.Sprites.Cha_gameover;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import jdk.nashorn.internal.runtime.NumberToString;

import javax.print.DocFlavor;

/**
 * Created by Toufu on 14/12/2559.
 */
public class GameoverScreen implements Screen {
    private Game game;
    private Viewport viewport;
    private Stage stage;
    Texture Background;
    private int score;
    private int timer;
    private SpriteBatch batch;
    public GameoverScreen(Game game,int score,int timer) {
        this.game = game;
        this.timer = timer;
        this.score = score;
        viewport = new FitViewport(MagiGO.V_WIDTH, MagiGO.V_HEIGHT, new OrthographicCamera());
        Background = new Texture("scene/Gameover.png");
        stage = new Stage(viewport, ((MagiGO) game).batch);
        Label.LabelStyle timeLabel = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        batch = new SpriteBatch();

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOverLabel.setFontScale(3,3);
        Label gameOverLabels = new Label(String.format("%06d",timer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameOverLabels.setFontScale(3,3);
        Label playAgainLabel = new Label("Click to Play Again", timeLabel);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(gameOverLabels).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);
    }
    @Override
    public void show() {

    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            game.setScreen(new MainmenuScreen((MagiGO)game));
            dispose();
        }
        batch.draw(Background,0,0);
        batch.end();
        stage.draw();
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
