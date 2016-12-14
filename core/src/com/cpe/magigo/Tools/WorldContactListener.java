package com.cpe.magigo.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.*;
import com.cpe.magigo.Sprites.Magic.Magic;

/**
 * Created by MSI GP72 on 14/11/2559.
 */
public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Crystal fixC;
        Enemy fixE;
        Magic fixM;
        Magician fixMC;

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef){
            case MagiGO.CRYSTAL_BIT | MagiGO.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == MagiGO.CRYSTAL_BIT)
                {
                    fixC = (Crystal)fixA.getUserData();
                    fixE = (Enemy)fixB.getUserData();
                }
                else
                {
                    fixC = (Crystal)fixB.getUserData();
                    fixE = (Enemy)fixA.getUserData();
                }
                fixC.hit();
                fixE.hit(fixC.getDmg(), fixC.getElement());
                if (fixE.isDead())
                    fixE.deconstruct();

                break;
            case MagiGO.MAGIC_BIT | MagiGO.ENEMY_BIT:

                if(fixA.getFilterData().categoryBits == MagiGO.MAGIC_BIT)
                {
                    fixM = (Magic)fixA.getUserData();
                    fixE = (Enemy)fixB.getUserData();
                }
                else
                {
                    fixM = (Magic)fixB.getUserData();
                    fixE = (Enemy)fixA.getUserData();
                }
                fixM.hit();
                fixE.hit(fixM.getDmg(), fixM.getElement());
                if (fixE.isDead())
                    fixE.deconstruct2();
                break;
            case MagiGO.MAGIGO_BIT | MagiGO.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == MagiGO.MAGIGO_BIT)
                {
                    fixMC = (Magician) fixA.getUserData();
                    fixE = (Enemy)fixB.getUserData();
                }
                else
                {
                    fixMC = (Magician) fixB.getUserData();
                    fixE = (Enemy)fixA.getUserData();
                }
                fixMC.hit(fixE.getStatus().getAtk(), fixE.getStatus().getElement().getElement());
                break;
            case MagiGO.MAGIC_BIT | MagiGO.PLATFORM_BIT:

                if(fixA.getFilterData().categoryBits == MagiGO.MAGIC_BIT)
                {
                    fixM = (Magic)fixA.getUserData();

                }
                else
                {
                    fixM = (Magic)fixB.getUserData();

                }
                fixM.hitFloor();
                break;
            case MagiGO.MAGIC_OB_BIT | MagiGO.ENEMY_BIT:

                if(fixA.getFilterData().categoryBits == MagiGO.MAGIC_OB_BIT)
                {
                    fixM = (Magic) fixA.getUserData();
                    fixE = (Enemy)fixB.getUserData();
                }
                else
                {
                    fixM = (Magic) fixB.getUserData();
                    fixE = (Enemy)fixA.getUserData();
                }
                fixM.hit(fixE);
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //THIS SHOULD ALLOW PLAYER TO PASS THROUGH PLATFORMS AND COLLIDE ON THE WAY DOWN
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        float platform_y;
        float player_y;


        platform_y = fixtureA.getBody().getPosition().y;
        player_y = fixtureB.getBody().getPosition().y;

        if(fixtureA.getFilterData().categoryBits == MagiGO.MAGIGO_BIT
                && fixtureB.getFilterData().categoryBits == MagiGO.PLATFORM_BIT ||
                fixtureA.getFilterData().categoryBits == MagiGO.PLATFORM_BIT
                        && fixtureB.getFilterData().categoryBits == MagiGO.MAGIGO_BIT) {

            //Gdx.app.log("Player Y ", "" + player_y);

            if (fixtureA.getBody().getUserData() == "platform") {
                platform_y = fixtureA.getBody().getPosition().y;
                player_y = fixtureB.getBody().getPosition().y;
            } else if (fixtureA.getBody().getUserData() == "player") {
                player_y = fixtureA.getBody().getPosition().y;
                platform_y = fixtureB.getBody().getPosition().y;
            }

            if (player_y < platform_y + 0.22f) {  //the player is below platform
                contact.setEnabled(false);
            } else {
                contact.setEnabled(true);
            }
            if (player_y > platform_y + 0.22f && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {  //the player is above platform
                contact.setEnabled(false);
            }
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
