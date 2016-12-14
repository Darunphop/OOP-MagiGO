package com.cpe.magigo.System;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

/**
 * Created by darunphop on 13-Nov-16.
 */
public  class Element {
    ElementType e;
    ElementType weak;
    Color color;

     public Element(ElementType e){
         this.e = e;
         switch (e){
             case FIRE:
                 weak = ElementType.WATER;
                 color = Color.RED;
                 break;
             case WATER:
                 weak = ElementType.WIND;
                 color = Color.BLUE;
                 break;
             case WIND:
                 weak = ElementType.FIRE;
                 color = Color.GREEN;
                 break;
             case LIGHT:
                 weak = ElementType.DARK;
                 color = Color.GOLD;
                 break;
             case DARK:
                 weak = ElementType.LIGHT;
                 color = Color.PURPLE;
                 break;
             default:
                 weak = ElementType.NULL;
                 break;
         }
     }

     public boolean isWeak(ElementType e){
         return e == weak;
     }

     public static Color getColor(ElementType e){
         Color color;
         switch (e){
             case FIRE:
                 color = Color.SALMON;
                 break;
             case WATER:
                 color = new Color(0.06f,0.13f,42f,1f);
                 break;
             case WIND:
                 color = Color.GREEN;
                 break;
             case LIGHT:
                 color = Color.GOLD;
                 break;
             case DARK:
                 color = Color.PURPLE;
                 break;
             default:
                 color = Color.CHARTREUSE;
                 break;
         }
         return color;
     }
    public ElementType getWeak(){
        return this.weak;
    }

    public ElementType getElement(){
        return this.e;
    }
}
