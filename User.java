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
abstract public class User {
    
    protected String name;
    protected int userID;
    protected int numberOfRentedBooks; // numero de livros com o usuario
          
    
    public String getName(){
        if (this.name != null){ 
            return this.name;
        }
        else
            return (" ");
    }

    public int getID(){
        return this.userID;
    }
    
    public void writeFile(){
        // Le um inteiro do arquivo numberOfBooks.txt que eh o numero de usuarios da biblioteca
        
        Scanner scanner = null;
        int x = 0;
        try {
            scanner = new Scanner(new FileReader("numberOfUsers.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        }
          String usuarios = scanner.next();
          x = Integer.parseInt(usuarios);
           
        // Incrementa e salva no mesmo arquivo
        x++;
        //System.out.println(x);
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter("numberOfUsers.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        escritor.println(x);
        escritor.close();
                
        //Escreve os dados do usuários em arquivo
        try( FileWriter writer = new FileWriter("users.csv", true)) //Append books.csv
        {
           writer.write(""+this.name);
           writer.write(",");
           writer.write(""+this.numberOfRentedBooks);
           writer.write("\t");           
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    
    User(){//Construtor padrão para todos os usuários setará o nome e o ID
        this.numberOfRentedBooks=0;
        
        Scanner s = new Scanner (System.in);
        System.out.println("Enter the Name: ");
        this.name = s.nextLine();
        
    }
    
    
}
