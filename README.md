# Trabalho 3 de POO - Sistema de Biblioteca
Repositório do Trabalho 3 da matéria de SSC0103 - Programação Orientada a Objetos.<br />
Descrição do trabalho: https://goo.gl/krggc4<br />
<br />

## Autores:<br />
João Victor Guimarães - 8936843<br />
Pedro Felipe Coloma - 8936781<br />
<br />

## Informações Gerais
### Versões usadas
Usamos a IDE Netbeans 8.0.2 e Java 7.

### Uso de arquivos externos
O programa registra todas as informações em dois arquivos com extensão CSV: *books.csv* e *users.csv*.<br />
O programa também registra dois arquivos TXT auxiliares: *numberOfBooks.txt* e *numberOfUsers.txt*. esses arquivos con´tem, respectivamente, o número de livros e o número de usuários já cadastrados.<br />

### Critério de suspenão de usuário
O usuário é suspenso um dia por cada dia de atraso na devolução do livro.

### Leitura e escrita de arquivos.
Com o intuito de otimizar os acessos a disco, optamos por criar duas listas do tipo *ArrayList* a partir dos arquivos CSV.
As listas *listaDeUsuarios* e *listaDeLivros* são inicializados no início do programa a partir dos arquivos de registros. As alterações nos usuários e nos livros são salvas nos respectivos arquivos ao encerrar o programa, por isso <b>é importante encerrar o programa através do menu (usando a tecla Q).</b>

### Interface e menus
A interação entre o programa e o usuário ocorre pelo terminal e através de um menu simples onde as ações são escolhidas por letras. <br />

## Descrição dos arquivos:<br />
Library.java:Classe encarregada de lidar com os arquivos e com a data.<br />
<br />
Book.java: Classe livro<br /> 
TextBook.java: Contem a classe do livro texto que herda da classe *Book*<br />
GeneralBook: Contem a classe do livro genérico que herda da classe *Book*<br />
<br />
User.java: Classe usuário <br />
AcademicUser.java: Contem a classe do usuário acadêmico que herda da classe *User*.<br />
CommonUser.java: Contem a classe do usuário comum (comunidade) que herda da classe *User*<br />

Log.txt: Contém todos os *logs* dos empréstimos e devoluções.<br />

## Instruções para executar o programa:<br />
- Verifique se a sua versão do Java é compativel.
- Clique duas vezes no arquivo *trabalho.jar*
- Interaja com o programa através do menu.
- Encerre o program clicando Q + Enter

