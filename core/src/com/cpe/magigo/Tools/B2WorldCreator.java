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
import com.cpe.magigo.Sprites.Platform;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by MSI GP72 on 13/11/2559.
 */
public class B2WorldCreator {
    private PlayScreen screen;
    private Array<EnemyM> EnemyMs;
   /* private Array<EnemyM> EnemyMs2;
    private Array<EnemyM> EnemyMs3;
    private Array<EnemyM> EnemyMs4;
    private Array<EnemyM> EnemyMs5;
    private Array<EnemyM> EnemyMs6;*/
    private PlayScreen play;
    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        EnemyMs = new Array<EnemyM>();
        play = screen;

        //create Ground object
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getY()+rect.getHeight() / 2) / MagiGO.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getHeight() /2)/ MagiGO.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //create Platform object
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Platform(screen, rect);
        }
        //create frame object
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getY()+rect.getHeight() / 2) / MagiGO.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getHeight() /2)/ MagiGO.PPM );
            fdef.shape = shape;
            fdef.filter.categoryBits = MagiGO.OBJECT_BIT;
            body.createFixture(fdef);
        }
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Crystal(screen, rect);
        }

        //create all malee Enemy

    }

    public Array<EnemyM> getEnemyMs() {
        return EnemyMs;
    }


    public void update(float dt){
        World world = play.getWorld();
        TiledMap map = play.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        int n = (int )(Math.random() * 6 + 1);
           // if(play.Timecount <= 10){
                while (play.Timer > 1f) {
                    play.Timer -= 5f;
                    switch (n) {
                    case 1:
                        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                    case 2:
                        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class )) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                    case 3:
                        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class )) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                    case 4:
                        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class )) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                    case 5:
                        for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class )) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                    case 6:
                        for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class )) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                            EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                        }
                        break;
                }

          //  }
        }

      /*  else if(play.Timecount <=20)
        {
            while(play.Timer1 >1f  ) {
                for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class )) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    EnemyMs.add(new EnemyM(play, rect.getX() / MagiGO.PPM, rect.getY() / MagiGO.PPM));
                }
                play.Timer1 -=3f;
            }
        }*/
    }
}

