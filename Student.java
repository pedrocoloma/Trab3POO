/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;


/**
 *
 * @author 8936843
 */
public class Student extends User {
    
    private final int limit; //Limite de quantos livros podem ser alguados
    
    Student(){
        super();
        this.type = ("Student");
        this.limit = 4;
        //this.writeFile();        //Construtor para a primeira inserÃ§Ã£o em arquivo, ativar append
    }
    
    Student(int userID, String type, String name, int numberOfRentedBooks, boolean active){
        this.userID = userID;
        this.name = name;
        this.type = type;
        this.limit = 4;        
        this.numberOfRentedBooks = numberOfRentedBooks;
        this.active = active;
    }  
}
