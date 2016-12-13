package com.cpe.magigo.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;

public class HPGauge extends Sprite {
    private Sprite HP_FG ;
    private Texture FG = new Texture("HealtBar/source/fullhealth_bar.png");
    private TextureRegion FG_R;
    private Sprite HP_BG;
    private Texture BG = new Texture("HealtBar/source/health_bar.png");
    private TextureRegion BG_R;
    private int Hp;
    public HPGauge(int hp)
    {
        this.Hp = hp;
        BG_R = new TextureRegion(BG);
        FG_R = new TextureRegion(FG);
        HP_FG = new Sprite(FG_R,0,0,500,83);
        HP_BG = new Sprite(BG_R);
        HP_BG.setOrigin(0,0);
        HP_FG.setOrigin(0,0);
    }

    public void update()
    {
        HP_BG.setPosition((MagiGO.V_WIDTH/2-270)/MagiGO.PPM, 100 / MagiGO.PPM);
        HP_FG.setPosition((MagiGO.V_WIDTH/2-270)/MagiGO.PPM, 100 / MagiGO.PPM);
        HP_BG.setScale(0.65f/MagiGO.PPM,0.5f/MagiGO.PPM);
        HP_FG.setScale(0.65f/MagiGO.PPM,0.5f/MagiGO.PPM);
    }

    public void draw(Batch batch)
    {
        HP_BG.draw(batch);
        HP_FG.draw(batch);
    }

    public void render(Batch batch)
    {
        HP_BG.draw(batch);
        //HP_FG.draw(batch);
    }
}
