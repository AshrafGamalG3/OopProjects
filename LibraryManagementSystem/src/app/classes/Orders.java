package app.classes;

import data.ImplementData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders extends ImplementData {

    private String userName ;
    private String bookName ;
    private String bookAuthor ;
    private int price ;
    private String AuthorEmail ;
    private int borrowCopies;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getAuthorEmail() {
        return AuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        AuthorEmail = authorEmail;
    }

    public Orders(String userName, String bookName) {
        this.userName = userName;
        this.bookName = bookName;
    }

    public int getBorrowCopies() {
        return borrowCopies;
    }

    public void setBorrowCopies(int borrowCopies) {
        this.borrowCopies = borrowCopies;
    }

    public boolean makeOrder(String authorEmail){

        try
        {
            String sql = "insert into orders (userName, bookName, authorEmail,price,userEmail ) VALUES (?, ?, ?, ?,?) ";
            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
            if (!getBook()){
                System.out.println("this book not found please try again");
                return false;
            }
            if (getBorrowCopies()<=0){
                System.out.println("Unfortunately, the stock has sold out please try again");
                return false;
            }else {
                preparedStatement.setString(1, getUserName());
                preparedStatement.setString(2, getBookName());
                preparedStatement.setString(3, getAuthorEmail());
                preparedStatement.setInt(4, getPrice());
                preparedStatement.setString(5, authorEmail);
                preparedStatement.executeUpdate();
                System.out.println(" Order  created successfully.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    private boolean getBook(){
        try
        {   String sql = " select * from books where bookUser=? " ;
            if (!isCustomerNumber()){
                return false;
            }
            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
            preparedStatement.setString(1,getBookName());
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                setBookAuthor(resultSet.getString("bookAuthor"));
                setPrice(resultSet.getInt("price"));
                setBorrowCopies(resultSet.getInt("borrowCopies"));
                setAuthorEmail(resultSet.getString("buemail"));
                updateBook();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    private void updateBook(){
    String sql="update books set borrowCopies =borrowCopies-1 where bookUser=? and buemail=? ";
  try {
      PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
      if (getBorrowCopies()>0) {
          preparedStatement.setString(1, getBookName());
          preparedStatement.setString(2, getAuthorEmail());
          preparedStatement.executeUpdate();
      }

  }catch (Exception e){
      System.out.println(e.getMessage());
  }
    }

    private boolean isCustomerNumber( ) {
        String query = "SELECT COUNT(*) FROM books WHERE bookUser = ?";
        try (PreparedStatement preparedStatement = getConnect().prepareStatement(query)) {
            preparedStatement.setString(1, getBookName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);

                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
