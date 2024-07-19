package DataBase;

import BackOffice.Customers.Clients;
import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsWaiter {

    public Clients GetOneByID(String id) {
        List<Object> results = new ArrayList<>();
        Clients client = new Clients();

        try
        {
            String query="select Cliente_ID,email,Password,FirstName, MiddleName,LastName, phone from client where Cliente_ID =" + id + ";";
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>)results.get(i);

                client.setClientId(Integer.parseInt(cols.get(0).toString()));
                client.setEmail(cols.get(1).toString());
                client.setPassword(cols.get(2).toString());
                client.setFirstName(cols.get(3).toString());
                client.setMiddleName(cols.get(4).toString());
                client.setLastName(cols.get(5).toString());
                client.setPhone(cols.get(6).toString());
            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitress");
        }

        return client;
    }



    public List<Clients> GetAll()
    {
        List<Clients> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData("select Cliente_ID,email,Password,FirstName, MiddleName,LastName, phone from client;");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Clients client = new Clients();
                client.setClientId(Integer.parseInt(cols.get(0).toString()));
                client.setEmail(cols.get(1).toString());
                client.setPassword(cols.get(2).toString());
                client.setFirstName(cols.get(3).toString());
                client.setMiddleName(cols.get(4).toString());
                client.setLastName(cols.get(5).toString());
                client.setPhone(cols.get(6).toString());
                list.add(client);
            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitress");
        }
        return list;
    }


    public void DisplayCLient(){

        for (Clients client:GetAll()
        ) {
            System.out.println("Client id"+client.getClientId());
            System.out.println("Client email"+client.getEmail());
            System.out.println("Client first name"+client.getFirstName());
            System.out.println("Client Middle Name"+client.getMiddleName());
            System.out.println("Client Last Name"+client.getLastName());
            System.out.println("Client phone"+client.getPhone());


        }



    }
    public void DisplayClientById(String id){


        System.out.println("Client id"+GetOneByID(id).getClientId());
        System.out.println("Client email"+GetOneByID(id).getEmail());
        System.out.println("Client first name"+GetOneByID(id).getFirstName());
        System.out.println("Client Middle Name"+GetOneByID(id).getMiddleName());
        System.out.println("Client Last Name"+GetOneByID(id).getLastName());
        System.out.println("Client phone"+GetOneByID(id).getPhone());
    }

    public static void InsertClient(Clients clients) throws SQLException {

        try{
        String sql = "insert into Client (email,Password,FirstName,MiddleName,LastName,Phone)values("
                + "'" + clients.getEmail() + "',"
                + "'" + clients.getPassword() + "',"
                + "'" + clients.getFirstName() + "',"
                + "'" + clients.getMiddleName() + "',"
                + "'" + clients.getLastName() + "',"
                + "'" + clients.getPhone() + "');";

        DataWaiter.GetInstance().ExecuteQuery(sql);

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
  /*
    public void DeleteClientById(int id) {
        boolean success = false;
        String query = "delete from clients where client_id=" + id + " ";

        success = DataWaiter. ExecuteQuery(query);

        if (success) {

            System.out.println("Client deleted successfully...");
        }

    }
*/

    public static String getUserPaswd(String id){
        List<Object> results = new ArrayList<>();
        Clients client = new Clients();
        try
        {
            String query="select Cliente_ID,email,Password,FirstName, MiddleName,LastName, phone from client where email ='" + id + "';";
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);

                client.setClientId(Integer.parseInt(cols.get(0).toString()));
                client.setEmail(cols.get(1).toString());
                client.setPassword(cols.get(2).toString());
                client.setFirstName(cols.get(3).toString());
                client.setMiddleName(cols.get(4).toString());
                client.setLastName(cols.get(5).toString());
                client.setPhone(cols.get(6).toString());
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitress");
        }

        return client.getPassword();
    }
    public static int getUserIdFromClient(String id){
        List<Object> results = new ArrayList<>();
        Clients client = new Clients();
        try
        {
            String query="select Cliente_ID,email,Password,FirstName, MiddleName,LastName, phone from client where email ='" + id + "';";
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);

                client.setClientId(Integer.parseInt(cols.get(0).toString()));
                client.setEmail(cols.get(1).toString());
                client.setPassword(cols.get(2).toString());
                client.setFirstName(cols.get(3).toString());
                client.setMiddleName(cols.get(4).toString());
                client.setLastName(cols.get(5).toString());
                client.setPhone(cols.get(6).toString());
            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitress");
        }



        return client.getClientId();
    }

}
