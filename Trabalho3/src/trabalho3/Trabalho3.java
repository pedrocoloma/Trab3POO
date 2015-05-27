/*
 * TRABALHO 3 - P.O.O.
 * OVERVIEW:
 * 
 * João Victor Lopes da Silva Guimarães 8936843
 * Pedro Felipe Coloma de Araújo 8936781
 */
package trabalho3;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.opencsv.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Trabalho3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        
        boolean exitflag = false;
        String cmd, cmdAux;
        Scanner s = new Scanner (System.in);
                
        File books = new File("books.csv");
        File users = new File("users.csv");
        File numberOfBooks = new File("numberOfBooks.txt");// contém o numero de livros e de usuários

        if(!books.exists()){//Cria o arquivo se não existente
            try {
                books.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!users.exists()){//Cria o arquivo se não existente
            try {
                users.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!numberOfBooks.exists()){
            try {
                numberOfBooks.createNewFile();
                        PrintWriter escritor = null;
                try {
                    escritor = new PrintWriter("numberOfBooks.txt", "UTF-8");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(TextBook.class.getName()).log(Level.SEVERE, null, ex);
                }
                escritor.println("0");
                escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//------------------------------------------------------------------------------        
//Command Loop        
        
        while(exitflag == false){
            System.out.println("B.Insert Book\tU.Insert User\nQ.Quit\n");
            cmd = s.next();
            
            if(cmd.equalsIgnoreCase("B")){//INSERT BOOKS
                
                System.out.println("T. Text Book\tG.Regular Book\nQ.Quit");
                cmdAux = s.next();
                    
                if(cmdAux.equalsIgnoreCase("T")){
                    TextBook t1 = new TextBook();
                }
                else if(cmdAux.equalsIgnoreCase("G")){
                    GeneralBook b1 = new GeneralBook();
                }
            }//END OF INSERT BOOKS

            else if(cmd.equalsIgnoreCase("Q")){
                exitflag = true;
            }
        }
                
    }
    
}
