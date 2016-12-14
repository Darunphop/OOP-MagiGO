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
        this(100,1,1,ElementType.NEUTRAL);
    }
    public Status(float maxHP, float atk, float def, ElementType element) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.element = new Element(element);
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

    public void damageCal(float dmg,ElementType receiver,ElementType attacker){
        float factor = 1f;
        if (new Element(receiver).isWeak(attacker))
            factor = 2f;
        else if (new Element(attacker).isWeak(receiver))
            factor = 0.5f;
        float fdamage = dmg / def * factor;
        this.setCurrentHP(getCurrentHP()-(fdamage));
    }
    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
