package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Enemy;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class ElementalShield extends Magic{
    private int count =2;
    private Texture texture;
    private Body body[];
    private float speed = 1.5f;
    private float damage = 8f;
    public ElementalShield(ElementType e) {
        super(e);
    }
    public ElementalShield(ElementType e,PlayScreen screen){
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        for (int i = -1; i <= 1; i++) {
            Body body;
            body = createMagic(50, -screen.getPlayer().b2body.getPosition().x + MagiGO.V_WIDTH/2/MagiGO.PPM,-screen.getPlayer().b2body.getPosition().y + MagiGO.V_HEIGHT/2/MagiGO.PPM
                    - 0.3f + (1.3f*i));
            body.setGravityScale(0);
            body.setType(BodyDef.BodyType.StaticBody);
            body.getFixtureList().get(0).getFilterData().categoryBits = MagiGO.MAGIC_OB_BIT;
            body.getFixtureList().get(0).setSensor(false);
            Sprite x = new Sprite(texture);
            x.setSize( 9 / MagiGO.PPM, 9 / MagiGO.PPM);
            x.setPosition(0,0);
            x.setColor(Element.getColor(element));
            body.setLinearVelocity(0, 0);
            body.setUserData(x);
            bullets.add(body);
        }

    }

    @Override
    public void hit(Enemy e) {
       if(new Element(element).isWeak(e.element)){
            deconstruct();
        }else if(e.element == ElementType.NEUTRAL && element == ElementType.NEUTRAL)
            deconstruct();
    }

    @Override
    public void excecute(PlayScreen screen) {
        ElementalShield attack = new ElementalShield(element,screen);

    }

    private float time = 0;
    @Override
    public void update(float dt) {
        time += dt;
        if (time > 500*dt){
            deconstruct();
        }
    }
}
