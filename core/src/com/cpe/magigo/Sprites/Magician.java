package com.cpe.magigo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Magic.Magic;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;
import com.cpe.magigo.System.MagicSequence;
import com.cpe.magigo.System.Status;

/**
 * Created by Asuka on 13/11/2559.
 */
public class Magician extends Sprite {
    public int Health = 100;
    public static int MaxHealth = 100;
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD, CASTING}

    public State currentState;
    public State previousState;
    private MagicSequence attack;
    private Status status;

    public World world;
    public Body b2body;
    private TextureRegion MagicianStand;
    private Animation magicianRun;
    private Animation magicianJump;
    private Animation magicianDead;

    private float stateTimer;
    private boolean runningRight;
    private boolean timeToRedefineMagician;
    private boolean magicianIsDead;
    private PlayScreen screen;
    private FixtureDef fdef;

    public Magician ( PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("character"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        status = new Status();
        this.screen = screen;
        status.setMaxHP(100);
        status.setCurrentHP(100);


        Array<TextureRegion> frame = new Array<TextureRegion>();
        //Animation Magician walk
        for(int i = 3 ; i < 7 ; i++)
        {
            frame.add(new TextureRegion(getTexture(),i*62 , 5 , 62 , 60));
        }
        magicianRun = new Animation(0.1f , frame);
        //Animation Magician jump
        for(int i = 0 ; i < 2 ; i++)
        {
            frame.add(new TextureRegion(getTexture(),i*62  , 76 , 62 , 60 ));
        }
        magicianJump = new Animation(0.1f , frame);
        for(int i = 0 ; i < 9 ;i++)
        {
            frame.add(new TextureRegion(getTexture(),((i*62) + 124),74,62,60));
        }
        magicianDead = new Animation(0.1f , frame);
        defineMagician();
        MagicianStand = new TextureRegion(getTexture(),0,5,60,60);
        setBounds(0 / MagiGO.PPM,0 / MagiGO.PPM,60 / MagiGO.PPM , 60 / MagiGO.PPM);
        setRegion(MagicianStand);
    }

    public void update (float dt)
    {

        setPosition(b2body.getPosition().x - getWidth() /2 ,b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
//        Gdx.app.log("POS", ""+( b2body.getPosition().x +" , "+ b2body.getPosition().y)) ;


    }




    public TextureRegion getFrame (float dt)
    {
        currentState = getState();

        TextureRegion region ;

        switch (currentState)
        {
            case DEAD:
                region = magicianDead.getKeyFrame(stateTimer);
                break;
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
            region.flip(true,false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
        {
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer +dt :0;
        previousState = currentState;
        return region;

    }

    public void defineMagician()
    {
        status = new Status(100f,100f,1,1,new Element(ElementType.NEUTRAL));
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(640/ MagiGO.PPM,384/ MagiGO.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / MagiGO.PPM);
        fdef.filter.categoryBits = MagiGO.MAGIGO_BIT;
        fdef.filter.maskBits = MagiGO.DEFAULT_BIT | MagiGO.PLATFORM_BIT | MagiGO.ENEMY_BIT | MagiGO.OBJECT_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / MagiGO.PPM, 17 / MagiGO.PPM), new Vector2(2 / MagiGO.PPM, 17 / MagiGO.PPM));
        fdef.filter.categoryBits = MagiGO.MAGIGO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");

        EdgeShape leg = new EdgeShape();
        leg.set(new Vector2(-2 / MagiGO.PPM, -17 / MagiGO.PPM), new Vector2(2 / MagiGO.PPM, -17 / MagiGO.PPM));
        fdef.filter.categoryBits = MagiGO.MAGIGO_LEG_BIT;
        fdef.shape = leg;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("leg");

    }

    public State getState(){
        //Test to Box2D for velocity on the X and Y-Axis
        //if mario is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
        if(magicianIsDead)
            return State.DEAD;
        else if (currentState == State.CASTING)
            return State.CASTING;
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
        if (currentState != State.CASTING){
            attack = new MagicSequence();
            currentState = State.CASTING;
            Gdx.app.log("State", "CASTING");
        }else {//excecute spell
            //Gdx.app.log("State", attack.sequenceAnalyse().getClass().getName());
            attack.sequenceAnalyse().excecute(screen);

            cancelCasting();
        }
    }

    public void cancelCasting(){
        Gdx.app.log("State", "CANCEL CASTING");
        attack.clear();
        currentState = State.STANDING;
    }

    public void casting(Element e){
        attack.addElement(e);
    }

    public boolean isDead(){
        return magicianIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }

    public Status getStatus() {
        return status;
    }

    public void jump(){
        if ( getState() != State.JUMPING  && getState() != State.FALLING) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
    public MagicSequence getAttack(){
        return this.attack;
    }

    public void hit()
    {
        Health -= 10;
        if(Health <= 0)
            magicianIsDead = true;
    }

    public boolean isRunningRight() {
        return runningRight;
    }

}
