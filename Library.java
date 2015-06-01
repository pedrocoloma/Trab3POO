
package trabalho3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    
    
    
    public void rentBook(){
        
        int userIDaux;
        int bookIDaux;
        Scanner s = new Scanner (System.in);
        
        //PASSO 1 - Receber IDs
 
        System.out.println("Type the User ID");
        userIDaux = s.nextInt();

        int renter = this.userExists(userIDaux);//Procura o possível locatário
        if(renter == -1){
            System.out.println("ERROR: USER NOT FOUND");
            return;
        }
        else if(renter == -2){
            System.out.println("ERROR: EMPTY USER LIST");
            return;
        }        
        if(this.listaDeUsuarios.get(renter).getActive() == false){
            System.out.println("ERROR: USER ACTIVITY IS SUSPENDED");
            return;
        }
        if(this.listaDeUsuarios.get(renter).getLimit() == this.listaDeUsuarios.get(renter).getNumberOfRentedBooks()){
            System.out.println("ERROR: LIMIT OF RENTALS REACHED");
            return;        
        }
        
        
        System.out.println("Type the Book ID");
        bookIDaux = s.nextInt();
        int book = this.bookExists(bookIDaux);
        if(book == -1){
            System.out.println("ERROR: BOOK NOT FOUND");
            return;
        }
        else if(book == -2){
            System.out.println("ERROR: EMPTY BOOK LIST");
            return;
        }        
        if(this.listaDeLivros.get(book).getRenterID() != -1){
            System.out.println("ERROR: THE BOOK IS RENTED");
            return;
        }        
        
        if(this.listaDeLivros.get(book).getType().equalsIgnoreCase("text") && this.listaDeUsuarios.get(renter).getType().equalsIgnoreCase("Common")){
            System.out.println("ERROR: USER DOES NOT HAVE TEXT BOOK RENTING AUTHORIZATION");
            return;        //Trata o único caso de erro
        }
        
        //Aluguel é possível, setar as variáveis
        this.listaDeLivros.get(book).setRenterID(this.listaDeUsuarios.get(renter).getID());
        
        // incrementa o numero de livros com o usuario
        this.listaDeUsuarios.get(renter).incNumberOfRentedBooks();
        
        // Pega a data de hoje no formato dia/mes/ano
        String today, returnDay; // essa string sera salva no books.csv como rentDate
        
        // salva o rentDate,
        this.listaDeLivros.get(book).setRentDate(this.libraryTime);
        
        // verifica qual é o prazo de entrega
        int days = -1;
        if(this.listaDeUsuarios.get(renter).getType().equalsIgnoreCase("teacher")){
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
        this.listaDeLivros.get(book).setReturnDate(returnDay);
        
        String auxLog1 = (""+userIDaux);        
        String auxLog2 = (""+bookIDaux);        
        this.writeLog(auxLog1,"Rented ", auxLog2);
            
    }
    
    public void returnBook(){
    
        int userIDaux;
        int bookIDaux;
        Scanner s = new Scanner (System.in);
        
        //PASSO 1 - Receber IDs
 
        System.out.println("Type the User ID");
        userIDaux = s.nextInt();

        int renter = this.userExists(userIDaux);//Procura o possível locatário
        if(renter == -1){
            System.out.println("ERROR: USER NOT FOUND");
            return;
        }
        else if(renter == -2){
            System.out.println("ERROR: EMPTY USER LIST");
            return;
        }        
        if(this.listaDeUsuarios.get(renter).getNumberOfRentedBooks() == 0){
            System.out.println("ERROR: USER HAS NO RENTED BOOKS");
            return;        
        }
        
        
        System.out.println("Type the Book ID");
        bookIDaux = s.nextInt();
        int book = this.bookExists(bookIDaux);
        if(book == -1){
            System.out.println("ERROR: BOOK NOT FOUND");
            return;
        }
        else if(book == -2){
            System.out.println("ERROR: EMPTY BOOK LIST");
            return;
        }        
        if(this.listaDeLivros.get(book).getRenterID() != this.listaDeUsuarios.get(renter).getID()){
            System.out.println("ERROR: USER IS NOT THE ORIGINAL RENTER");
            return;
        }        
        
        //Cria DATEs para verificação de data
        Date libTime = null;
        Date returnDate = null;
        Date rentDate = null;
        Date reactivationDate = null;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	try {
		libTime = formatter.parse(this.libraryTime);
 
	} catch (ParseException e) {
		e.printStackTrace();
	}        
	try {
 
		returnDate = formatter.parse(this.listaDeLivros.get(book).getReturnDate());

 
	} catch (ParseException e) {
		e.printStackTrace();
	} 
	try {
		rentDate = formatter.parse(this.listaDeLivros.get(book).getRentDate());
 
	} catch (ParseException e) {
		e.printStackTrace();
	}                 
        
        if(libTime==null || returnDate==null || rentDate ==null){//Condião de erro
            System.out.println("ERROR: Null Date");
            return;
        }
        
        long diff = libTime.getTime() - returnDate.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));//Calcula os dias


        
        if(diff<=0){
            System.out.println("Return complete");
        }
        else{
        System.out.println("User is late by:" + diff + " days.");        
        }
        
        //Aluguel é possível, setar as variáveis
        this.listaDeLivros.get(book).setRenterID(-1);
        
        // incrementa o numero de livros com o usuario
        this.listaDeUsuarios.get(renter).decNumberOfRentedBooks();
        
        // salva o rentDate,
        this.listaDeLivros.get(book).setRentDate("00/00/0000");
        this.listaDeLivros.get(book).setReturnDate("00/00/0000");
        
        String auxLog1 = (""+userIDaux);        
        String auxLog2 = (""+bookIDaux);        
        this.writeLog(auxLog1,"Returned ", auxLog2);
    }
    
    
    public void delayCheck(int pos){
        //Calcula se o livro está atrasado e modifica os valores do usuário

        int renter = this.userExists(this.listaDeLivros.get(pos).getRenterID());//Obtem a posição na lista de usuarios do ID em questão
        
        if(renter == -1 || renter == -2){
            return;
        }
        
        //Cria DATEs para verificação de data
        Date libTime = null;
        Date returnDate = null;
        Date reactivationDate = null;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	try {
		libTime = formatter.parse(this.libraryTime);
 
	} catch (ParseException e) {
		e.printStackTrace();
	}        
	try {
 
		returnDate = formatter.parse(this.listaDeLivros.get(pos).getReturnDate());
 
	} catch (ParseException e) {
		e.printStackTrace();
	}       
        
        if(libTime==null || returnDate==null){//Condião de erro
            System.out.println("ERROR: Null Date");
            return;
        }
        
        long diff = libTime.getTime() - returnDate.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));//Calcula os dias

        /*System.out.println("Difference between  " + libTime + " and "+ returnDate+" is "
                + diff + " days.");*/
        
        if(diff <= 0){//Nao Atrasado
            return;
        }      
        
        
        //Usuario atrasdo
        this.listaDeUsuarios.get(renter).setActive(false);
        Integer days = (int)(long) diff;
        System.out.println("\nWARNING: The rental of the book");
        this.listaDeLivros.get(pos).printBook();
        System.out.println("is expired by " + days + " days.");        
        
        if("00/00/0000".equals(this.listaDeUsuarios.get(renter).getReactivationDate())){//Usuario não tinha nenhuma pendência até o momento
                    
            Calendar c = Calendar.getInstance();
            c.setTime(libTime);
            //Cria instância de calendário e seta o tempo dele para a data da biblioteca

            c.add(Calendar.DATE, days);//Incrementa os dias de suspensão
            reactivationDate = c.getTime();
            String reactivationDateString;        
            reactivationDateString = formatter.format(reactivationDate);
            this.listaDeUsuarios.get(renter).setReactivationDate(reactivationDateString);
            //Escreve no Log
            String auxLog1 = (""+this.listaDeUsuarios.get(renter).getID());        
            String auxLog2 = ("Changed Reactivation Date to" + reactivationDateString);
            this.writeLog("System","Deactivated ", auxLog1);                        
            this.writeLog("System",auxLog2, auxLog1);            
            return;
        }
        else{//Usuario já tem já tem pendência
            try {
                reactivationDate = formatter.parse(this.listaDeUsuarios.get(renter).getReactivationDate());
                /*System.out.println(reactivationDate);
                System.out.println(formatter.format(reactivationDate));*/
            } catch (ParseException e) {
                    e.printStackTrace();
            }    
            Calendar c = Calendar.getInstance();
            c.setTime(reactivationDate);
            //Cria instância de calendário e seta o tempo dele para a data da biblioteca

            c.add(Calendar.DATE, 1);//Incrementa os dias de suspensão
            reactivationDate = c.getTime();
            String reactivationDateString;        
            reactivationDateString = formatter.format(reactivationDate);
            this.listaDeUsuarios.get(renter).setReactivationDate(reactivationDateString);
            
            //Escreve no Log
            String auxLog1 = (""+this.listaDeUsuarios.get(renter).getID());        
            String auxLog2 = ("Changed Reactivation Date to " + reactivationDateString);
            this.writeLog("System",auxLog2, auxLog1);     
            return;        
        }
        
    }
    
    public void reactivationCheck(int pos){
    
        Date libTime = null;
        Date reactivationDate = null;
        
        
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
	try {
            libTime = formatter.parse(this.libraryTime);
 
	} catch (ParseException e) {
		e.printStackTrace();
	}        
	try {
            reactivationDate = formatter.parse(this.listaDeUsuarios.get(pos).getReactivationDate());
 
	} catch (ParseException e) {
		e.printStackTrace();
	}           
        
        
        if(libTime == null || reactivationDate == null){
            System.out.println("ERROR: Null Date");
        }
        
        
        long diff = libTime.getTime() - reactivationDate.getTime();
        diff = (diff / (1000 * 60 * 60 * 24));//Calcula os dias

        /*System.out.println("Difference between  " + libTime + " and "+ reactivationDate+" is "
                + diff + " days.");*/
        
        if(diff >= 0){//É ou passou da data
            this.listaDeUsuarios.get(pos).setActive(true);
             this.listaDeUsuarios.get(pos).setReactivationDate("00/00/0000");
             
            String auxLog1 = (""+this.listaDeUsuarios.get(pos).getID());        
            this.writeLog("System","Reactivated", auxLog1);             
            return;
        }        
        
    }
    
    
    //Funciona
    public void montaListaDeUsuarios() throws IOException{
        
        BufferedReader s = null;
        String aux;
        int pos = 0;
        
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
            String reactivationDate;
            //Fazer as transferências/conversões para os parâmetros do construtor
            
            userID = Integer.parseInt(data[0]);
            type = data[1];
            name = data[2];
            numOfBooks = Integer.parseInt(data[3]);
            active = Boolean.parseBoolean(data[4]);
            reactivationDate = data[5];
           

            if(type.equalsIgnoreCase("Student")){
                recoveredUser = new User(userID, type, name, 4 ,numOfBooks, active, reactivationDate);
            }
            else if(type.equalsIgnoreCase("Teacher")){
                recoveredUser = new User(userID, type, name, 6 ,numOfBooks, active, reactivationDate);                    
            }                
            else if(type.equalsIgnoreCase("Common")){
                recoveredUser = new User(userID, type, name, 2 ,numOfBooks, active, reactivationDate);                    
            }
            
            this.listaDeUsuarios.add(recoveredUser);
            if(this.listaDeUsuarios.get(pos).getActive() == false){//Usuario esta desativado, checar se podemos reativar
                this.reactivationCheck(pos);
            }
            pos++;            
        }
        //this.printAllUsers();
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
        int pos = 0;
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
            String rentDate;
            String returnDate;
            int renterID;
                          
            bookID = Integer.parseInt(data[0]);
            type = data[1];
            title = data[2];
            author = data[3];
            year = Integer.parseInt(data[4]);
            rentDate = data[5];
            returnDate = data[6];
            renterID = Integer.parseInt(data[7]);
            
            Book livro = new Book(bookID,  type,  title,  author, year, rentDate,  returnDate, renterID);
            
            this.listaDeLivros.add(livro);
            this.delayCheck(pos);
            pos++;
        }
        //this.printAllBooks();
        s.close();
    }
    
    
    
    public void printAllBooks(){
        for(int i = 0; i < this.listaDeLivros.size(); i++){
            this.listaDeLivros.get(i).printBook();
        }        
    }
    
    public void printAllUsers(){
        for(int i = 0; i < this.listaDeUsuarios.size(); i++){
                this.listaDeUsuarios.get(i).printUser();
        }
    }    
        
    
    
    public void writeUsers(){
    
        for (int i = 0 ; i<this.listaDeUsuarios.size(); i++) { //for (int i = 0 ; i<2; i++)
            this.listaDeUsuarios.get(i).writeFile();
        }
            
    }    
    
    public void writeBooks(){
        for (int i = 0 ; i<this.listaDeLivros.size(); i++) { //for (int i = 0 ; i<2; i++)
            this.listaDeLivros.get(i).writeFile();
        }
    }        
            
    
    
    
    public int userExists(int id){//Verifica se o usuário existe no arquivo, se existir, retorna uma instância de User
      
      int pos = -1;
      int i = 0;
      if(this.listaDeUsuarios.size()==0){
          pos = -2;
      }
      else{
          while(i < this.listaDeUsuarios.size()){
              if(this.listaDeUsuarios.get(i).getID() == id){
                  pos = i;
              }
              i++;
          }
      }
      return pos;
    }

    public int bookExists(int id){//Verifica se o usuário existe no arquivo, se existir, retorna uma instância de User
      int pos = -1;
      int i = 0;
      if(this.listaDeLivros.size()==0){
          pos = -2;
      }
      else{
          while(i < this.listaDeLivros.size()){
              if(this.listaDeLivros.get(i).getBookID()== id){
                  pos = i;
              }
              i++;
          }
      }
      return pos;
    }    
    
    
    public void timeLeap(){
        Scanner s  = new Scanner(System.in);
        System.out.println("Type in the new Date in the following format:\ndd/MM/yyyy\nPress Q to return");
        String newTime, cmd;
        
        newTime = s.next();
        if(newTime.equalsIgnoreCase("Q")){
            return;
        }
        else{
            System.out.println("Are you ABSOLUTLY sure that the correct date is "+newTime+", that it follows the dd/MM/yyyy format and that you want to change the date?");
            System.out.println("WARNING: A wrong date format could possibly compromise the other functionalities of the system\nY/N");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            cmd = s.next();
            
            
            if(cmd.equalsIgnoreCase("N")){
                return;
            }
            else if(cmd.equalsIgnoreCase("Y")){
                System.out.println("WARNING: FLUX CAPACITOR ACTIVATED\nINITIATING TIME LEAP");
                this.libraryTime = newTime;
                
                //Recheca todas as validades
                for (int i = 0; i < this.listaDeLivros.size(); i++) {
                    this.delayCheck(i);
                }
                for (int i = 0; i < this.listaDeUsuarios.size(); i++) {
                    this.reactivationCheck(i);
                }          
                System.out.println("TIME LEAP COMPLETED");
            }
            else{
                return;
            }
        }
    
    }

    public void writeLog(String subjectA, String action, String subjectB){
    
        try(FileWriter writer = new FileWriter("log.csv", true)) //Append books.csv
        {
           writer.write(""+this.libraryTime+",");
           writer.write(""+subjectA+",");
           writer.write(""+action+",");
           writer.write(""+subjectB);
           writer.write("\n");  
           writer.close();                      
        } catch (IOException ex) {
            Logger.getLogger(Trabalho3.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void printLog(){
        

        BufferedReader s = null;
        String aux;
        Book recoveredBook = null;
        
        try {
            s = new BufferedReader(new FileReader("log.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while(s.ready()){
                String data[] = null;
                try {
                    data = s.readLine().split(",");
                } catch (IOException ex) {
                    Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                String date;
                String actor;
                String action;
                String subjectB;
                
                
                date = data[0];
                actor = data[1];
                action = data[2];
                subjectB = data[3];
                
                System.out.println("DATE: "+date);
                System.out.println("ACTOR: "+actor);
                System.out.println("ACTION: "+action);
                System.out.println("SUBJECT: "+subjectB);
                System.out.println("");
            }
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
        
        //System.out.println("Current Date: " + strDate);
   }
    
}
