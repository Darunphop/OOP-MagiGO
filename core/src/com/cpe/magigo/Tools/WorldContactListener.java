package com.cpe.magigo.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.InteractiveTileObject;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.Sprites.Platform;

/**
 * Created by MSI GP72 on 14/11/2559.
 */
public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
       /* Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "head" || fixB.getUserData() == "head" ){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
        if(fixA.getUserData() == "leg" || fixB.getUserData() == "leg"){
            Fixture head = fixA.getUserData() == "leg" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onLegHit();
            }
        }*/

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
            } else if(fixtureA.getBody().getUserData() == "player") {
                player_y = fixtureA.getBody().getPosition().y;
                platform_y = fixtureB.getBody().getPosition().y;
            }

            if(player_y < platform_y + 0.22f) {  //the player is below platform
                contact.setEnabled(false);
            } else {
                contact.setEnabled(true);
            }
            if(player_y > platform_y + 0.22f && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {  //the player is above platform
                contact.setEnabled(false);
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
