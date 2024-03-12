package app.classes;

import data.ImplementData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends ImplementData {
    private String bookUser;
    private String bookAuthor;
    private int  price;
    private int borrowCopies;
    private String  buemail;

    private final String type="Admin";

    public Admin(String bookUser, String bookAuthor, int price, int borrowCopies, String buemail) {
        this.bookUser = bookUser;
        this.bookAuthor = bookAuthor;
        this.price = price;
        this.borrowCopies = borrowCopies;
        this.buemail = buemail;
    }

    public Admin() {
    }

    public String getBookUser() {
        return bookUser;
    }

    public void setBookUser(String bookUser) {
        this.bookUser = bookUser;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBorrowCopies() {
        return borrowCopies;
    }

    public void setBorrowCopies(int borrowCopies) {
        this.borrowCopies = borrowCopies;
    }

    public String getBuemail() {
        return buemail;
    }



    @Override
    public String toString() {
        return "{ "  +
                "bookUser='" + bookUser + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", price=" + price +
                ", borrowCopies=" + borrowCopies +
                '}';
    }

    public boolean addBook(){
        String sql = "insert into books (bookUser, bookAuthor, price,borrowCopies,buemail ) VALUES (?, ?, ?, ?,?)";
        try {
            if (!isCustomerNumber(getBookUser())){
                return false;
            }
                try (PreparedStatement preparedStatement = getConnect().prepareStatement(sql)) {
                    preparedStatement.setString(1, getBookUser());
                    preparedStatement.setString(2, getBookAuthor());
                    preparedStatement.setInt(3, getPrice());
                    preparedStatement.setInt(4, getBorrowCopies());
                    preparedStatement.setString(5, getBuemail());
                    preparedStatement.executeUpdate();
                    System.out.println("added successfully.");
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
}

    private boolean isCustomerNumber(String bookName) {
        String query = "SELECT COUNT(*) FROM books WHERE bookUser = ?";
        try (PreparedStatement preparedStatement = getConnect().prepareStatement(query)) {
            preparedStatement.setString(1, bookName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);

                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void viewBooks( String buemail){

        try
        {
            String sql = "select * from books where buemail= ?";

            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
            preparedStatement.setString(1,buemail );
            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                setBookUser(resultSet.getString("bookUser"));
                setBookAuthor( resultSet.getString("bookAuthor"));
                setPrice( resultSet.getInt("price"));
                setBorrowCopies( resultSet.getInt("borrowCopies"));
                System.out.println(++i+" "+toString());


            }
            if (i == 0) {
                System.out.println("No books found for the provided email. Please add books.");
            }
        } catch (SQLException e) {
            System.out.println("please added books");
        }
    }
    public void viewOrders( String authorEmail){

        String sql = "select * from orders where authorEmail= ?";
        try{
            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
            preparedStatement.setString(1,authorEmail );
            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                String userName=resultSet.getString("userName");
                String bookName=resultSet.getString("bookName");
                String userEmail=resultSet.getString("userEmail");
                System.out.println(++i+" - Book Name : "+bookName+", User Name  : "+userName+", User Email: "+userEmail );
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
