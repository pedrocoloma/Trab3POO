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
        File numberOfUsers = new File ("numberOfUsers.txt");
        
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
            
            else if(cmd.equalsIgnoreCase("U")){//INSERT USER
                
                System.out.println("S.Student\tT.Teacher\tC.Common User\nQ.Quit");
                cmdAux = s.next();
                    
                if(cmdAux.equalsIgnoreCase("S")){
                    Student s1 = new Student();
                }
                else if(cmdAux.equalsIgnoreCase("T")){
                    Teacher t1 = new Teacher();
                }
                else if(cmdAux.equalsIgnoreCase("C")){
                    CommonUser c1 = new CommonUser();
                }
            }//END OF INSERT USER
            
            else if(cmd.equalsIgnoreCase("Q")){
                exitflag = true;
            }
        }
                
    }
    
}
