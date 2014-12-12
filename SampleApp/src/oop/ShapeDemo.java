/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author dmagadi
 */
public class ShapeDemo {
    
    public static void main(String args[]){
        Shape shape1 = null;
        
        System.out.println("What shape you want to draw? c - circle s - square");
        
        
        String input = new Scanner(System.in).nextLine();
        
        if(input.equals("s")){
            shape1 = new SquareShape(10, 10, 2);
        }if(input.equals("c")){
            shape1 = new RoundShape(10,2);
        }
        
        System.out.println("What line thickness you want?");
        
        Integer lineThickness = new Scanner(System.in).nextInt();
        
        
        shape1.setLineThickness(lineThickness);
        
        
        shape1.draw();
    }
    
}
