/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public class Bicycle {
    
    public  static int NUMBER_OF_INSTANCES = 0;
    
    
    
    private int bikeId = -1 ;
    
    private int cadence = 0;
    private int speed = 0;
    
    private Bicycle(){
        
    }

    public Bicycle(int bikeId) {
        System.out.println("Number of instances " + ++NUMBER_OF_INSTANCES);
        if(bikeId <= 0 ){
            throw new RuntimeException("Need valid bike id");
        }
        this.bikeId = bikeId;
    }
    
    
    

    public int getSpeed() {
        return speed;
    }
    private int gear = 1;

    void changeCadence(int newValue) {
         cadence = newValue;
         Math.random();
    }

    void changeGear(int newValue) {
         gear = newValue;
    }

    void speedUp(int increment) {
         speed = speed + increment;   
    }

    void applyBrakes(final int decrement) {
         speed = speed - decrement;
         
         
         
    }
    
    public int compare( Bicycle anotherBike){
        return this.getSpeed() - anotherBike.getSpeed();
    }

    public void printId(){
        System.out.println("bike id " + bikeId );
    } 
    
    void printStates() {
         System.out.println("bike id " + bikeId + "cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
