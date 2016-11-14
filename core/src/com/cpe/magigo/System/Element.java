package com.cpe.magigo.System;

/**
 * Created by darunphop on 13-Nov-16.
 */
public  class Element {
    ElementType e;
    ElementType weak;

     public Element(ElementType e){
         this.e = e;
         switch (e){
             case FIRE:
                 weak = ElementType.WATER;
                 break;
             case WATER:
                 weak = ElementType.WIND;
                 break;
             case WIND:
                 weak = ElementType.FIRE;
                 break;
             case LIGHT:
                 weak = ElementType.DARK;
                 break;
             case DARK:
                 weak = ElementType.LIGHT;
                 break;
             default:
                 weak = ElementType.NULL;
                 break;
         }
     }

     public boolean isWeak(ElementType e){
         return e == weak;
     }

    public ElementType getWeak(){
        return this.weak;
    }

    public ElementType getElement(){
        return this.e;
    }
}
