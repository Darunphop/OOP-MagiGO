package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.ElementType;
import com.cpe.magigo.System.Status;

/**
 * Created by Asuka on 26/11/2559.
 */
public class EnemyR extends Enemy {
    private float statetime;
    private Animation walkAnimation;
    public float hp_p=50;
    private Array<TextureRegion> frames;
    public EnemyR(PlayScreen screen, float x, float y, ElementType e,float dt) {
        super(screen, x, y,e);
        frames = new Array<TextureRegion>();
        for ( int i = 0 ; i < 3 ; i++)
            frames.add(new TextureRegion(screen.getAtlastMon().findRegion("monster_R"), i * 45 , 0 , 45 , 70  ));
        walkAnimation = new Animation(0.1f , frames);
        statetime = 0;
        setBounds(getX(),getY() ,60/ MagiGO.PPM , 70/MagiGO.PPM);
        if(dt%60==0)
            hp_p += 50f;
        this.status = new Status(hp_p,10,1f, ElementType.NEUTRAL);
    }

    public void update(float dt)
    {
            statetime += dt;
            setRegion(getFrame());
            if(b2body.getPosition().x > 640/MagiGO.PPM){
                b2body.setLinearVelocity(velocity4);
            }
            else
                b2body.setLinearVelocity(velocity3);
            setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
            setRegion(walkAnimation.getKeyFrame(statetime , true));

    }

    public TextureRegion getFrame() {
        TextureRegion region;
        region = walkAnimation.getKeyFrame(statetime,true);
        if(b2body.getLinearVelocity().x > 0 && region.isFlipX() == false){
            region.flip(false, false);
        }
        if(b2body.getLinearVelocity().x < 0 && region.isFlipX() == false){
            region.flip(true, false);
        }
        if(b2body.getLinearVelocity().x > 0 && region.isFlipX() == true){
            region.flip(true, false);
        }
        return region;
    }
    @Override
    protected void defineEnemy()
    {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(getX(),getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / MagiGO.PPM);
        fdef.filter.categoryBits = MagiGO.BOSS_BIT;
        fdef.filter.maskBits = MagiGO.DEFAULT_BIT | MagiGO.PLATFORM_BIT | MagiGO.MAGIGO_BIT | MagiGO.CRYSTAL_BIT | MagiGO.OBJECT_BIT | MagiGO.MAGIC_BIT| MagiGO.MAGIC_OB_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    public void draw(Batch batch)
    {
        super.draw(batch);
    }
}