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
public class Teacher extends User{
    
    private final int limit; //Limite de quantos livros podem ser alguados
    
    public int getLimit(){
        return this.limit;
    }

    Teacher(){
        super();
        this.type = ("Teacher");
        this.limit = 6;
        //this.writeFile();        //Construtor para a primeira inserção em arquivo, ativar append
    }
    Teacher(int userID, String type, String name, int numberOfRentedBooks, boolean active){
        this.userID = userID;
        this.name = name;
        this.type = type;
        this.limit = 6;        
        this.numberOfRentedBooks = numberOfRentedBooks;
        this.active = active;
    }
}
