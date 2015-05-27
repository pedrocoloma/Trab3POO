/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Book {
        
    protected  String title;
    protected  String author;
    protected  int year;
    protected  int bookID;
    
    
    protected GregorianCalendar rentDate;
    protected GregorianCalendar returnDate;
    

    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }    
    public int getYear(){
        return this.year;
    }
    public int getBookID(){
        return this.bookID;
    }

    public GregorianCalendar getRentDate(){
        return this.rentDate;
    }
    
    public GregorianCalendar getReturnDate(){
        return this.returnDate;
    }

    void writeFile(){

        try( FileWriter writer = new FileWriter("books.csv", true)) //Append books.csv
        {
           writer.write(""+this.title);
           writer.write(",");
           writer.write(""+this.author);
           writer.write(",");           
           writer.write(""+this.year);
           writer.write(",");           
           writer.write(""+this.rentDate);           
           writer.write(",");           
           writer.write(""+this.returnDate); 
           /*writer.write(",");           
           if(this.renter != null){
                writer.write(""+this.renter.getName()); 
           }
           else{
                writer.write("null"); 
           }*/
           writer.write("\t");           
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /*
    Book(){
        Scanner s = new Scanner (System.in);

        System.out.println("Enter the Title: ");
        this.title = s.nextLine();
            
        System.out.println("Enter the Author: ");
        this.author = s.nextLine();
        
        System.out.println("Enter the Year: ");
        this.year = s.nextInt();
        
        //this.renter = null;
        this.rentDate = null;
        this.returnDate = null;
        

        this.writeFile();
        /*String fileContent;
        SimpleDateFormat s1 = new SimpleDateFormat("dd/MM/yyyy");  
        String renDate = s1.format(this.rentDate.getTime());
        
        SimpleDateFormat s2 = new SimpleDateFormat("dd/MM/yyyy");  
        String retDate = s2.format(this.returnDate.getTime());
        */
        
        //fileContent = ("" + String.valueOf(this.bookID) + this.title + this.author + this.year + this.renter.getName() /*+ renDate + retDate*/);
        //System.out.println(fileContent);
        
        
    //}*/
    
}
