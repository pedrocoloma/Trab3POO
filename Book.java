
package trabalho3;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Book {
        
    protected  String title;
    protected  String author;
    protected  String type;
    protected  int renterID;//MANTEM COMO USER? USER ID?
    protected  int year;
    protected  int bookID;
    
    protected String rentDate;
    protected String returnDate;
    
    // GETTERS
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
    public int getRenterID(){
        return this.renterID;
    }
    public String getType(){
        return this.type;
    }    
    public String getRentDate(){
        return this.rentDate;
    }
    public String getReturnDate(){
        return this.returnDate;
    }
    
    //SETTERS
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setBookID(int id){
        this.bookID = id;
    }
    public void setRenterID(int id){
        this.renterID = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setRentDate(String date){
        this.rentDate = date;
    }
    public void setReturnDate(String date){
        this.returnDate = date;
    }
    
    void writeFile(){
        
        try(FileWriter writer = new FileWriter("books.csv", true)) //Append books.csv
        {
           
           writer.write(""+this.bookID+",");
           writer.write(""+this.type+",");
           writer.write(""+this.title+",");
           writer.write(""+this.author+",");
           writer.write(""+this.year+",");
           writer.write(""+this.rentDate+",");           
           writer.write(""+this.returnDate+","); 
           writer.write(""+this.renterID); 
           writer.write("\n");  
           writer.close();                      
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    void printBook(){
        System.out.println("BOOK ID: " + this.bookID);    
        System.out.println("TYPE: " + this.type);
        System.out.println("NAME: " + this.title);
        System.out.println("NAME: " + this.author);
        
        if(this.renterID != -1){
            System.out.println("RENT DATE: " + this.rentDate);
            System.out.println("RETURN DATE: " + this.returnDate);
            System.out.println("RENTER ID: " + this.renterID);
        }
        
        System.out.println("Book was not rented");

        System.out.println("");    
    }

        
    // construtor  
    Book(){
        // Le um inteiro do arquivo numberOfBooks.txt
        Scanner scanner = null;
        int x = 0;
        try {
            scanner = new Scanner(new FileReader("numberOfBooks.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneralBook.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GeneralBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GeneralBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        escritor.println(x);
        escritor.close();
        scanner.close();
        
        Scanner s = new Scanner (System.in);

        System.out.println("Enter the Title: ");
        this.title = s.nextLine();
            
        System.out.println("Enter the Author: ");
        this.author = s.nextLine();
        
        System.out.println("Enter the Year: ");
        this.year = s.nextInt();
        
        this.type = ("book");
        
        this.renterID = -1;
        this.rentDate = "00/00/0000";
        this.returnDate = "00/00/0000";
    }
        
    Book(int bookID, String type, String title, String author, int year, String rentDate, String returnDate, int renterID){
        
        this.bookID = bookID;
        this.type = type;
        this.title = title;
        this.author = author;
        this.year = year;
        
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.renterID = renterID;
    }


    
}
