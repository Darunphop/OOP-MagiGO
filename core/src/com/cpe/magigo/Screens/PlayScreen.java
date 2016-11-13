package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Scenes.Hud;
import com.cpe.magigo.Sprites.Magician;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

/**
 * Created by darunphop on 02-Nov-16.
 */
public class PlayScreen implements Screen {
    private MagiGO game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Magician player;

    //Tilemap variable
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variable
    private World world;
    private Box2DDebugRenderer b2dr;


    public PlayScreen(MagiGO game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MagiGO.V_WIDTH / MagiGO.PPM, MagiGO.V_HEIGHT/ MagiGO.PPM,gamecam);

        //create HUD for score/time/level
        hud = new Hud(game.batch);

        //Import background
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("scene/test.tmx");// <---- locate file name here ******
        renderer = new OrthogonalTiledMapRenderer(map, 1/ MagiGO.PPM);

        //
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();
        player = new Magician(world);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create Ground object
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getY()+rect.getHeight() / 2) / MagiGO.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2)/ MagiGO.PPM  , (rect.getHeight() /2)/ MagiGO.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);
        }

    }

    @Override
    public void show() {
    }

    public void handleInput(float dt)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            player.b2body.applyLinearImpulse(new Vector2(0,1f), player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=2 )
        {
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0) , player.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >=-2)
        {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(),true);
        }
    }

    public void update(float dt)
    {
        handleInput(dt);

        world.step(1/60f ,6,2);

        //camera on your character
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0f, 0.7f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        renderer.render();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
