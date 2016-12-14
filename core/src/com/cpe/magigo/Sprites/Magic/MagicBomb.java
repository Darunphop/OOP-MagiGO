package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;

import java.util.ArrayList;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class MagicBomb extends Magic{
    private Texture texture;
    private Body body;
    private float speed = 4f;
    private float damage = 10f;
    public MagicBomb(ElementType e) {
        super(e);
    }
    public MagicBomb(ElementType e,PlayScreen screen) {
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        this.body = createMagic(6f,0.2f*(screen.getPlayer().isRunningRight()?1:-1),0.2f);
        body.setGravityScale(2);
        Sprite x = new Sprite(texture);
        x.setSize(30f/ MagiGO.PPM,30f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        if (!screen.getPlayer().isRunningRight())
            speed *= -1;
        body.setLinearVelocity(speed,5);
        bullets.add(body);

    }

    @Override
    public void hit() {
        deconstruct();
    }

    @Override
    public void hitFloor() {
        deconstruct();
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicBomb attack = new MagicBomb(element,screen);
    }

}
