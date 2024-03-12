package app.classes;

import data.ImplementData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NormalUser extends ImplementData {


    public void viewBooks(){

        try
        {
            String sql = "select * from books ";
            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                String bookUser=resultSet.getString("bookUser");
                String bookAuthor=resultSet.getString("bookAuthor");
                int price=resultSet.getInt("price");
                int borrowCopies= resultSet.getInt("borrowCopies");
                System.out.println(++i+" - Book Name : "+bookUser+", Book Author : "+bookAuthor+", Book Price : "+price+", Borrow Copies : "+borrowCopies);
            }
            if (i == 0) {
                System.out.println("No books found .");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewMyBooks(String userEmail){

        try
        {
            String sql = "select * from orders where userEmail = ?  ";
            PreparedStatement preparedStatement = getConnect().prepareStatement(sql);
            preparedStatement.setString(1,userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()) {
                String bookName=resultSet.getString("bookName");
                int price=resultSet.getInt("price");
                System.out.println(++i+" - Book Name : "+bookName+", Book Price : "+price);
            }
            System.out.println();
            if (i == 0) {
                System.out.println("No Books Found Please buy some books  .");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
