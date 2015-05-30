/*
 * TRABALHO 3 - P.O.O.
 * OVERVIEW:
 * 
 * João Victor Lopes da Silva Guimarães 8936843
 * Pedro Felipe Coloma de Araújo 8936781
 */
package trabalho3;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;


public class Trabalho3 {

    public static void main(String[] args) throws IOException {  
        
        boolean exitflag = false;
        String cmd, cmdAux;
        String aux;
        Scanner s = new Scanner (System.in);

        // CRIA OS ARQUIVOS
        File books = new File("books.csv");
        File users = new File("users.csv");
        File log = new File("log.csv");
        File numberOfUsers = new File ("numberOfUsers.txt");
        File numberOfBooks = new File("numberOfBooks.txt");// contém o numero de livros e de usuários
        
        Library lib = new Library();
     
        if(!books.exists()){//Cria o arquivo se não existente
            try {
                books.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           // lib.montaListaDeLivros();
        }
        if(!users.exists()){//Cria o arquivo se não existente
            try {
                users.createNewFile();
                lib.montaListaDeUsuarios();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            lib.montaListaDeUsuarios();
        }
        if(!log.exists()){//Cria o arquivo se não existente
            try {
                log.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        //Verifica se existe o arquivo com o numero de usuários. Se não existe, cria o arquivo e o inicializa com 0
        if(!numberOfUsers.exists()){
            try {
                numberOfUsers.createNewFile();
                        PrintWriter escritor = null;
                try {
                    escritor = new PrintWriter("numberOfUsers.txt", "UTF-8");
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
//Deleta versão antiga dos arquivos
    //books.delete();
    Path path = Paths.get("users.csv");
    try {
            
        Files.delete(path);
        //Files.deleteIfExists(path);
    } catch (NoSuchFileException x) {
        System.err.format("%s: no such" + " file or directory%n", path);
    } catch (DirectoryNotEmptyException x) {
        System.err.format("%s not empty%n", path);
    } catch (IOException x) {
        // File permission problems are caught here.
         System.err.println(x);
    }
//------------------------------------------------------------------------------        
//Command Loop        
        
        while(true){
            System.out.println("B.Insert Book\tU.Insert User\tN.New Rental\nQ.Quit\n");
            cmd = s.next();
            
            if(cmd.equalsIgnoreCase("B")){//INSERT BOOKS
                System.out.println("T. Text Book\tG.Regular Book\nQ.Quit\n");
                cmdAux = s.next();
                    
                if(cmdAux.equalsIgnoreCase("T")){
                    TextBook t1 = new TextBook();
                    //lib.listaDeUsuarios.add(t1)
                }
                else if(cmdAux.equalsIgnoreCase("G")){
                    GeneralBook b1 = new GeneralBook();
                }
            }//END OF INSERT BOOKS
            
            else if(cmd.equalsIgnoreCase("U")){//INSERT USER
                
                System.out.println("S.Student\tT.Teacher\tC.Common User\nQ.Quit");
                cmdAux = s.next();
                    
                if(cmdAux.equalsIgnoreCase("S")){
                    Student s1 = new Student();
                    lib.listaDeUsuarios.add(s1);
                }
                else if(cmdAux.equalsIgnoreCase("T")){
                    Teacher t1 = new Teacher();
                    lib.listaDeUsuarios.add(t1);                    
                }
                else if(cmdAux.equalsIgnoreCase("C")){
                    CommonUser c1 = new CommonUser();
                    lib.listaDeUsuarios.add(c1);                    
                }
            }//END OF INSERT USER
            
            else if(cmd.equalsIgnoreCase("N")){//NEW RENTAL
                lib.rentBook();
            }            
            else if(cmd.equalsIgnoreCase("Q")){// QUIT
                // ## SALVA AS LISTAS NOS ARQUIVOS
                lib.writeUsers();
                System.out.println("End of program.");
                break;
            }
            
            
        }
        
        //lib.printAllBooks();
        //lib.printAllUsers();
    }
    
}
