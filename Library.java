/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Essa classe será responsável por administrar os arquivos com os arquivos
 * @author User
 */
public class Library {
    
    String libraryTime;
    ArrayList<User> listaDeUsuarios = new ArrayList();
    ArrayList<Book> listaDeLivros = new ArrayList();
    
    
    
    
    
    //Funciona
    public void montaListaDeUsuarios() throws IOException{
        
        BufferedReader s = null;
        String aux;
        
        try {
            s = new BufferedReader(new FileReader("users.csv"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        User recoveredUser = null;
        
        while(s.ready()){
            String data[] = null;
            try {
                data = s.readLine().split(",");
            } catch (IOException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            //DEBUG System.out.println("Chegou aqui");
            int userID;
            String type;
            String name;
            int numOfBooks;
            boolean active;
            //Fazer as transferências/conversões para os parâmetros do construtor
            
            userID = Integer.parseInt(data[0]);
            type = data[1];
            name = data[2];
            numOfBooks = Integer.parseInt(data[3]);
            active = Boolean.parseBoolean(data[4]);
           

            if(type.equalsIgnoreCase("Student")){
                recoveredUser = new User(userID, type, name, 4 ,numOfBooks, active);
            }
            else if(type.equalsIgnoreCase("Teacher")){
                recoveredUser = new User(userID, type, name, 6 ,numOfBooks, active);                    
            }                
            else if(type.equalsIgnoreCase("Common")){
                recoveredUser = new User(userID, type, name, 2 ,numOfBooks, active);                    
            }
            
            this.listaDeUsuarios.add(recoveredUser);
            
        }
        for(int i = 0; i < this.listaDeUsuarios.size(); i++){
                this.listaDeUsuarios.get(i).printUser();
            }
        s.close();
    }
    
    //Lê tudo, bota numa lista, modifica o valor necessário, apaga arquivo antigo e cria novo
    public void montaListaDeLivros() throws IOException{
        BufferedReader s = null;
        String aux;
        Book recoveredBook = null;
        
        try {
            s = new BufferedReader(new FileReader("books.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        

           writer.write(""+this.rentDate+",");           
           writer.write(""+this.returnDate+","); 
                writer.write(""+this.renterID);
           writer.write("\n");   
        */
        
        
        while(s.ready()){
            String data[] = null;
            try {
                data = s.readLine().split(",");
            } catch (IOException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            int bookID;
            String type;
            String title;
            String author;
            int year;
            int numOfBooks;
            boolean active;
                          
            bookID = Integer.parseInt(data[0]);
            type = data[1];
            title = data[2];
            author = data[3];
            numOfBooks = Integer.parseInt(data[3]);
            active = Boolean.parseBoolean(data[4]);
           /*
           if(type.equalsIgnoreCase("Student")){
                recoveredBook = new Book(bookID, type, title, 4 ,numOfBooks, active);
            }
            else if(type.equalsIgnoreCase("Teacher")){
                recoveredBook = new Book(bookID, type, title, 6 ,numOfBooks, active);                    
            }                
            else if(type.equalsIgnoreCase("Common")){
                recoveredBook = new Book(bookID, type, title, 2 ,numOfBooks, active);
            }
            listaDeUsuarios.add(recoveredUser);
           
*/
        }
    }
    
        
    public void writeUsers(){
    
        for (int i = 0 ; i<this.listaDeUsuarios.size(); i++) { //for (int i = 0 ; i<2; i++)
            this.listaDeUsuarios.get(i).writeFile();
        }
            
    }    
        
    //
    public void rewriteUsers(User updatedUser) throws IOException{
        
        BufferedReader s = null;
        String aux;
        ArrayList<User> allUsers = new ArrayList();
        
        try {
            s = new BufferedReader(new FileReader("users.csv"));
            //s.useDelimiter(",");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        User recoveredUser = null;
        
        while(s.ready()){
            String data[] = null;
            try {
                data = s.readLine().split(",");
            } catch (IOException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            //DEBUG System.out.println("Chegou aqui");
            int userID;
            String type;
            String name;
            int numOfBooks;
            boolean active;
            //Fazer as transferências/conversões para os parâmetros do construtor
            
            //aux = s.next();                
            userID = Integer.parseInt(data[0]);
            //aux = s.next();
            type = data[1];
            //aux = s.next();
            name = data[2];
            //aux = s.next();                
            numOfBooks = Integer.parseInt(data[3]);
            //aux = s.nextLine().replace(",","");
            active = Boolean.parseBoolean(data[4]);
           

            if(type.equalsIgnoreCase("Student")){
                recoveredUser = new User(userID, type, name, 4 ,numOfBooks, active);
                //Student recoveredUser = new Student(userID, type, name, 4 ,numOfBooks, active);
            }
            else if(type.equalsIgnoreCase("Teacher")){
                //Teacher recoveredUser = new Teacher(userID, type, name, numOfBooks, active);
                recoveredUser = new User(userID, type, name, 6 ,numOfBooks, active);                    
            }                
            else if(type.equalsIgnoreCase("Common")){
                recoveredUser = new User(userID, type, name, 2 ,numOfBooks, active);                    
                //CommonUser recoveredUser = new CommonUser(userID, type, name, numOfBooks, active);
            }
            
            if(userID == updatedUser.getID()){
                //updatedUser.printUser();
                allUsers.add(updatedUser);
            }
            else{
                //recoveredUser.printUser();
                allUsers.add(recoveredUser);
            } 
        //Lê tudo, bota numa lista, modifica o valor necessário, apaga arquivo antigo e cria novo
        }
        System.out.println("");
        /*for (User allUser : allUsers) {
            allUser.printUser();
        }*/
        //s.close();
        
       /*File usersOld = new File("usersOld.csv");
       
        File usersCurrent = new File("users.csv");
        boolean rename = usersCurrent.renameTo(usersOld);
        
        System.out.println("rename"+rename);
        
        File usersNew = new File("users.csv");
        usersNew.createNewFile();
        try {
            usersNew.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        // APAGA O ARQUIVO
        Path path = Paths.get("users.csv");
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        
        for (int i = 0 ; i<allUsers.size(); i++) { //for (int i = 0 ; i<2; i++)
            allUsers.get(i).rewriteFile();
            System.out.println("##############");
            System.out.println(allUsers.get(i).getName());
            System.out.println("##############");
        }
    }
    
    public void printAllUsers(){
        
        Scanner s = null;
        try {
            s = new Scanner(new FileReader("users.csv"));
            s.useDelimiter(",");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        while (s.hasNextLine()) {
            
            String id = s.next();
            String type = s.next();
            String name = s.next();
            String currentBooks = s.next();
            String active = s.nextLine().replace(",","");//Ignora a ultima virgula para ler o ultimo campo

            
            System.out.println("USER ID: " + id);    
            System.out.println("TYPE: " + type);
            System.out.println("NAME: " + name);
            System.out.println("BOOKS CURRENTLY RENTED: " + currentBooks);
            System.out.println("ACTIVE: "+active);
            
            System.out.println("");
           // System.out.println(s.next());
         }
        s.close();
    }        
    
    public User userExists(String id){//Verifica se o usuário existe no arquivo, se existir, retorna uma instância de User
        String aux;
        Scanner s = null;
        try {
            s = new Scanner(new FileReader("users.csv"));
            s.useDelimiter(",");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(s.hasNextLine()){
            aux = s.next();
            
            if(aux.equals(id)){//usuário encontrado
               //DEBUG System.out.println("Chegou aqui");
                int userID;
                String type;
                String name;
                int numOfBooks;
                boolean active;
                //Fazer as transferências/conversões para os parâmetros do construtor
                userID = Integer.parseInt(aux);
                aux = s.next();
                type = aux;
                aux = s.next();
                name = aux;
                aux = s.next();                
                numOfBooks = Integer.parseInt(aux);
                aux = s.nextLine().replace(",","");
                active = Boolean.parseBoolean(aux);
  
                if(type.equalsIgnoreCase("Student")){
                    System.out.println("caiu no student");
                    User recoveredUser;
                    recoveredUser = new User(userID, type, name, 4 ,numOfBooks, active);
                    //Student recoveredUser = new Student(userID, type, name, 4 ,numOfBooks, active);
                    s.close();
                    return recoveredUser;
                }
                else if(type.equalsIgnoreCase("Teacher")){
                    System.out.println("caiu no student");                    
                    //Teacher recoveredUser = new Teacher(userID, type, name, numOfBooks, active);
                    User recoveredUser = new User(userID, type, name, 6 ,numOfBooks, active);         
                    s.close();
                    return recoveredUser;
                }                
                else if(type.equalsIgnoreCase("Common")){
                    System.out.println("caiu no student");     
                    User recoveredUser = new User(userID, type, name, 2 ,numOfBooks, active);                    
                    //CommonUser recoveredUser = new CommonUser(userID, type, name, numOfBooks, active);
                    s.close();
                    return recoveredUser;
                }
                else{
                    s.close();
                    return null;
                }
            }
            else{
                aux = s.nextLine();
            }
        }
        s.close();
        return null;//Nada foi encontrado
    }

    public Book bookExists(String id){//Verifica se o usuário existe no arquivo, se existir, retorna uma instância de User
        String aux;
        Scanner s = null;
        try {
            s = new Scanner(new FileReader("books.csv"));
            s.useDelimiter(",");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(s.hasNextLine()){
            aux = s.next();
            
            if(aux.equals(id)){//O usuário existe
               //DEBUG System.out.println("Chegou aqui");
                int bookID;
                String type;
                String title;
                String author;
                int year;
                String rentDate;
                String returnDate;
                int renterID;
                

                //Fazer as transferências/conversões para os parâmetros do construtor
                bookID = Integer.parseInt(aux);
                aux = s.next();
                
                type = aux;
                aux = s.next();
                
                title = aux;
                aux = s.next();
                
                author = aux;
                aux = s.next();             
                
                year = Integer.parseInt(aux);
                aux = s.next();             
                
                rentDate = aux;
                aux = s.next();
                
                returnDate = aux;
                
                aux = s.nextLine().replace(",","");                
                renterID = Integer.parseInt(aux);
               
                  
                if(type.equalsIgnoreCase("Text")){
                    System.out.println("caiu no Text");
                    Book recoveredBook;
                    recoveredBook = new Book(bookID, type, title, author, year, rentDate, returnDate, renterID);
                    s.close();
                    //Student recoveredUser = new Student(userID, type, name, 4 ,numOfBooks, active);
                    return recoveredBook;
                }
                else if(type.equalsIgnoreCase("General")){
                    System.out.println("caiu no General");                    
                    //Teacher recoveredUser = new Teacher(userID, type, name, numOfBooks, active);
                    Book recoveredBook = new Book(bookID, type, title, author, year, rentDate, returnDate, renterID);     
                    s.close();
                    return recoveredBook;
                }
                else{
                    s.close();
                    return null;
                }
            }
            else{
                aux = s.nextLine();
            }
        }
        s.close();
        return null;//Nada foi encontrado
    }    
    
    public void rentBook(){
        
        String userIDaux;
        String bookIDaux;
        Scanner s = new Scanner (System.in);
        
        //PASSO 1 - Receber IDs
 
        System.out.println("Type the User ID");
        userIDaux = s.nextLine();
        User renter = this.userExists(userIDaux);//Procura o possível locatário
        if(renter == null){
            System.out.println("ERROR: USER NOT FOUND");
            return;
        }
        if(renter.getActive() == false){
            System.out.println("ERROR: USER ACTIVITY IS SUSPENDED");
            return;
        }
        if(renter.getLimit() == renter.getNumberOfRentedBooks()){
            System.out.println("ERROR: LIMIT OF RENTALS REACHED");
            return;        
        }
        
        
        System.out.println("Type the Book ID");
        bookIDaux = s.nextLine();
        Book book = this.bookExists(bookIDaux);
        if(book == null){
            System.out.println("ERROR: BOOK NOT FOUND");
            return;
        }
        if(book.getRenterID() != -1){
            System.out.println("ERROR: THE BOOK IS RENTED");
            return;
        }        
        
        if(book.getType().equalsIgnoreCase("text") && renter.getType().equalsIgnoreCase("Common")){
            System.out.println("ERROR: USER DOES NOT HAVE TEXT BOOK RENTING AUTHORIZATION");
            return;        //Trata o único caso de erro
        }
        
        //Aluguel é possível, setar as variáveis
        book.setRenterID(renter.userID);
        
        // incrementa o numero de livros com o usuario
        renter.incNumberOfRentedBooks();
        
        // Pega a data de hoje no formato dia/mes/ano
        String today, returnDay; // essa string sera salva no books.csv como rentDate
        
        // salva o rentDate
        book.setRentDate(this.libraryTime);
        
        // verifica qual é o prazo de entrega
        int days = -1;
        if(renter.getType().equalsIgnoreCase("teacher")){
            days = 60;
        }else{
            days = 15;
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//Usado para formatar Date->Sring e String->Date

	//String dateInString = "07/06/2013";
        Date date2 = null;
	try {
            date2 = formatter.parse(this.libraryTime);//Converte a String LibraryTime em Date
	} catch (ParseException e) {
		e.printStackTrace();
	}        
               
        Calendar c = Calendar.getInstance();
        c.setTime(date2);
        //Cria instância de calendário e seta o tempo dele para a data da biblioteca
        
        c.add(Calendar.DATE, days);
        Date date3 = c.getTime();
        returnDay = formatter.format(date3);
        //Adiciona o numero de dias até o vencimento, converte em String por formatter e armazena em returnDay
        
        System.out.println("User has until "+returnDay+" to return the book");
        book.setReturnDate(returnDay);
        
        try {
            this.rewriteUsers(renter);
            //2 REFERENCIAS DE OBJETOS E INICIALIZA COM AS COISAS LOCAS LÁ 
        } catch (IOException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    Library(){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDate = new Date();
        String strDate = formatter.format(currentDate);

	try {
            Date date = formatter.parse(strDate);
	} catch (ParseException e) {
            e.printStackTrace();
	}
        
        this.listaDeLivros = new ArrayList();
        this.listaDeUsuarios = new ArrayList();
        this.libraryTime = strDate;
        
        System.out.println("Current Date: " + this.libraryTime);
   }
    
}
