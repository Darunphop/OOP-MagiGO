package com.cpe.magigo.Sprites.Magic;

import com.cpe.magigo.System.ElementType;

/**
 * Created by ICQCQ on 15-Nov-16.
 */
public abstract class Magic {
    private ElementType element;
    public Magic(ElementType e) {
        this.element = e;
    }

    public Magic() {
    }

}
