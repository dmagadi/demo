/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.godil.aamir1;

/**
 *
 * @author Aamir
 */
public class Test {
    
    public static void main(String[] args)  {
        
        Contact aslam;
        try {
            aslam = new Contact("Aslam", "Godil", "5302632478", "9167835816",
                    "5302733377", 5410, "Via Milano Ct.", "Granite Bay", "CA", "95746");
            
            
            aslam.printContact();
        } catch (ValidationException ex) {
            System.err.println("Error :" + ex.getMessage());
            
        }
        
        
    }
    
}
