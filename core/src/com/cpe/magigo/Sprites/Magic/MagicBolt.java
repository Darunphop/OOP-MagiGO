package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class MagicBolt extends Magic{
    private Texture texture;
    private Body body;
    public MagicBolt(ElementType e) {
        super(e);
    }
    public MagicBolt(ElementType e,PlayScreen screen) {
        super(e,screen);
        texture = new Texture(("magic/bolt/1.png"));
        this.body = createMagic();
        body.setGravityScale(0);
        setTexture(texture);
        screen.magics.add(this);
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicBolt attack = new MagicBolt(element,screen);
    }
}
