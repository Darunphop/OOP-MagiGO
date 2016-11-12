package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;

/**
 * Created by Toufu on 9/11/2559.
 */
public class Magician extends Sprite {
    public World world;
    public Body b2body;

    public  Magician (World world)
    {
        this.world = world;
        defineMagician();
    }
    public  void  defineMagician()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MagiGO.PPM,32/ MagiGO.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody((bdef));

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MagiGO.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
