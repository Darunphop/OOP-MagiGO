package com.cpe.magigo.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by MSI GP72 on 13/11/2559.
 */
public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen play;

    protected Fixture fixture;

    public  InteractiveTileObject(PlayScreen screen, Rectangle bounds){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / MagiGO.PPM, (bounds.getY() + bounds.getHeight() / 2) / MagiGO.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / MagiGO.PPM, bounds.getHeight() / 2 / MagiGO.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);

    }
    //public abstract void onHeadHit();
    //public abstract void onLegHit();


    public void setCategoryFilter(short fileterbit){
        Filter filter = new Filter();
        filter.categoryBits = fileterbit;
        fixture.setFilterData(filter);
    }
}
