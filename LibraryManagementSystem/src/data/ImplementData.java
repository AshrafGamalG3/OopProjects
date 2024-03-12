package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ImplementData implements MyData{
    Connection connection=null;
    @Override
    public Connection getConnect() {
        if (connection == null) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
            return connection;
        }
        return connection;
    }
}
