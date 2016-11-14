package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by Asuka on 13/11/2559.
 */
public class Magician extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD, CASTING};
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;

    private float stateTimer;
    private boolean runningRight;
    private boolean timeToRedefineMagician;
    private boolean magicianIsDead;
    private PlayScreen screen;

    public Magician (World world)
    {
        this.world = world;
        defineMagician();
    }

    public void defineMagician()
    {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(512/ MagiGO.PPM,384/ MagiGO.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MagiGO.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public State getState(){
        //Test to Box2D for velocity on the X and Y-Axis
        //if mario is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
        if(magicianIsDead)
            return State.DEAD;
        else if((b2body.getLinearVelocity().y > 0 && currentState == State.JUMPING) || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
            //if negative in Y-Axis mario is falling
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
            //if mario is positive or negative in the X axis he is running
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
            //if none of these return then he must be standing
        else
            return State.STANDING;
    }

    public boolean isDead(){
        return magicianIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }


    public void jump(){
        if ( currentState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
}
