/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public class RoundShape extends Shape implements IShape{
    private int radius;
    
    @Override
    public void draw() {
        System.out.println(String.format("I am a circle with radius %d lineThickness %d",radius,this.getLineThickness()));
        
    }

    public RoundShape(int radius, int lineThickness) {
        super(lineThickness);
        this.radius = radius;
    }
    
}
