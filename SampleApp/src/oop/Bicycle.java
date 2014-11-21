/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
class Bicycle {

    private int cadence = 0;
    private int speed = 0;

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

    void applyBrakes(int decrement) {
         speed = speed - decrement;
    }

    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
