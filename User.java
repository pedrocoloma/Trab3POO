/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;


import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class User {
    
    protected String name;
    protected String type = ("none");
    private final int limit;
    protected int userID;
    protected int numberOfRentedBooks; // numero de livros com o usuario
    protected boolean active;
    
    // Getters
    public int getLimit(){
        return this.limit;
    }    
    public boolean getActive(){
        return this.active;
    }
    public String getName(){
        if (this.name != null){ 
            return this.name;
        }
        else
            return (" ");
    }
    public int getNumberOfRentedBooks(){
        return this.numberOfRentedBooks;
    }
    public int getID(){
        return this.userID;
    }
    public String getType(){
        return this.type;
    }
    
    // Setters
    public void setID(int id){
        this.userID = id;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public void decNumberOfRentedBooks(){ // decrementa o numero de livros com o usuario
        this.numberOfRentedBooks -= 1;
    }
    public void incNumberOfRentedBooks(){ // incrementa o numero de livros com o usuario
        this.numberOfRentedBooks += 1;
    }

    
    public void printUser(){
            System.out.println("USER ID: " + this.userID);    
            System.out.println("TYPE: " + this.type);
            System.out.println("NAME: " + this.name);
            System.out.println("BOOKS CURRENTLY RENTED: " + this.numberOfRentedBooks);
            System.out.println("ACTIVE: "+this.active);
            System.out.println("");
    }
    
    
    public void writeFile(){//Escreve pela primeira vez

        //Escreve os dados do usuários em arquivo
        try( FileWriter writer = new FileWriter("users.csv", true)) //Append books.csv
        {
           writer.write(""+this.userID);
           writer.write(",");
           writer.write(""+this.type);
           writer.write(",");           
           writer.write(""+this.name);
           writer.write(",");
           writer.write(""+this.numberOfRentedBooks);
           writer.write(",");
           writer.write(""+this.active);           
           writer.write("\n");           
           writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rewriteFile() throws FileNotFoundException, IOException{
                
        //Escreve os dados do usuários em arquivo
        
        File f = new File("users.csv"); 
        PrintWriter a = new PrintWriter(f);
        try (BufferedWriter bw = new BufferedWriter(a)) {
            bw.write(""+this.userID);
            bw.write(",");
            bw.write(""+this.type);
            bw.write(",");
            bw.write(""+this.name);
            bw.write(",");
            bw.write(""+this.numberOfRentedBooks);
            bw.write(",");
            bw.write(""+this.active);
            bw.write("\n");
            System.out.println("Chegou aqui!!"+this.numberOfRentedBooks);
            bw.close();

        }
    }    

    
    
    User(){
        //Construtor padrão inicializa os valores pela primeira vez
        Scanner scanner = null;
        int x = 0;
        try {
            scanner = new Scanner(new FileReader("numberOfUsers.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
          String usuarios = scanner.next();
          x = Integer.parseInt(usuarios);
           
        // Incrementa e salva no mesmo arquivo
        x++;
        this.userID = x;
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter("numberOfUsers.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        escritor.println(x);
        escritor.close();
        scanner.close();                
        
        this.limit = 0;
        this.numberOfRentedBooks=0;
        this.active = true;
        Scanner s = new Scanner (System.in);
        System.out.println("Enter the Name: ");
        this.name = s.nextLine();
    }
    
    //Construtor para gerar instância do objeto a partir de dados de arquivo
    User(int userID, String type, String name, int limit ,int numberOfRentedBooks, boolean active){
        this.userID = userID;
        this.name = name;
        this.type = type;
        this.limit = limit;        
        this.numberOfRentedBooks = numberOfRentedBooks;
        this.active = active;
    }
    
}
