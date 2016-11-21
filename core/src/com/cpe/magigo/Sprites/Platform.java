package com.cpe.magigo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by MSI GP72 on 14/11/2559.
 */
public class Platform extends InteractiveTileObject {
    public Platform(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MagiGO.PLATFORM_BIT);

    }

    /*@Override
    public void onHeadHit() {
        Gdx.app.log("Platform", "Collision");
        setCategoryFilter(MagiGO.DESTROY_BIT);
    }

    @Override
    public void onLegHit() {
        Gdx.app.log("Platform", "Build");
        setCategoryFilter(MagiGO.PLATFORM_BIT);
    }*/
}
