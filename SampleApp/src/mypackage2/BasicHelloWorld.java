package mypackage2;

import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author dmagadi
 */
public class BasicHelloWorld {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name[] = new String[3];
        int age = -1;
        float gpa = 0;

        for (int i = 1; i < 4; i++) {
//            System.out.println(i);

//            if (name[0] == null || name[0].isEmpty()) {
                out.println("Hello There ! What is your name?");
                name[i] = scanner.nextLine();
//            } 
        }
        
        
        
        for(String enteredName : name){
            out.println(enteredName);
        }

//        if (name.equalsIgnoreCase("Copper")) {
//            out.println("Woof");
//            System.exit(0);
//        } else {
//            out.println("Hello " + name + "!! What is your age?");
//
//        }

        age = scanner.nextInt();

        out.println("You are " + age + " years old!!");

        out.println("What month were you born? enter 1 - 12");

        int month = scanner.nextInt();

        String monthString = getStringMonth(month);

        out.println("You were born in " + monthString + ". What is your GPA?");

        gpa = scanner.nextFloat();

        out.println("GPA: " + gpa);
    }

    static String getStringMonth(int month) {

        if (month <= 0 || month > 12) {
            return "unknown";
        }

        String monthstring = "";

        switch (month) {
            case 1:
                monthstring = "Jan";
                break;
            case 2:
                monthstring = "Feb";
                break;
            case 3:
                monthstring = "Mar";
                break;
            case 4:
                monthstring = "Apr";
                break;
            case 5:
                monthstring = "May";
                break;
            case 6:
                monthstring = "Jun";
                break;
            case 7:
                monthstring = "Jul";
                break;
            case 8:
                monthstring = "Aug";
                break;
            case 9:
                monthstring = "Sep";
                break;
            case 10:
                monthstring = "Oct";
                break;
            case 11:
                monthstring = "Nov";
                break;
            case 12:
                monthstring = "Dec";
                break;
            default:
                monthstring = "Unknown";

        }

        return monthstring;
    }
}