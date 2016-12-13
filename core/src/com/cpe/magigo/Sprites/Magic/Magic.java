package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.System.ElementType;

/**
 * Created by ICQCQ on 15-Nov-16.
 */
public abstract class Magic {
    protected ElementType element;
    public Magic(ElementType e) {
        this.element = e;
    }

    public Magic() {
    }

    public abstract void excecute(PlayScreen screen);

}
