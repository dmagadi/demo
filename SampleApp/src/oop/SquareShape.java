/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public class SquareShape extends Shape implements IShape{
    
    private int width;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    private int height;

    @Override
    public void draw() {
        System.out.println(String.format("I am a Square with width %d height %d and linr thickness of %d",width,height,this.getLineThickness() ));
    }

    public SquareShape(int width, int height, int lineThickness) {
        super(lineThickness);
        this.width = width;
        this.height = height;
    }
    
    
    
}
