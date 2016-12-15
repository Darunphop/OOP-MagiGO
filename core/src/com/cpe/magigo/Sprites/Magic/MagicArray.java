package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Enemy;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class MagicArray extends Magic{
    private Texture texture;
    private float startTime;
    private Body body;
    Sprite x;
    private float damage = 8f;
    public MagicArray(ElementType e) {
        super(e);
    }
    public MagicArray(ElementType e,PlayScreen screen)  {
        super(e,screen);
        startTime = 0;
        setDmg(damage);
        texture = new Texture(("magic/array/1.png"));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(screen.getPlayer().b2body.getPosition().x,screen.getPlayer().b2body.getPosition().y);

        Body body = screen.getWorld().createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(80f/ MagiGO.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.filter.categoryBits = MagiGO.MAGIC_OB_BIT;
        fixtureDef.filter.maskBits =  MagiGO.ENEMY_BIT | MagiGO.MAGIC_OB_BIT;
        fixtureDef.isSensor = true;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);


        body.setGravityScale(0);
        x = new Sprite(texture);
        x.setSize(180f/ MagiGO.PPM,180f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        x.setAlpha(0.3f);
        body.setUserData(x);
        bullets.add(body);
    }

    @Override
    public void hit(Enemy e) {
        float speedx = (new Element(e.element).isWeak(element))?0f:e.velocity.x/1.5f;
        float speedx2 = (new Element(e.element).isWeak(element))?0f:e.velocity2.x/1.5f;
        e.velocity = new Vector2(speedx,0);
        e.velocity2 = new Vector2(speedx2,0);
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicArray attack = new MagicArray(element,screen);
    }

    @Override
    public void update(float dt) {
        startTime += dt;
        if (startTime > 100*dt){
            deconstruct();
        }

    }
}
