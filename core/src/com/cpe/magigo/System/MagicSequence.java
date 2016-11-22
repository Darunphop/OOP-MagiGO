package com.cpe.magigo.System;

import com.badlogic.gdx.Gdx;
import com.cpe.magigo.Sprites.Magic.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicSequence {
    Element sequence[];
    HashMap<ElementType,Integer> elementList;
    final int CAPACITY = 5;
    int size;

    public MagicSequence(){
        sequence = new Element[CAPACITY];
        elementList = new HashMap<ElementType, Integer>();
        size = 0;
    }

    public void addElement(Element e){
        if (isEmpty()){
            sequence[0] = e;
            elementList.put(e.getElement(),1);
            size++;
        }
        else if (!isFull()){
            if (sequence[size-1].isWeak(e.getElement())){
                removeElement(sequence[size-1].getElement());
                size--;
            }
            else{
                sequence[size] = e;
                if (elementList.containsKey(e.getElement())){
                    elementList.put(e.getElement(), elementList.get(e.getElement()) + 1);
                }else {
                    elementList.put(e.getElement(), 1);
                }
                size++;
            }
        }
        Gdx.app.log("SEQUENCE",stringSequence() + new String(Integer.toString(size))) ;
    }

    public Magic sequenceAnalyse(){
        if (size >= 3){
            if (elementList.size() == 1 && size==5){
                if (elementList.containsKey(ElementType.FIRE)) return new MagicBolt(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicBolt(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicBolt(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicBolt(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicBolt(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WATER)) &&(elementList.get(ElementType.FIRE).intValue() >= 2 && (elementList.get(ElementType.WATER).intValue() >=2))){
                if (size == 4) return new StreamGayser(ElementType.NEUTRAL);
                removeElement(ElementType.FIRE);removeElement(ElementType.FIRE);
                removeElement(ElementType.WATER);removeElement(ElementType.WATER);
                if (elementList.containsKey(ElementType.FIRE)) return new StreamGayser(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new StreamGayser(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new StreamGayser(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new StreamGayser(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new StreamGayser(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WIND)) && (elementList.get(ElementType.FIRE).intValue() >= 2 && elementList.get(ElementType.WIND).intValue() >=2)){
                if (size == 4) return new MagicMine(ElementType.NEUTRAL);
                removeElement(ElementType.FIRE);removeElement(ElementType.FIRE);
                removeElement(ElementType.WIND);removeElement(ElementType.WIND);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicMine(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicMine(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicMine(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicMine(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicMine(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.LIGHT)) && (elementList.get(ElementType.FIRE).intValue() >= 2 && elementList.get(ElementType.LIGHT).intValue() >=2)){
                if (size == 4) return new MagicArray(ElementType.NEUTRAL);
                removeElement(ElementType.FIRE);removeElement(ElementType.FIRE);
                removeElement(ElementType.LIGHT);removeElement(ElementType.LIGHT);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicArray(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicArray(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicArray(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicArray(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicArray(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.DARK)) && (elementList.get(ElementType.FIRE).intValue() >= 2 && elementList.get(ElementType.DARK).intValue() >=2)){
                if (size == 4) return new MagicBomb(ElementType.NEUTRAL);
                removeElement(ElementType.FIRE);removeElement(ElementType.FIRE);
                removeElement(ElementType.DARK);removeElement(ElementType.DARK);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicBomb(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicBomb(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicBomb(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicBomb(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicBomb(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.WIND)) && (elementList.get(ElementType.WATER).intValue() >= 2 && elementList.get(ElementType.WIND).intValue() >=2)){
                if (size == 4) return new MagicPulse(ElementType.NEUTRAL);
                removeElement(ElementType.WATER);removeElement(ElementType.WATER);
                removeElement(ElementType.WIND);removeElement(ElementType.WIND);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicPulse(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicPulse(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicPulse(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicPulse(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicPulse(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.LIGHT)) && (elementList.get(ElementType.WATER).intValue() >= 2 && elementList.get(ElementType.LIGHT).intValue() >=2)){
                if (size == 4) return new ChainLighting(ElementType.NEUTRAL);
                removeElement(ElementType.WATER);removeElement(ElementType.WATER);
                removeElement(ElementType.LIGHT);removeElement(ElementType.LIGHT);
                if (elementList.containsKey(ElementType.FIRE)) return new ChainLighting(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new ChainLighting(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new ChainLighting(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new ChainLighting(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new ChainLighting(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.DARK)) && (elementList.get(ElementType.WATER).intValue() >= 2 && elementList.get(ElementType.DARK).intValue() >=2)){
                if (size == 4) return new ElementalMagnetic(ElementType.NEUTRAL);
                removeElement(ElementType.WATER);removeElement(ElementType.WATER);
                removeElement(ElementType.DARK);removeElement(ElementType.DARK);
                if (elementList.containsKey(ElementType.FIRE)) return new ElementalMagnetic(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new ElementalMagnetic(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new ElementalMagnetic(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new ElementalMagnetic(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new ElementalMagnetic(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.LIGHT) && elementList.containsKey(ElementType.WIND)) && (elementList.get(ElementType.LIGHT).intValue() >= 2 && elementList.get(ElementType.WIND).intValue() >=2)){
                if (size == 4) return new PiercingArrow(ElementType.NEUTRAL);
                removeElement(ElementType.LIGHT);removeElement(ElementType.LIGHT);
                removeElement(ElementType.WIND);removeElement(ElementType.WIND);
                if (elementList.containsKey(ElementType.FIRE)) return new PiercingArrow(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new PiercingArrow(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new PiercingArrow(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new PiercingArrow(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new PiercingArrow(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.DARK) && elementList.containsKey(ElementType.WIND)) && (elementList.get(ElementType.DARK).intValue() >= 2 && elementList.get(ElementType.WIND).intValue() >=2)){
                if (size == 4) return new MagicBullet(ElementType.NEUTRAL);
                removeElement(ElementType.DARK);removeElement(ElementType.DARK);
                removeElement(ElementType.WIND);removeElement(ElementType.WIND);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicBullet(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicBullet(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicBullet(ElementType.WIND);
                else if (elementList.containsKey(ElementType.LIGHT)) return new MagicBullet(ElementType.LIGHT);
                else if (elementList.containsKey(ElementType.DARK)) return new MagicBullet(ElementType.DARK);
            }else
            if ((elementList.containsKey(ElementType.LIGHT) && elementList.containsKey(ElementType.DARK)) && (elementList.get(ElementType.LIGHT).intValue() == 2 && elementList.get(ElementType.DARK).intValue() == 2)){
                removeElement(ElementType.LIGHT);removeElement(ElementType.LIGHT);
                removeElement(ElementType.DARK);removeElement(ElementType.DARK);
                if (elementList.containsKey(ElementType.FIRE)) return new MagicLaser(ElementType.FIRE);
                else if (elementList.containsKey(ElementType.WATER)) return new MagicLaser(ElementType.WATER);
                else if (elementList.containsKey(ElementType.WIND)) return new MagicLaser(ElementType.WIND);
            }else
            if (elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.WIND) && elementList.containsKey(ElementType.LIGHT) && elementList.containsKey(ElementType.DARK)){
                return new Recharge();
            }else
            if ((elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WATER)) && (elementList.containsKey(ElementType.WIND) && elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.WIND))){
                if (size == 3) return new ElementalShield(ElementType.NEUTRAL);
                removeElement(ElementType.FIRE);removeElement(ElementType.WATER);removeElement(ElementType.WIND);
                if (size == 4) {
                    if (elementList.containsKey(ElementType.FIRE)) return new ElementalShield(ElementType.FIRE);
                    else if (elementList.containsKey(ElementType.WATER)) return new ElementalShield(ElementType.WATER);
                    else if (elementList.containsKey(ElementType.WIND)) return new ElementalShield(ElementType.WIND);
                    else if (elementList.containsKey(ElementType.LIGHT)) return new ElementalShield(ElementType.LIGHT);
                    else if (elementList.containsKey(ElementType.DARK)) return new ElementalShield(ElementType.DARK);
                }
            }else
            if ((elementList.containsKey(ElementType.LIGHT) && elementList.containsKey(ElementType.DARK)) && (elementList.get(ElementType.DARK).intValue() == 1 && elementList.get(ElementType.LIGHT).intValue() ==1)) {
                removeElement(ElementType.DARK);
                removeElement(ElementType.LIGHT);
                if (elementList.size() == 1) {
                    if (elementList.containsKey(ElementType.FIRE)) return new Blink();
                    if (elementList.containsKey(ElementType.WATER)) return new Recall();
                    if (elementList.containsKey(ElementType.WIND)) return new Flying();
                } else if (elementList.size() == 2) {
                    if (elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WIND))
                        return new Recall();
                    if (elementList.containsKey(ElementType.WATER) && elementList.containsKey(ElementType.WIND))
                        return new Blink();
                    if (elementList.containsKey(ElementType.FIRE) && elementList.containsKey(ElementType.WATER))
                        return new Flying();
                }
            }
            return new Magic();
        }else return new Failure();
    }

    private void removeElement(ElementType e){
        if (elementList.get(e).intValue() == 1){
            elementList.remove(e);
        }else {
            elementList.put(e, elementList.get(e) - 1);
        }
    }

    public String stringSequence(){
        String tmp = "{ ";
        for (int i=0;i<size;i++) {
            tmp += sequence[i].getElement().toString();
            tmp += " ";
        }
        tmp += " }";
        return tmp;
    }
    public void clear(){
        this.size = 0;
    }
    public boolean isFull(){
        return size==CAPACITY;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public Element[] getCombine(){
        return this.sequence;
    }

}
