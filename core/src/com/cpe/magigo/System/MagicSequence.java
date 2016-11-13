package com.cpe.magigo.System;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicSequence {
    ArrayList<Element> sequence;
    final int CAPACITY = 5;
    int size;

    public MagicSequence(){
        sequence = new ArrayList<Element>(CAPACITY);
        size = 0;
    }

    public void addElement(Element e){
        if (isEmpty()) sequence.set(size++, e);
        else if (!isFull()){
            if (sequence.get(size-1).isWeak(e.getElement())) size--;
            else sequence.set(size++,e);
        }
    }
    public boolean isFull(){
        return size==CAPACITY;
    }

    public boolean isEmpty(){
        return size==0;
    }

}
