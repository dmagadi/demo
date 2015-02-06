/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

import java.util.ArrayList;
import oop.Bicycle;

/**
 *
 * @author dmagadi
 */
public class DemostrateHeapFail {
    
    public static void main(String[] args) {
        ArrayList<oop.Bicycle> bicycles = new ArrayList<>();
        int index = 1;
        while(true){
            final Bicycle bicycle = new Bicycle(index++);
            
            bicycles.add(bicycle);
            
            System.err.println("Bikes create : " + index);
        }
    }
    
}
