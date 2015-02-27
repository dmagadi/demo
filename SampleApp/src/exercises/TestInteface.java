/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

/**
 *
 * @author dmagadi
 */
public class TestInteface {
    
    public static void main(String[] args) {
        new SampleInterfaceMethod().searchContacts(new SampleInterfaceMethod.ICallMe() {

            @Override
            public void foundContact(String contact) {
                System.err.println(contact);
            }
        });
        
        
        
    }
    
}
