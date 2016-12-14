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

    public MagicBolt(ElementType e) {
        super(e);
    }
    public MagicBolt(ElementType e,PlayScreen screen) {
        super(e,screen);
        texture = new Texture(("magic/bolt/1.png"));
        this.body = createMagic();
        body.setGravityScale(0);
//        setRegion(new Texture(("element/element mini/fire2.png")));
        Sprite x = new Sprite(texture);
        x.setSize(12f/MagiGO.PPM,12f/MagiGO.PPM);
//        x.setPosition(body.getPosition().x-6f/MagiGO.PPM,body.getPosition().y-6f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        if (!screen.getPlayer().isRunningRight())
            speed *= -1;
        body.setLinearVelocity(speed,0);
        bullets.add(body);

    }

    @Override
    public void update() {
        for (Fixture fix:body.getFixtureList()){

        }
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicBolt attack = new MagicBolt(element,screen);
    }
}
