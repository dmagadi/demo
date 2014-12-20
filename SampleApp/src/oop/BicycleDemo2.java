/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dmagadi
 */
class BicycleDemo2 {
    public static void main(String[] args) {

        // Create two different 
        // Bicycle objects
        ArrayList<Bicycle> bicycles = new ArrayList<>();
        
        //Home work 
        // Add id to Bike
        // Add constructor with id
        // In the print state print the id of the bike
        
        // Optional home work 
        
        // identify which bike is winning programatically
        // Hint add a equals method in bike class which take another bike and compares the speed and return difference
        
        
        System.out.println("Enter the number of bikes you want in the race? ");
        
        Integer num = new Scanner(System.in).nextInt();
        
        for(int i = 0 ; i < num ; i ++){
            Bicycle  bike = new Bicycle(i+1);
            bicycles.add(bike);
        }
        
        System.out.println("Enter the number of laps you want in the race? ");
        
        Integer laps = new Scanner(System.in).nextInt();
        
        
        for(int lap = 1 ; lap <= laps; lap++ ){
            // winnining 
            
            Bicycle  winningBike = null;
            
            for(Bicycle bike : bicycles){
                bike.speedUp(getRandomSpeed());
                bike.printStates();
                if(winningBike == null){
                    winningBike = bike;
                }else{
                    // compare winningBike and the current bike if current bike is winning set the current bike to winningBike
                    if(bike.compare(winningBike) > 0){
                        winningBike = bike;
                    }
                }
             
                
            }
            System.out.print("Winning bike for the lap is "  );
                winningBike.printId();
            // get the winning bikes id and print that bike with that id won the race
            
                
            String input = new Scanner(System.in).nextLine();
        }

    }
    
    private static int getRandomSpeed(){
        return (int)(Math.random() * 100);
    }
}
