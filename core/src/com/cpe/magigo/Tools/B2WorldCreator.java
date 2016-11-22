package com.cpe.magigo.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Crystal;
import com.cpe.magigo.Sprites.Platform;

/**
 * Created by MSI GP72 on 13/11/2559.
 */
public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

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
    }
}
