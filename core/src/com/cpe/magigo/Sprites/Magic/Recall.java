package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.math.Vector2;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class Recall extends Translocation{
    private float targetx = 6.4f;
    private float targety = 4.5249f;
    public Recall() {
    }

    @Override
    public void excecute(PlayScreen screen) {
        screen.getPlayer().b2body.setTransform(new Vector2(targetx,targety),screen.getPlayer().b2body.getAngle());
    }
}
