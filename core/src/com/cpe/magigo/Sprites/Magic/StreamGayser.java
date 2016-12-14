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
    private float damage = 10f;
    public StreamGayser(ElementType e) {super(e);
    }
    public StreamGayser(ElementType e,PlayScreen screen) throws InterruptedException {
        super(e,screen);
        body = new Body[4];
        setDmg(damage);
        texture = new Texture(("magic/bolt/1.png"));
        for (int i = 0; i < 4; i++) {
            if (!screen.getPlayer().isRunningRight())
                speed = -1;
            this.body[i] = createMagic(10 + (14f * i), (0.2f + 0.4f * i) * speed, 0f);
            body[i].setGravityScale(0);
            Sprite x = new Sprite(texture);
            x.setSize((10 + (7 * i)) * 4 / MagiGO.PPM, (10 + (7 * i)) * 4 / MagiGO.PPM);
            //x.setPosition(screen.getPlayer().b2body.getPosition().x+((0.2f + 0.4f * i) * speed),screen.getPlayer().b2body.getPosition().y-(20 + (14f * i))/0.2f);
            x.setPosition(0,0);
            x.setColor(Element.getColor(element));
            x.setAlpha(0.8f);
            body[i].setUserData(x);

            bullets.add(body[i]);
        }
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                deconstruct();
            }
        }, 0.75f);

    }

    @Override
    public void excecute(PlayScreen screen)  {
        try {
            StreamGayser attack = new StreamGayser(element,screen);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
