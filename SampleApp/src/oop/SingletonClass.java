/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author dmagadi
 */
public class SingletonClass {
    
    private static SingletonClass singleTon  = new SingletonClass();
    
    private String instance = "instance1";
    
    private int count = 0;
            
            
    private SingletonClass(){
        
    }
    
    public static SingletonClass getInstance(){
        return singleTon;
    }
    
    public void someApi(String someData){
        System.out.println(instance + " " + someData + " " + ++count);
        
    }
}
