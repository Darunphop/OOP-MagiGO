package com.cpe.magigo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.ReachOrientation;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Scenes.Hud;
import com.cpe.magigo.Scenes.MagicCombineInterface;
import com.cpe.magigo.Sprites.*;
import com.cpe.magigo.Sprites.Magic.Magic;
import com.cpe.magigo.System.Element;
import com.cpe.magigo.System.ElementType;
import com.cpe.magigo.System.Status;
import com.cpe.magigo.Tools.B2WorldCreator;
import com.cpe.magigo.Tools.BossControl;
import com.cpe.magigo.Tools.WorldContactListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by darunphop on 02-Nov-16.
 */
public class PlayScreen implements Screen {
    public static final float GRAVITY = -10f;

    //Reference to our game
    private MagiGO game;
    private TextureAtlas atlas;
    private TextureAtlas atlastMon;

    //basic screen variable
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private MagicCombineInterface MCI;

    //HP variable
    private HPGauge hp;
    private HP_Char hp_char;
    private float health;

    //Character variable
    private Magician player;
    private EnemyM malee;
    private EnemyR range;
    private Crystal crystal;


    //Tilemap variable
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variable
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
   // private BossControl boss;
    public float Timecount;
    public float Timer;
    public float Timer2;
    public Status status_Crystal;

    //Magic Objects
    public ArrayList<Magic> magics;

    public ArrayList<Body> deleteList;
    public ArrayList<Magic> deleteMagicList;
    private Music music;

    public int magicCharge;

    public boolean signalToClear;



    public PlayScreen(MagiGO game){
        atlas = new TextureAtlas("character/character.pack");
        atlastMon = new TextureAtlas("enemy/Enemy/Monster.pack");
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
        world = new World(new Vector2(0,GRAVITY),true);
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);
       // boss = new BossControl(this);

        //create mario in our game world
        status_Crystal = new Status(100f,1,1,ElementType.NEUTRAL);
        player = new Magician(this);

        /* malee = new EnemyM(this , 0.32f , 0.32f);
        range = new EnemyR(this , 0.32f , 0.32f);*/
        hp = new HPGauge(status_Crystal.getCurrentHP());
        hp_char = new HP_Char(player.getStatus().getCurrentHP());
        music = MagiGO.manager.get("soundtrack/Main.ogg",Music.class);
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        //create MCI
        MCI = new MagicCombineInterface(game.batch, player);

        world.setContactListener(new WorldContactListener());

        magics = new ArrayList<Magic>();
        deleteList = new ArrayList<Body>();
        deleteMagicList = new ArrayList<Magic>();
        magicCharge = 0;

        signalToClear = false;
    }
    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    public TextureAtlas getAtlastMon()
    {
        return atlastMon;
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
                        MCI.standby();
                        Gdx.app.log("State", player.getState().toString());
                    }
                }
            }else { // is casting
                if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
                    player.casting(new Element(ElementType.FIRE));
                    MCI.update();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                    player.casting(new Element(ElementType.WATER));
                    MCI.update();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                    player.casting(new Element(ElementType.WIND));
                    MCI.update();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.Z)){
                    player.casting(new Element(ElementType.LIGHT));
                    MCI.update();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
                    player.casting(new Element(ElementType.DARK));
                    MCI.update();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                    MCI.sleep();
                    player.cancelCasting();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                    MCI.sleep();
                    player.casting();
                }
            }

        }
    }

    public void update(float dt)
    {
            handleInput(dt);
            health = player.getStatus().getCurrentHP();
            world.step(1 / 60f, 6, 2);
            Timer += 1 * dt;
            Timer2 += 1 * dt;
            Timecount += 1 * dt;
            //player Texture
            player.update(dt);
        /*malee.update(dt);
        range.update(dt);*/
            hp.update(status_Crystal.getCurrentHP());
            hp_char.update(player.getStatus().getCurrentHP());
            creator.update(dt);
            //boss.update(dt);
            for (Enemy enemy : creator.getEnemyMs()) {
                enemy.update(dt);
            }
           /* for (Enemy enemy : boss.getEnemyRs()) {
                 enemy.update(dt);
             }*/

        for (Magic magic:magics){
            magic.update(dt);
        }

            //camera on your character
            gamecam.position.x = 640 / MagiGO.PPM;
            gamecam.position.y = 384 / MagiGO.PPM;
            hud.update(dt);
            //MCI.update(dt);

            gamecam.update();
            renderer.setView(gamecam);

//        sweepDeadBodies();
            for (Body body : deleteList) {
                world.destroyBody(body);
            }
            deleteList.clear();
        if (signalToClear){
            for (Magic magic: deleteMagicList){
                magics.remove(magic);
            }
            deleteMagicList.clear();
            signalToClear = false;
        }




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
        //b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        //game.batch.draw(Hp,0,0,10,5);
        game.batch.begin();
        player.draw(game.batch);
       // malee.draw(game.batch);
        for (Enemy enemy:creator.getEnemyMs() )
        {
            enemy.draw(game.batch);
        }
       /* for (Enemy enemy:boss.getEnemyRs() )
        {
            enemy.draw(game.batch);
        }*/
        //range.draw(game.batch);
        hp.draw(game.batch);
        hp_char.draw(game.batch);
        //magic objects render
//        Array<Body> tmpBody = new Array<Body>();
//        world.getBodies(tmpBody);
//        for (Body body:tmpBody){
//            if (body.getUserData() instanceof Sprite){
//                Sprite sprite = (Sprite) body.getUserData();
//                sprite.setPosition(body.getPosition().x-6f/MagiGO.PPM,body.getPosition().y-6f/MagiGO.PPM);
//                sprite.draw(game.batch);
//            }
//        }

        for (Magic magic:magics){
            for (Body body:magic.getBullets()){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x-(sprite.getWidth()/2),body.getPosition().y-(sprite.getHeight()/2));
                sprite.draw(game.batch);
            }
        }

        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if (MCI.isReady())
            MCI.stage.draw();

        if(status_Crystal.getCurrentHP()<=0 || health <= 0){
            music.stop();
            game.setScreen(new GameoverScreen(game,getHud().getScore(),getHud().getWorldTimer()));
            dispose();
        }

        //gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);



    }

//    public void sweepDeadBodies() {
//        Array<Body> tmpBody = new Array<Body>();
//        world.getBodies(tmpBody);
//        for (Body body:tmpBody){
//            if (body != null) {
//                 EnemyM data = (EnemyM) body.getUserData();
//                if (data.isDead()) {
//                    world.destroyBody(body);
//                    body.setUserData(null);
//                    body = null;
//                }
//            }
//        }
//    }

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

    public int getMagicCharge() {
        return magicCharge;
    }

    public void addMagicCharge() {
        if (magicCharge < 3){
            magicCharge++;
        }
    }

    public void useMagicCharge() {
        magicCharge = 0;
    }

    public void clearMagic(Magic magic){
        deleteMagicList.add(magic);
    }
    public Magician getPlayer() {
        return player;
    }

    public Hud getHud(){ return hud; }
}
