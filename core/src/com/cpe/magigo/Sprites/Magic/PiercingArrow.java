package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class PiercingArrow extends Magic{
    private Texture texture;
    private Body body;
    private float speed = 5f;
    private float damage = 10f;
    public PiercingArrow(ElementType e) {
        super(e);
    }
    public PiercingArrow(ElementType e,PlayScreen screen) {
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/arrow/1.png"));
        this.body = createMagic(12f,0.2f,0f);
        body.setGravityScale(0);
        Sprite x = new Sprite(texture);
        x.setSize(35f/ MagiGO.PPM,12f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        if (!screen.getPlayer().isRunningRight())
            speed *= -1;
        body.setLinearVelocity(speed,0);
        bullets.add(body);

    }

    @Override
    public void excecute(PlayScreen screen) {
        PiercingArrow attack = new PiercingArrow(element,screen);
    }

    @Override
    public void hit() {
        setDmg(--damage);
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
