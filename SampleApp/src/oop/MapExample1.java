/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author dmagadi
 */
public class MapExample1 {
    
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        
        HashMap<String,String> contacts = new HashMap<>();
        
        String input ="";
        
        do {
            System.out.println("Press a to Add a name and phone number or s to search ");
            input = scanner.nextLine();
            
            if(input.equalsIgnoreCase("a")){
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Phone:");
                String ph = scanner.nextLine();
                
                contacts.put(name, ph);
            }
            
        } while (input.equalsIgnoreCase("q")) ;
        
            
        
    }
    
    
}
