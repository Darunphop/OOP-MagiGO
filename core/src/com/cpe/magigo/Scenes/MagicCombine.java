package com.cpe.magigo.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicCombine implements Disposable {
    public Stage stage;
    private Viewport viewport;

    public MagicCombine(SpriteBatch sb){
        viewport = new FitViewport(MagiGO.V_WIDTH,MagiGO.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
