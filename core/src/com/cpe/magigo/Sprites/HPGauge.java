package com.cpe.magigo.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

public class HPGauge extends Sprite
{
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    private float statetime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;//full gauge
    private Array<TextureRegion> frame;//empty gauge
    public HPGauge(PlayScreen screen ,int HP)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        frame = new Array<TextureRegion>();
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlastHP().findRegion("fullhealth_bar"),0 , 0 , HP*(826/100) , 83));
        frame.add(new TextureRegion(screen.getAtlastHP().findRegion("health_bar"),0 , 0 , (100-HP)*(826/100) , 83));
        walkAnimation = new Animation(0.1f , frames);
        statetime = 0;
        setBounds(getX(),getY() ,HP*6/MagiGO.PPM , 40/MagiGO.PPM);
        defineHP();
    }

    protected void defineHP()
    {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set((MagiGO.V_WIDTH/2)/ MagiGO.PPM,130/ MagiGO.PPM);
        bodydef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / MagiGO.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(int dt)
    {
        statetime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(statetime , true));
    }
}
