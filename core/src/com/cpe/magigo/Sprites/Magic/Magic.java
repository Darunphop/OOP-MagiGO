package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Enemy;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.System.ElementType;

import java.util.ArrayList;

/**
 * Created by ICQCQ on 15-Nov-16.
 */
public abstract class Magic  {
    protected ElementType element;
    protected PlayScreen screen;
    private float dmg;
    protected ArrayList<Body> bullets;
    public Magic(ElementType e) {
        this.element = e;
    }
    public Magic(ElementType e,PlayScreen screen) {
        this.element = e;
        this.screen = screen;
        bullets = new ArrayList<Body>();
        screen.magics.add(this);
    }

    public Magic() {
    }

    protected Body createMagic(float size,float xpad,float ypad){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(screen.getPlayer().b2body.getPosition().x+xpad,screen.getPlayer().b2body.getPosition().y+ypad);

        Body body = screen.getWorld().createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(size/ MagiGO.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.filter.categoryBits = MagiGO.MAGIC_BIT;
        fixtureDef.filter.maskBits = MagiGO.PLATFORM_BIT | MagiGO.ENEMY_BIT | MagiGO.OBJECT_BIT | MagiGO.DEFAULT_BIT;
        fixtureDef.isSensor = true;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
       // circle.dispose();

        return body;
    }

    public ArrayList<Body> getBullets(){
        return this.bullets;
    }
    public void hit(){
        Gdx.app.log("MAGIC", "ENEMY CONTACT");
    }
    public void update(float dt){

    }

    public float getDmg() {
        return dmg;
    }

    public void setDmg(float dmg) {
        this.dmg = (dmg<0)?0:dmg;
    }

    public ElementType getElement() {
        return element;
    }


    public void deconstruct(){
        for (Body body:bullets){
            screen.deleteList.add(body);
        }
        bullets.clear();
        screen.clearMagic(this);
        screen.signalToClear=true;
    }
    public void hit(Enemy e){}
    public void hitFloor(){}
    public abstract void excecute(PlayScreen screen);

}
