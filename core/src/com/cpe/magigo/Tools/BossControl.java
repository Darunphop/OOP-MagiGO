package com.cpe.magigo.Tools;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Scenes.Hud;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Crystal;
import com.cpe.magigo.Sprites.EnemyM;
import com.cpe.magigo.Sprites.EnemyR;
import com.cpe.magigo.Sprites.Platform;
import com.cpe.magigo.System.ElementType;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by MSI GP72 on 13/11/2559.
 */
public class BossControl {
    private PlayScreen screen;
    private Array<EnemyR> EnemyRs;

    private PlayScreen play;

    public BossControl(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        EnemyRs = new Array<EnemyR>();
        play = screen;


    }

    public Array<EnemyR> getEnemyRs() {
        return EnemyRs;
    }


    public void update(float dt) {
        World world = play.getWorld();
        TiledMap map = play.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        int n = (int) (Math.random() * 6 + 1);
        int m = (int) (Math.random() * 2 + 1);

        //level-1
            while(play.Timer2 > 1){
                switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                }
                n = (int) (Math.random() * 6 + 1);
                play.Timer2 -= 60f;
            }
        }
        //level-2
     /*   else if (play.Timecount <= 120) {
            while(play.Timer2 > 60){
                switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                }
                n = (int) (Math.random() * 6 + 1);
                play.Timer2 -= 60f;
            }
        }
        //level-3
        else if (play.Timecount <= 180) {
            while(play.Timer2 > 60){
                switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                }
                n = (int) (Math.random() * 6 + 1);
                play.Timer2 -= 60f;
            }
        }
        //level-4
        else if (play.Timecount <= 240) {
            while(play.Timer2 > 60){
                switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                }
                n = (int) (Math.random() * 6 + 1);
                play.Timer2 -= 60f;
            }
        }
        else if (play.Timecount >240) {
            while(play.Timer2 > 60){
                switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyRs.add(new EnemyR(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM, ElementType.NEUTRAL, dt));
                        }
                        break;
                }
                n = (int) (Math.random() * 6 + 1);
                play.Timer2 -= 60f;
            }
        }


    }*/
}


