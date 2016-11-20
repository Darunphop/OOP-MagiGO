package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.cpe.magigo.Sprites.EnemyM;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;
import com.cpe.magigo.Tools.B2WorldCreator;
import com.cpe.magigo.Tools.WorldContactListener;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

/**
 * Created by darunphop on 02-Nov-16.
 */
public class PlayScreen implements Screen {
    //Reference to our game
    private MagiGO game;
    private TextureAtlas atlas;

    //basic screen variable
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Character variable
    private Magician player;
    private EnemyM malee;

    //Tilemap variable
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variable
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;


    public PlayScreen(MagiGO game){
        atlas = new TextureAtlas("character/MagicianFix.pack");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MagiGO.V_WIDTH / MagiGO.PPM, MagiGO.V_HEIGHT / MagiGO.PPM,gamecam);

        //create HUD for score/time/level
        hud = new Hud(game.batch);

        //Import background
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("scene/dayscene.tmx");// <---- locate file name here ******
        renderer = new OrthogonalTiledMapRenderer(map, 1/ MagiGO.PPM);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        //create mario in our game world
        player = new Magician(this);
        malee = new EnemyM(this, 0.32f,0.32f);

        world.setContactListener(new WorldContactListener());

    }
    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    @Override
    public void show() {
    }

    public void handleInput(float dt)
    {
        if(player.currentState != Magician.State.DEAD ) {
            if (player.currentState != Magician.State.CASTING){ //if not casting
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                    player.jump();
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                    player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                    player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

                if (player.getState() == Magician.State.STANDING || player.getState() == Magician.State.RUNNING){
                    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                        player.casting(); //Enter casting state
                        Gdx.app.log("State", player.getState().toString());
                    }
                }
            }else { // is casting
                if (Gdx.input.isKeyJustPressed(Input.Keys.A))
                    player.casting(new Element(ElementType.FIRE));
                if (Gdx.input.isKeyJustPressed(Input.Keys.S))
                    player.casting(new Element(ElementType.WATER));
                if (Gdx.input.isKeyJustPressed(Input.Keys.D))
                    player.casting(new Element(ElementType.WIND));
                if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
                    player.casting(new Element(ElementType.LIGHT));
                if (Gdx.input.isKeyJustPressed(Input.Keys.X))
                    player.casting(new Element(ElementType.DARK));
                if (Gdx.input.isKeyJustPressed(Input.Keys.C))
                    player.casting();
            }

        }
    }

    public void update(float dt)
    {
        handleInput(dt);

        world.step(1/60f ,6,2);

        //player Texture
        player.update(dt);
        malee.update(dt);

        //camera on your character
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear the game screen with Green
        Gdx.gl.glClearColor(0f, 0.7f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        malee.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


        //gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    public TiledMap getMap()
    {
        return map;
    }

    public World getWorld()
    {
        return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public Hud getHud(){ return hud; }
}
