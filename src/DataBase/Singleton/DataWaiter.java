package DataBase.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
public class DataWaiter {

    private static final String url ="jdbc:sqlite:C:\\BrewLinkDB\\BrewLink.db";
    private static DataWaiter instance  = new DataWaiter();
    private DataWaiter(){}

    public static DataWaiter GetInstance() {
        return instance;
    }


    //insert delete updates
    public boolean ExecuteQuery(String query)
    {
        /*System.out.println("----- ENTRÓ A EXECUTE QUERY -----");
        System.out.println(query);
        System.out.println("---------------------------------");*/
        // for insert, update, delete
        boolean success = false;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.execute(query);
            statement.close();
            conn.close();
            success=true;

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return success;

    }

     public List<Object> GetData(String query) {
        /*System.out.println("----- ENTRÓ A GET DATA -----");
        System.out.println(query);
        System.out.println("---------------------------------");*/
        List<Object> rows=new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query);
            List<Object> columns;
            //lista de objetos va a contener lso n renglones que se obtengan de la BDs
            while (results.next()) {
                columns = new ArrayList<>();
                ResultSetMetaData metaData = results.getMetaData();
                int columnCount = metaData.getColumnCount();
                // Iterate through columns to get their values
                for (int i = 1; i <= columnCount; i++) {
                    //String columnName = metaData.getColumnName(i);
                    Object columnValue = results.getObject(i);
                    columns.add(results.getObject(i));
                }
                rows.add(columns);
            }
            results.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return rows;
    }

}
