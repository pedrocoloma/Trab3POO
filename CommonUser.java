/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;


/**
 *
 * @author User
 */
public class CommonUser extends User{
    
    private final int limit; //Limite de quantos livros podem ser alguados
    

    CommonUser(){
        super();
        this.type = ("Common");
        this.limit = 2;
        //this.writeFile(); //Construtor para a primeira inserção em arquivo, ativar append
    }
    
    
    CommonUser(int userID, String type, String name, int numberOfRentedBooks, boolean active){
        this.userID = userID;
        this.name = name;
        this.type = type;
        this.limit = 2;        
        this.numberOfRentedBooks = numberOfRentedBooks;
        this.active = active;
    }
}
