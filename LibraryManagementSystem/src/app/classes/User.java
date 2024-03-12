package app.classes;

import data.ImplementData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class User extends ImplementData {
    private String nameUser;
    private String emailUser;
    private String phoneUser;
    private String passUser;
    private String type;



    public User(String nameUser, String emailUser, String phoneUser, String passUser, String type) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneUser = phoneUser;
        this.passUser = passUser;
        this.type = type;


    }

    public User(String emailUser, String passUser) {
        this.emailUser = emailUser;
        this.passUser = passUser;
    }

    public User() {
    }



    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean insertUser() {
        String sql = "insert into users (nameUser, emailUser, phoneUser,passUser,userType ) VALUES (?, ?, ?, ?,?)";
        try {
            if (isCustomerNumber(getConnect())) {
                return false;
            } else {
                try (PreparedStatement preparedStatement = getConnect().prepareStatement(sql)) {
                    preparedStatement.setString(1, getNameUser());
                    preparedStatement.setString(2, getEmailUser());
                    preparedStatement.setString(3, getPhoneUser());
                    preparedStatement.setString(4, getPassUser());
                    preparedStatement.setString(5, getType());
                    preparedStatement.executeUpdate();
                    System.out.println("created successfully.");
                    return true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private boolean isCustomerNumber(Connection connection) {
        String query = "SELECT COUNT(*) FROM users WHERE emailUser = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, getEmailUser());
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

    public boolean getUser() {
        try {
            if (!isCustomerNumber(getConnect())){
                System.out.println("Email is not valid, please try again");
                return false;
            }
            String selectSQL = "SELECT * FROM users WHERE emailUser = ?";
            PreparedStatement preparedStatement = getConnect().prepareStatement(selectSQL);
            preparedStatement.setString(1, getEmailUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setNameUser(resultSet.getString("nameUser"));
                setPhoneUser( resultSet.getString("phoneUser"));
                setType( resultSet.getString("userType"));
                String pass= resultSet.getString("passUser");
                if (pass.equals(getPassUser())){
                    System.out.println("login successfully");
                    return true;
                }else {
                    System.out.println("your password is not valid");
                    return false;
                }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
