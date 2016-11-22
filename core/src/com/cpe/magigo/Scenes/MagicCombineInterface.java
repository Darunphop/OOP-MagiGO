package com.cpe.magigo.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicCombineInterface implements Disposable {
    public Stage stage;
    private boolean flag;
    private Element elementCombine[];
    private SpriteBatch batch;
    private Image elementSet[];
    private Magician player;
    private Viewport viewport;

    public MagicCombineInterface(SpriteBatch sb, Magician player){
        this.batch = sb;
        viewport = new FitViewport(MagiGO.V_WIDTH,MagiGO.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        this.player = player;
        flag = false;
        elementSet = new Image[5];

//        for (int i = -2; i < 3; i++) {
//            Image tmp1 = new Image(new com.badlogic.gdx.graphics.Texture("element/fire.png"));
//            tmp1.setPosition((MagiGO.V_WIDTH/2.08f) + (i * 100),(MagiGO.V_HEIGHT/1.3f));
//            tmp1.setSize(77,77);
//            elementSet[i + 2] = tmp1;
//            stage.addActor(elementSet[i + 2]);
//        }

    }
    public void standby(){
        flag = true;
        stage.clear();
        elementCombine = player.getAttack().getCombine();
    }
    public void sleep(){
        flag = false;
    }
    public void update() {
        float startPoint = 0;
        stage.clear();
        if (flag){
            elementCombine = player.getAttack().getCombine();
            Gdx.app.log("MCI", new String(Integer.toString(player.getAttack().getSize())));
            switch (player.getAttack().getSize()){
                case 1:
                    Image bd1 = new Image(new com.badlogic.gdx.graphics.Texture("etc/1BD.png"));
                    bd1.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd1.setSize(500,100);
                    stage.addActor(bd1);
                    startPoint = (MagiGO.V_WIDTH/2.08f) - (0 * 100);
                    break;
                case 2:
                    Image bd2 = new Image(new com.badlogic.gdx.graphics.Texture("etc/2BD.png"));
                    bd2.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd2.setSize(500,100);
                    stage.addActor(bd2);
                    startPoint = (MagiGO.V_WIDTH/2.08f) - (50);
                    break;
                case 3:
                    Image bd3 = new Image(new com.badlogic.gdx.graphics.Texture("etc/3BD.png"));
                    bd3.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd3.setSize(500,100);
                    stage.addActor(bd3);
                    startPoint = (MagiGO.V_WIDTH/2.08f) - (1 * 100);
                    break;
                case 4:
                    Image bd4 = new Image(new com.badlogic.gdx.graphics.Texture("etc/4BD.png"));
                    bd4.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd4.setSize(500,100);
                    stage.addActor(bd4);
                    startPoint = (MagiGO.V_WIDTH/2.08f) - (150);
                    break;
                case 5:
                    Image bd5 = new Image(new com.badlogic.gdx.graphics.Texture("etc/5BD.png"));
                    bd5.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd5.setSize(500,100);
                    stage.addActor(bd5);
                    startPoint = (MagiGO.V_WIDTH/2.08f) - (2 * 100);
                    break;
                default:
                    Image bd0 = new Image(new com.badlogic.gdx.graphics.Texture("etc/0BD.png"));
                    bd0.setPosition((MagiGO.V_WIDTH/2.08f - 11 - (200) ) + (0 * 100),(MagiGO.V_HEIGHT/1.3f) - 11 );
                    bd0.setSize(500,100);
                    stage.addActor(bd0);
                    break;
            }
            for (int i = 0; i < player.getAttack().getSize(); i++) {
                Image tmp;
                switch (elementCombine[i].getElement()){
                    case FIRE:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/fire.png"));
                        break;
                    case WATER:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/water.png"));
                        break;
                    case WIND:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/wind.png"));
                        break;
                    case LIGHT:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/light.png"));
                        break;
                    case DARK:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/night.png"));
                        break;
                    default:
                        tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/night.png"));
                        break;
                }// end switch
                tmp.setPosition(startPoint + (i * 100),(MagiGO.V_HEIGHT/1.3f));
                tmp.setSize(77,77);
                elementSet[i] = tmp;
                stage.addActor(elementSet[i]);
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isReady(){
        return flag;
    }
}
