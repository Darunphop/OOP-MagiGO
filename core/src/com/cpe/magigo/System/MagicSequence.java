package com.cpe.magigo.System;

import java.util.Stack;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicSequence {
    Stack<Element> sequence;

    public MagicSequence(){
        sequence = new Stack<Element>();
    }

    public void addElement(Element e){
        if (isEmpty()) sequence.push(e);
        else if (!isFull()){
            if (sequence.peek().isWeak(e.getElement())) sequence.pop();
            else sequence.push(e);
        }
    }
    public boolean isFull(){
        return sequence.size()==5;
    }

    public boolean isEmpty(){
        return sequence.size()==0;
    }

}
