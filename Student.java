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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 8936843
 */
public class Student extends AcademicUser {
    
    private final int limit; //Limite de quantos livros podem ser alguados
    
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
           writer.write("Student,");
           writer.write(""+this.name);
           writer.write(",");
           writer.write(""+this.numberOfRentedBooks);
           writer.write("\t");           
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    Student(){
        this.limit = 4;
        this.numberOfRentedBooks = 0;
        
        this.writeFile();        
    }
}
