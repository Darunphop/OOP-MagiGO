package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.graphics.Texture;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.ElementType;

/**
 * Created by darunphop on 14-Nov-16.
 */
public class Recharge extends Magic{
    public Recharge() {
    }
    public Recharge(ElementType e, PlayScreen screen) {
        super(e, screen);
        screen.addMagicCharge();
    }

    @Override
    public void excecute(PlayScreen screen) {
        Recharge attack = new Recharge(element,screen);
    }
}
