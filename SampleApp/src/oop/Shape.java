/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public abstract class Shape implements IShape{
    
    private int lineThickness;

    public int getLineThickness() {
        return lineThickness;
    }

    public void setLineThickness(int lineThickness) {
        
        if( lineThickness < 1){
            throw new RuntimeException("Line thickenss cannot be less than 1");
        }
        this.lineThickness = lineThickness;
    }
    
    


    public Shape(int lineThickness) {
        this.lineThickness = lineThickness;
    }

    @Override
    public abstract void draw() ;
    
}
