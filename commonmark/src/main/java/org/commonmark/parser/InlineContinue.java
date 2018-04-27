package org.commonmark.parser;

public class InlineContinue {
    
    //Current position 
    private final int index;
    
    public static InlineContinue finished = new InlineContinue(-1);
    
    public InlineContinue(int index) {
        super();
        this.index = index;
    }
    
    public boolean isFinished () {
        return this.index == -1;
    }

    public int getIndex() {
        return index;
    }
    
    
    
    
    
    
    
}
