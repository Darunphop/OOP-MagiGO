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
public class MagicMine extends Magic{
    private static int count = 3;
    private Texture texture;
    private Body body;
    private float damage = 20f;
    public MagicMine(ElementType e) {
        super(e);
    }
    public MagicMine(ElementType e,PlayScreen screen) {
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/mine/1.png"));
        this.body = createMagic(18f,0.3f*(screen.getPlayer().isRunningRight()?1:-1),-0.25f);
        body.setGravityScale(0);
        Sprite x = new Sprite(texture);
        x.setSize(40f/ MagiGO.PPM,40f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        bullets.add(body);
        count--;
    }

    @Override
    public void excecute(PlayScreen screen) {
        if (count>0){
            MagicMine attack = new MagicMine(element,screen);
        }
    }

    @Override
    public void hit() {
        count++;
        deconstruct();
    }
}
