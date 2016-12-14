package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
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
    int flag = 0;
    private float damage = 8f;
    public MagicArray(ElementType e) {
        super(e);
    }
    public MagicArray(ElementType e,PlayScreen screen)  {
        super(e,screen);
        startTime = 0;
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        this.body = createMagic(80f,0,0);
        body.setGravityScale(0);
        x = new Sprite(texture);
        x.setSize(180f/ MagiGO.PPM,180f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        x.setAlpha(0.3f);
        body.setUserData(x);
        bullets.add(body);
    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicArray attack = new MagicArray(element,screen);
    }

    @Override
    public void update(float dt) {
        startTime += dt;
        if (startTime > 100*dt && flag==0){
            deconstruct();
            flag=1;
        }

    }
}
