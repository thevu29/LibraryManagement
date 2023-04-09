package sellBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CSDL {
    private static Connection kn;
    private static String url = "jdbc:mysql://localhost:3306/qlbansua";
    public static Connection getKetNoi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                kn = DriverManager.getConnection(url,"root","huy123");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return kn;
    }

//    public static void main(String[] args) {
//        try {
//            Connection conn = getKetNoi();
//
//            // do something with the connection
//            conn.close(); // remember to close the connection when done
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}
