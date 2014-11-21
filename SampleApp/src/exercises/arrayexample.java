/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 *
 * @author Aamir
 */
public class arrayexample {

    static String[] names = new String[15];

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);




        String input = "";
        do {

            if (numberOfSlotsTaken() < 15) {
                System.out.println("What is your name? enter print to print all names. \nenter delete to delete a name\n enter quit to exit.");

                input = keyboard.nextLine();
                if (input.equalsIgnoreCase("print")) {
                    printAllNames();
                } else if (input.equalsIgnoreCase("delete")){
                    System.out.println("Which name do you want to delete? Enter 1-15. enter -1 to skip");
                    printAllNames();
                    short index = keyboard.nextShort();
                    if(index == -1){
                        
                    }
                    else if(index < 1 || index > 15){
                        System.out.println("Bad input");
                    }else{
                        names[index-1] = null;
                    }
                    
                    
                }else if (!isPresent(input) && !input.equalsIgnoreCase("quit")) {
                    
                    names[getIndexOfFirstNullSlot()] = input;
                    
                } else if(!input.equalsIgnoreCase("quit")){
                    System.out.println("Name is already entered.");
                }
            } else {
                System.out.println("All Slots taken");

                printAllNames();
                System.out.println("Enter quit to exit");
                input = keyboard.nextLine();

            }


        } while (!input.equalsIgnoreCase("quit"));



    }

    private static boolean isPresent(String input) {
        for (String name : names) {
            if (name != null && name.equalsIgnoreCase(input)) {
                return true;
            }
        }
        
        return false;
    }

    private static void printAllNames() {
        int count = 0;
        for (String name : names) {
            if (name != null && !name.isEmpty()) {
                System.out.println(++count+" - "+name);
            }
        }
    }

    private static int numberOfSlotsTaken() {
        int count = 0;
        for(String name : names){
            if(name != null){
                count++;
            }
        }
     // Loop thorugh names using for (String name : names ). Count the number of slots which are not null. Have a int count and increment it using count ++
        return count;
       // return -1; // remove this
    }

    private static int getIndexOfFirstNullSlot() {
        // Loop thorugh names using for (int i : i < 15 ; i++). When you match null return the index that is i
        int i;
        for(i = 0 ; i < 15; i++){
            if(names[i] == null){
                break;
            }
        }
        return i;
        //return -1; // remove this
    }
}