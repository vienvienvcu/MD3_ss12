package ra.ss12_usermanagement.model.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/User_data";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public static Connection openConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(Connection connection, CallableStatement callableStatement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
