package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by Asuka on 20/11/2559.
 */
public class EnemyM extends Enemy {
    private float statetime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    public EnemyM(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for ( int i = 0 ; i < 5 ; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("MagicianNew"), i * 39 , 0 , 39 , 60  ));
        walkAnimation = new Animation(0.4f , frames);
        statetime = 0;
        setBounds(getX(),getY() ,16/MagiGO.PPM , 16/MagiGO.PPM);
    }

    public void update(float dt)
    {
        statetime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(statetime , true));
    }

    @Override
    protected void defineEnemy()
    {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(512/ MagiGO.PPM,384/ MagiGO.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(17 / MagiGO.PPM);
        fdef.filter.categoryBits = MagiGO.ENEMY_BIT;
        fdef.filter.maskBits = MagiGO.DEFAULT_BIT | MagiGO.PLATFORM_BIT | MagiGO.ENEMY_BIT | MagiGO.MAGIGO_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
