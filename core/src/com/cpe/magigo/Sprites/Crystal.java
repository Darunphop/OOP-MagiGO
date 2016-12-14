package com.cpe.magigo.Sprites;

import com.badlogic.gdx.math.Rectangle;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.InteractiveTileObject;

/**
 * Created by MSI GP72 on 22/11/2559.
 */
public class Crystal extends InteractiveTileObject {
    public Crystal(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MagiGO.CRYSTAL_BIT);
        play = screen;

    }

    public void hit()
    {
        play.status_Crystal.setCurrentHP(play.status_Crystal.getCurrentHP()-3);
    }
}
