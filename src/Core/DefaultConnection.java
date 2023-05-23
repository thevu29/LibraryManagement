package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.RecursiveTask;

public abstract class DefaultConnection {
    private String url = "jdbc:mysql://localhost:3306";
    private String user = "root";
    private String pass = "";

    public DefaultConnection() {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT", user, pass);
    }

    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT", "root", "");
    }
}
