package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cpe.magigo.Screens.PlayScreen;

/**
 * Created by Asuka on 20/11/2559.
 */
public abstract class Enemy extends Sprite{
    protected World world;
    protected PlayScreen sceen;
    public Body b2body;
    public Enemy(PlayScreen screen ,float x , float y)
    {
        this.world = screen.getWorld();
        this.sceen = screen;
        setPosition(x , y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
}
