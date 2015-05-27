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
public class User {
    
    protected String name;
    protected int limit; //Limite de quantos livros podem ser alguados
    protected int numberOfRentedBooks; // numero de livros com o usuario
          
    
    public String getName(){
        if (this.name != null){ 
            return this.name;
        }
        else
            return (" ");
    }
    
    public void bookRent(Book b){
        
        
        
    }//Quando aluga, modifica o rent e return date do objeto Book
    public void bookReturn(){
    
    
    
    }//Reseta as datas do objeto Book
    
    
}
