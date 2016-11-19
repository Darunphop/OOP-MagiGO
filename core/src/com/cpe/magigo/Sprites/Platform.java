package com.cpe.magigo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;

/**
 * Created by MSI GP72 on 14/11/2559.
 */
public class Platform extends InteractiveTileObject {
    public Platform(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MagiGO.PLATFORM_BIT);

    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Platform", "Collision");
        setCategoryFilter(MagiGO.DESTROY_BIT);
    }

    @Override
    public void onLegHit() {
        Gdx.app.log("Platform", "Build");
        setCategoryFilter(MagiGO.PLATFORM_BIT);
    }
}
