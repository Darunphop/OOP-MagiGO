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
public class MagicBullet extends Magic{
    private int count =2;
    private Texture texture;
    private Body body[];
    private float speed = 1.5f;
    private float damage = 8f;
    public MagicBullet(ElementType e) {
        super(e);
    }
    public MagicBullet(ElementType e,PlayScreen screen){
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        for (int i = 1; i < 9; i++) {
            Body body;
            if (!screen.getPlayer().isRunningRight())
                speed = -1.5f;
            body = createMagic(9, 0f, 0f);
            body.setGravityScale(0);
            Sprite x = new Sprite(texture);
            x.setSize( 9 / MagiGO.PPM, 9 / MagiGO.PPM);
            x.setPosition(0,0);
            x.setColor(Element.getColor(element));
            body.setUserData(x);
            body.setLinearVelocity(speed,speed*speed * (i)/10);
            bullets.add(body);
        }

    }

    @Override
    public void hit() {
        if(--count ==0)
            deconstruct();
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicBullet attack = new MagicBullet(element,screen);
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
