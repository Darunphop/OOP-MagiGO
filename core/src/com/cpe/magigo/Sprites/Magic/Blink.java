package com.cpe.magigo.Sprites.Magic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class Blink extends Translocation{
    private float distance;
    public Blink() {
        distance = 1.95f;
    }

    @Override
    public void excecute(PlayScreen screen) {
        if (!screen.getPlayer().isRunningRight())
            distance *= -1;
        float target = screen.getPlayer().b2body.getPosition().x+distance;
        if (target < 0.2f){
            target = 0.3f;
        }else if (target >12.6f){
            target = 12.5f;
        }
        screen.getPlayer().b2body.setTransform(new Vector2(target,screen.getPlayer().b2body.getPosition().y),screen.getPlayer().b2body.getAngle());
    }


}
