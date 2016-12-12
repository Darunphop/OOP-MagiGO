package com.cpe.magigo.System;

/**
 * Created by darunphop on 12-Dec-16.
 */
public class Status {
    private double maxHP;
    private double currentHP;
    private double atk;
    private double def;// 1 is default
    private Element element;

    public Status(){
        this(10,10,1,1,new Element(ElementType.NEUTRAL));
    }
    public Status(float maxHP, float currentHP, float atk, float def, Element element) {
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.atk = atk;
        this.def = def;
        this.element = element;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(double currentHP) {
        if (currentHP > maxHP)
            this.currentHP = maxHP;
        else if (currentHP < 0)
            this.currentHP = 0;
        else
            this.currentHP = currentHP;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
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
