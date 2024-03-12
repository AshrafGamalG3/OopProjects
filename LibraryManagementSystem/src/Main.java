import app.classes.Admin;
import app.classes.NormalUser;
import app.classes.Orders;
import app.classes.User;
import data.ImplementData;
import data.MyData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
public static Scanner scanner=new Scanner(System.in);
//    static MyData myData = new ImplementData();
//    static Connection connection = myData.getConnect();
    public static String email=null;

    public static void main(String[] args) {
homePage();
    }

    public static void homePage(){
        System.out.println("1 - Login ");
        System.out.println("2 - New User ");
        System.out.println("3 - Exit ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            userOption(choice);
        } else {
            System.out.print("Please choose a number from 1 - 3: ");
            scanner.nextLine();
            homePage();
        }

    }
    private static void userOption(int c){
        switch (c){
            case 1:
                loginPage();
                break;
            case 2:
                newUserPageAORN();
            break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                userPage();
                break;
        }
    }
    private static void loginPage(){
        System.out.print("Enter Your Email : ");
        String gmail=scanner.nextLine();
        System.out.print("Enter Password : ");
        String pass=scanner.nextLine();
        User user=new User(gmail,pass);
        email=gmail;
        if (user.getUser()){
            if (user.getType().equals("Admin"))
            {
                adminPage();
            }
          else if (user.getType().equals("User")){
                userPage();
           }

        }else {
            loginPage();
        }
    }
    private static void newUserPageAORN(){
        System.out.println("1 - Admin ");
        System.out.println("2 - NormalUser ");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            newUserPage(choice);
        } else {
            System.out.print("Please choose a number from 1 - 2: ");
            scanner.nextLine();
            homePage();
        }

    }
    private static void newUserPage(int c){
        System.out.print("Enter Your Name : ");
        String name=scanner.nextLine();
        System.out.print("Enter Your Phone Number : ");
        String phoneNumber=scanner.nextLine();
        System.out.print("Enter email : ");
        String gmail=scanner.nextLine();
        System.out.print("Enter Password : ");
        String pass=scanner.nextLine();
        email=gmail;
        User user;
        if (isValidUser(name,gmail,phoneNumber,pass)){
            switch (c){
                case 1:
                    user=  new User(name,gmail,phoneNumber,pass,"Admin");
                    isValidUser(user.insertUser(),c, user.getType());
                    break;
                case 2:
                    user=  new User(name,gmail,phoneNumber,pass,"User");
                    isValidUser(user.insertUser(),c,user.getType());
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    newUserPage(c);
                    break;
            }
        }else {
            System.out.println("Your Data not valid please try again gmail contains @gmail.com && name,pass > 4 ,phone > 4 digit");
            newUserPage(c);
        }

    }
private static boolean isValidUser(String name,String gmail,String phone,String pass){
    return name.length() > 4 && gmail.contains("@gmail.com") && phone.length() > 4 && pass.length() > 4;
}
private static void isValidUser(boolean valid,int c,String type){
    if (valid){
        if (type.equals("Admin"))
        {
            adminPage();
        }
        else if (type.equals("User")){
            userPage();
        }
    }else {
        System.out.println("User already exists. Please try again .");
        newUserPage(c);
    }
}

    private static void adminPage (){
        System.out.println("1 - View Books ");
        System.out.println("2 - Add Book ");
        System.out.println("3 - View Orders ");
        System.out.println("4 - Exit ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
           adminOptions(choice);
        } else {
            System.out.print("Please choose a number from 1 - 4: ");
            scanner.nextLine();
            homePage();
        }

    }
    private static void adminOptions (int c){
        switch (c){
            case 1:
                viewBooks();
                break;
            case 2:
            addBook();
                break;
            case 3:
        viewOrders();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                adminOptions(c);
                break;
        }

    }
    private static void viewBooks(){
        new Admin().viewBooks(email);
        adminPage();
    }
    private static void viewOrders(){
        new Admin().viewOrders(email);
    }
    private static void addBook(){
        System.out.println("Enter book name");
        String bookName=scanner.nextLine();
        System.out.println("Enter author name");
        String bookAuthor=scanner.nextLine();
        System.out.println("Enter number of borrowing");
        int bookBorrowing=scanner.nextInt();
        System.out.println("Enter book price");
        int bookPrice=scanner.nextInt();
        Admin admin=new Admin(bookName,bookAuthor,bookPrice,bookBorrowing,email);
        if (admin.addBook()){
            adminPage();
        }else {
            System.out.println("book not valid please try again");
            addBook();
        }
    }
     private static void userPage (){
        System.out.println("1 - View Books ");
        System.out.println("2 - Place Order ");
         System.out.println("3 - My Books ");
        System.out.println("4 - Exit ");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            userOptions(choice);
        } else {
            System.out.print("Please choose a number from 1 - 3: ");
            scanner.nextLine();
            userPage();
        }

    }
    private static void userOptions (int c){
        switch (c){
            case 1:
                viewBooksToUser();
                break;
            case 2:
                placeOrder();
                break;
            case 3:
                viewBooksOwned();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                adminOptions(c);
                break;
        }

    }
    private static void viewBooksToUser(){
        new NormalUser().viewBooks();
        userPage();
    }
    private static void viewBooksOwned(){
        new NormalUser().viewMyBooks(email);
        userPage();
    }
    private static void placeOrder(){
        System.out.print("Enter Your Name : ");
        String userName=scanner.nextLine();
        System.out.print("Enter book Name : ");
        String bookName=scanner.nextLine();
        Orders orders=new Orders(userName,bookName);
        if (orders.makeOrder(email)){
            userPage();
        }else {
            placeOrder();
        }
    }

}