package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by MSI GP72 on 14/12/2559.
 */
public class Cha_gameover extends Sprite {

    protected World world;
    protected PlayScreen sceen;
    public Body b2body;
    private float statetime;
    private Sprite character;
    private Texture pr = new Texture("character/source_gameover.png");
    private TextureRegion pr_r ;
    private Animation stunAnimation;
    private Array<TextureRegion> frames;
    public Cha_gameover(PlayScreen screen)
    {
        this.world = screen.getWorld();
        this.sceen = screen;
        frames = new Array<TextureRegion>();
        for ( int i = 0 ; i < 3 ; i++)
            frames.add(new TextureRegion(pr,i * 60 , 0 , 50 , 65   ));
        stunAnimation = new Animation(0.1f , frames);
        setBounds(getX(),getY() ,60/ MagiGO.PPM , 60/MagiGO.PPM);
        definegameover();
    }

    public void definegameover() {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(getX(), getY());
        bodydef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(60 / MagiGO.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

    }

    public void update(float dt)
    {
        statetime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(stunAnimation.getKeyFrame(statetime , true));
    }

}
