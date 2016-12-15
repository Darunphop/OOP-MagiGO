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
public class MagicLaser extends Magic{
    private int count =2;
    private float time;
    private Texture texture;
    private Body body[];
    private float speed = 10f;
    private float damage = 1f;
    public MagicLaser(ElementType e) {
        super(e);
    }
    public MagicLaser(ElementType e,PlayScreen screen) {
        super(e, screen);
        setDmg(damage);
        time = 0;
        texture = new Texture(("magic/bolt/1.png"));
//        for (int i = 1; i < 9; i++) {
//            Body body;
//            if (!screen.getPlayer().isRunningRight())
//                speed = -3f;
//            body = createMagic(15, 0f, 0f);
//            body.setGravityScale(0);
//            Sprite x = new Sprite(texture);
//            x.setSize(9 / MagiGO.PPM, 9 / MagiGO.PPM);
//            x.setPosition(0, 0);
//            x.setColor(Element.getColor(element));
//            body.setUserData(x);
//            body.setLinearVelocity(speed, 0);
//            bullets.add(body);
//        }
    }

    @Override
    public void hit() {

    }

    @Override
    public void excecute(PlayScreen screen) {
        MagicLaser attack = new MagicLaser(element,screen);
    }

    @Override
    public void update(float dt) {
        time += dt;
        if (!(time > 0.5*screen.magicCharge)){
            //for (int i = 1; i < 9; i++) {
                Body body;
                if (!screen.getPlayer().isRunningRight())
                    speed = -10f;
                body = createMagic(15, 0f, 0f);
                body.setGravityScale(0);
                Sprite x = new Sprite(texture);
                x.setSize(9 / MagiGO.PPM, 9 / MagiGO.PPM);
                x.setPosition(0, 0);
                x.setColor(Element.getColor(element));
                body.setUserData(x);
                body.setLinearVelocity(speed, 0);
                bullets.add(body);

            screen.signalToClear = false;
            //}
        }else if(time >2){
            screen.useMagicCharge();
            deconstruct();
            screen.signalToClear = true;
        }

    }
}
