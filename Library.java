/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Essa classe será responsável por administrar os arquivos com os arquivos
 * @author User
 */
public class Library {
    
    GregorianCalendar libraryTime;
    
    //Método genérico utilizado para escrever uma string num arquivo

        
    Library(){
        
        Date currentDate = new Date();
        
        this.libraryTime = new GregorianCalendar();
        this.libraryTime.setTime(currentDate);
        
        System.out.println("Current Date: " + this.libraryTime.getTime() );
   }
    
}
