/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        // Le um inteiro do arquivo numberOfBooks.txt
        Scanner scanner = null;
        int x = 0;
        try {
            scanner = new Scanner(new FileReader("numberOfBooks.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        }
          String livros = scanner.next();
          x = Integer.parseInt(livros);
           
        // Incrementa e salva no mesmo arquivo
        x++;
        this.bookID = x;
        //System.out.println(x);
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter("numberOfBooks.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        escritor.println(x);
        escritor.close();
        
        try(FileWriter writer = new FileWriter("books.csv", true)) //Append books.csv
        {
           
           writer.write(""+this.bookID);
           writer.write(",");
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
           writer.write("\n");           
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
