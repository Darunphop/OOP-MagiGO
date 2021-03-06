package com.cpe.magigo.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Magic.Magic;

public class HP_Char extends Sprite {
    private Sprite HP_FG ;
    private Texture FG = new Texture("HealtBar/source/fullhealth_bar.png");
    private TextureRegion FG_R;
    private Sprite HP_BG;
    private Texture BG = new Texture("HealtBar/source/health_bar.png");
    private TextureRegion BG_R;
    public float Hp;
    public HP_Char(float hp)
    {
        this.Hp = hp;
        BG_R = new TextureRegion(BG);
        FG_R = new TextureRegion(FG);
        HP_FG = new Sprite(FG_R,0,0,FG_R.getRegionWidth(),FG_R.getRegionHeight());
        HP_BG = new Sprite(BG_R);
        HP_BG.setOrigin(0,0);
        HP_FG.setOrigin(0,0);
    }

    public void update(float dt)
    {
        this.Hp = dt;
        HP_BG.setPosition((MagiGO.V_WIDTH/2-270)/MagiGO.PPM, 30 / MagiGO.PPM);
        HP_BG.setScale(0.65f/MagiGO.PPM,0.5f/MagiGO.PPM);
        HP_FG.setSize((int)this.Hp*(FG_R.getRegionWidth()/100),FG_R.getRegionHeight());
        HP_FG.setPosition((MagiGO.V_WIDTH/2-210)/MagiGO.PPM, 30 / MagiGO.PPM);
        HP_FG.setRegion(90,0,(int)(this.Hp*(FG_R.getRegionWidth()/100)),FG_R.getRegionHeight());
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

    public void hit()
    {
        Hp-=10;
    }
}
