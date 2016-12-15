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
public class ChainLighting extends Magic{
    private float startTime=0;
    private int charge = 3;
    private Texture texture;
    private Body body;
    private float speed = 5f;
    private float damage = 8f;
    public ChainLighting(ElementType e) {
        super(e);
    }
    public ChainLighting(ElementType e,PlayScreen screen) {
        super(e,screen);
        setDmg(damage);
        texture = new Texture(("magic/chain/1.png"));
        this.body = createMagic(8f,0.2f,-0.18f);
        body.setGravityScale(0);
        Sprite x = new Sprite(texture);
        x.setSize(12f/ MagiGO.PPM,12f/MagiGO.PPM);
        x.setColor(Element.getColor(element));
        body.setUserData(x);
        if (!screen.getPlayer().isRunningRight())
            speed *= -1;
        body.setLinearVelocity(speed,0);
        bullets.add(body);

    }

    @Override
    public void hit() {
        if (charge==3){
            body.setLinearVelocity(-speed,0);
            setDmg(1.25f*damage);
            charge--;
        }else if (charge==2){
            body.setLinearVelocity(speed,0);
            setDmg(1.5f*damage);
            charge--;
        }else if (charge == 1){
            body.setLinearVelocity(speed,0);
            setDmg(1.8f*damage);
            charge--;
        }else if (charge == 0){
            body.setLinearVelocity(-speed,0);
            setDmg(2f*damage);
            charge--;
        }
    }

    @Override
    public void update(float dt) {
        startTime += dt;
        if (startTime > 500*dt){
            deconstruct();
        }
    }

    @Override
    public void excecute(PlayScreen screen) {
        ChainLighting attack = new ChainLighting(element,screen);

    }
}
