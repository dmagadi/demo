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
public class SampleInterfaceMethod {
    
    public void searchContacts(ICallMe callMe){
        
        for (int i = 0; i < 10; i++) {
            if((i % 2) == 0)
            callMe.foundContact("Contact " + i);
        }
    
    
        
    }

    public  interface ICallMe {

        public void foundContact ( String contact) ;
    }
    
}
