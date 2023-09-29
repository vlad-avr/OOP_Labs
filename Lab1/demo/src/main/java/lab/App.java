package lab;

// import lab.cotroller.Controller;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App 
{

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:D:/Java/OOP_Labs/Lab1/demo/src/main/java/lab/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS example (\n" 
                    + "     id integer PRIMARY KEY, \n"
                    + "     name text NOT NULL, \n"
                    + "     capacity real\n"
                    + ");";

        try(Connection con = DriverManager.getConnection(url);
         Statement st = con.createStatement();){
            st.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main( String[] args )
    {
        //createNewDatabase("test.db");
        createNewTable();
        // System.out.println( "Hello World!" );
        // Controller controller = new Controller();
        // controller.start();
    }
}
