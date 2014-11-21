/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;
import java.util.Scanner;
/**
 *
 * @author dmagadi
 */
class BicycleDemo {
    public static void main(String[] args) {

        // Create two different 
        // Bicycle objects
        Bicycle bike1 = new Bicycle();
        Bicycle bike2 = new Bicycle();

        
        
        
        for(int lap = 1 ; lap <= 100; lap++ ){
            
            
            bike1.speedUp(getRandomSpeed());
            bike2.speedUp(getRandomSpeed());
            
            bike1.printStates();
            bike2.printStates();
            
            if(bike1.getSpeed() > bike2.getSpeed()){
                System.out.println("Bike 1 is winning in lap " + lap );
            }else{
                System.out.println("Bike 2 is winning in lap " + lap );
            }
            //String input = new Scanner(System.in).nextLine();
        }

    }
    
    private static int getRandomSpeed(){
        return (int)(Math.random() * 10);
    }
}
