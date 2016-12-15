package com.cpe.magigo.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.InteractiveTileObject;
import com.cpe.magigo.System.ElementType;

/**
 * Created by MSI GP72 on 22/11/2559.
 */
public class Crystal extends InteractiveTileObject {
    private float dmg;
    private ElementType elementType;
    public Crystal(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MagiGO.CRYSTAL_BIT);
        play = screen;
        dmg = 100;
        elementType = ElementType.NEUTRAL;
    }

    public void hit()
    {
        play.status_Crystal.setCurrentHP(play.status_Crystal.getCurrentHP()-5);
    }
    public void hit2()
    {
        play.status_Crystal.setCurrentHP(play.status_Crystal.getCurrentHP()-20);
    }

    public float getDmg(){return dmg;}
    public ElementType getElement(){return elementType;}
}

