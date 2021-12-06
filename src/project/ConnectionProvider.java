



package project;
import java.sql.*;
import javax.swing.JOptionPane;


public class ConnectionProvider {
    public static Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_stock2","root","root");
            System.out.println("Connected Successfully");
            return con;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,""+e.toString());
            System.out.println("Error in connection"+e.toString());
            return null;
        }
    }
    
}
