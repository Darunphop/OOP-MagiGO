package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
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
    private TextureRegion MagicianStand;
    private Animation magicianRun;
    private Animation magicianJump;

    private float stateTimer;
    private boolean runningRight;
    private boolean timeToRedefineMagician;
    private boolean magicianIsDead;
    private PlayScreen screen;

    public Magician (World world , PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("Magician"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;


        Array<TextureRegion> frame = new Array<TextureRegion>();
        //Animation Magician walk
        for(int i = 1 ; i < 5 ; i++)
        {
            frame.add(new TextureRegion(getTexture(),i*66 , 10 , 69 , 69));
        }
        magicianRun = new Animation(0.1f , frame);
        //Animation Magician jump
        for(int i = 0 ; i < 2 ; i++)
        {
            frame.add(new TextureRegion(getTexture(),i*10 , 10 , 69 , 69 ));
        }
        magicianJump = new Animation(0.1f , frame);
        defineMagician();
        MagicianStand = new TextureRegion(getTexture(),10,10,69,69);
        setBounds(10 / MagiGO.PPM,10 / MagiGO.PPM,68 / MagiGO.PPM , 69 / MagiGO.PPM);
        setRegion(MagicianStand);
    }

    public void update (float dt)
    {
        setPosition(b2body.getPosition().x - getWidth() /2 ,b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame (float dt)
    {
        currentState = getState();

        TextureRegion region ;

        switch (currentState)
        {
            case JUMPING:
                region = magicianJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = magicianRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = MagicianStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
        {
            //region.flip(true,false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
        {
            region.isFlipY();
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer +dt :0;
        previousState = currentState;
        return region;

    }

    public void defineMagician()
    {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(512/ MagiGO.PPM,384/ MagiGO.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(17 / MagiGO.PPM);

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

    public void casting(){

    }

    public boolean isDead(){
        return magicianIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }


    public void jump(){
        if ( getState() != State.JUMPING  && getState() != State.FALLING) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
}
