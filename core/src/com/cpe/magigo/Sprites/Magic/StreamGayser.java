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
public class StreamGayser extends Magic{
    private Texture texture;
    private Body body[];
    private float speed = 1f;
    private float damage = 5f;
    public StreamGayser(ElementType e) {super(e);
    }
    public StreamGayser(ElementType e,PlayScreen screen){
        super(e,screen);
//        body = new Body[4];
        setDmg(damage);
        texture = new Texture(("magic/geyser/1.png"));
        for (int i = 0; i < 4; i++) {
            Body body;
            if (!screen.getPlayer().isRunningRight())
                speed = -1;
            body = createMagic(10 + (14f * i), (0.2f + 0.4f * i) * speed, 0f);
            body.setGravityScale(0);
            Sprite x = new Sprite(texture);
            x.setSize((10 + (7 * i)) * 4 / MagiGO.PPM, (10 + (7 * i)) * 4 / MagiGO.PPM);
            x.setColor(Element.getColor(element));
            x.setAlpha(0.9f);
            body.setUserData(x);

            bullets.add(body);
        }
//        Timer.schedule(new Timer.Task(){
//            @Override
//            public void run() {
//                deconstruct();
//            }
//        }, 0.35f);

    }

    @Override
    public void excecute(PlayScreen screen)  {
            StreamGayser attack = new StreamGayser(element,screen);

    }
    private float time = 0;
    @Override
    public void update(float dt) {
        time += dt;
        if (time > 20*dt){
            deconstruct();
        }
    }
}
