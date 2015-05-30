/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;


/**
 *
 * @author User
 */
public class GeneralBook extends Book {
            

    GeneralBook(){
        super();
        this.type = ("General");
        this.renterID = -1;
        this.rentDate = null;
        this.returnDate = null;
        

        this.writeFile();
        
    }
    
}
