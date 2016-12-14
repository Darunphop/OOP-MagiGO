package com.cpe.magigo.System;

/**
 * Created by darunphop on 12-Dec-16.
 */
public class Status {
    private float maxHP;
    private float currentHP;
    private float atk;
    private float def;// 1 is default
    private Element element;

    public Status(){
        this(100,10,1,1,new Element(ElementType.NEUTRAL));
    }
    public Status(float maxHP, float currentHP, float atk, float def, Element element) {
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.atk = atk;
        this.def = def;
        this.element = element;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(float maxHP) {
        this.maxHP = maxHP;
    }

    public float getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(float currentHP) {
        if (currentHP > maxHP)
            this.currentHP = maxHP;
        else if (currentHP < 0)
            this.currentHP = 0;
        else
            this.currentHP = currentHP;
    }

    public float getAtk() {
        return atk;
    }

    public void setAtk(float atk) {
        this.atk = atk;
    }

    public float getDef() {
        return def;
    }

    public void setDef(float def) {
        if (def != 0)
            this.def = def;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
