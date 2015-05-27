/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class GeneralBook extends Book {
    
        private CommonUser renter;
        
        @Override
    void writeFile(){

        try(FileWriter writer = new FileWriter("books.csv", true)) //Append books.csv
        {
           writer.write("General,");
           writer.write(""+this.title);
           writer.write(",");
           writer.write(""+this.author);
           writer.write(",");           
           writer.write(""+this.year);
           writer.write(",");           
           writer.write(""+this.rentDate);           
           writer.write(",");           
           writer.write(""+this.returnDate); 
           writer.write(",");           
           if(this.renter != null){
                writer.write(""+this.renter.getName()); 
           }
           else{
                writer.write("null"); 
           }
           writer.write("\t");           
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    GeneralBook(){
        Scanner s = new Scanner (System.in);

        System.out.println("Enter the Title: ");
        this.title = s.nextLine();
            
        System.out.println("Enter the Author: ");
        this.author = s.nextLine();
        
        System.out.println("Enter the Year: ");
        this.year = s.nextInt();
        
        this.renter = null;
        this.rentDate = null;
        this.returnDate = null;
        

        this.writeFile();
        
    }
    
}
