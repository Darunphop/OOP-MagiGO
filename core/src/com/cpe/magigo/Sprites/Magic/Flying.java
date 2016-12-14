package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.math.Vector2;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class Flying extends Translocation{
    private float distance;
    public Flying() {
        distance = 1.35f;
    }

    @Override
    public void excecute(PlayScreen screen) {
        screen.getPlayer().b2body.setTransform(new Vector2(screen.getPlayer().b2body.getPosition().x,screen.getPlayer().b2body.getPosition().y+distance),screen.getPlayer().b2body.getAngle());
    }
}
