package com.cpe.magigo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cpe.magigo.Scenes.Hud;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;
import com.cpe.magigo.System.Status;

/**
 * Created by Asuka on 20/11/2559.
 */
public abstract class Enemy extends Sprite{
    protected World world;
    protected PlayScreen sceen;
    protected Status status;
    public ElementType element;
    public Body b2body;
    public Vector2 velocity;
    public Vector2 velocity2;

    public Enemy(PlayScreen screen ,float x , float y,ElementType e)
    {
        this.world = screen.getWorld();
        this.sceen = screen;
        this.element = e;
        setPosition(x , y);
        defineEnemy();
        this.status = new Status();
        velocity = new Vector2(0.8f,-1);
        velocity2 = new Vector2(-0.8f,-1);
       // element = ElementType.NEUTRAL;
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);

    public void reversVelocity(boolean x ,boolean y)
    {
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }

    public void hit(float dmg, ElementType eatk){
        this.status.damageCal(dmg,this.element,eatk);
        Gdx.app.log("Enemy ", "Hp = " + status.getCurrentHP());
    }
    public boolean isDead(){
        return status.getCurrentHP() == 0f;
    }
    public void deconstruct(){
        sceen.deleteList.add(b2body);
    }
    public void deconstruct2(){
        Hud.addScore(100);
        sceen.deleteList.add(b2body);
    }

    @Override
    public void draw(Batch batch) {
        if (!isDead())
         super.draw(batch);
    }
    public Status getStatus(){return status;}

    public ElementType getElement() {
        return element;
    }
}
