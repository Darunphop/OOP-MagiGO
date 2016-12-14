package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class MagicBolt extends Magic{
    private Texture texture;
    private Body body;
    private float speed = 5f;
    private float damage = 10f;

    public MagicBolt(ElementType e) {
        super(e);
    }
    public MagicBolt(ElementType e,PlayScreen screen) {
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        this.body = createMagic(6f,0.2f,0f);
        body.setGravityScale(0);
        Sprite x = new Sprite(texture);
        x.setSize(12f/MagiGO.PPM,12f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        if (!screen.getPlayer().isRunningRight())
            speed *= -1;
        body.setLinearVelocity(speed,0);
        bullets.add(body);

    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicBolt attack = new MagicBolt(element,screen);
    }

    @Override
    public void hit() {
        deconstruct();
    }
}
