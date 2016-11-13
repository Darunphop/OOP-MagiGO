package com.cpe.magigo.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;

/**
 * Created by MSI GP72 on 13/11/2559.
 */
public class B2WorldCreator {
    public B2WorldCreator(World world,TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create Ground object
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getY()+rect.getHeight() / 2) / MagiGO.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getHeight() /2)/ MagiGO.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}