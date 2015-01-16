/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public class SingletonTest {
    
    public static void main(String[] args){
        SingletonClass x = SingletonClass.getInstance();
        
        x.someApi("hello");
        
        x = SingletonClass.getInstance();
        
        x.someApi("hello");

        x = SingletonClass.getInstance();
        
        x.someApi("hello");
        
        
    }
    
}
