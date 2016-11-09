package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

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
        bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody((bdef));

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
