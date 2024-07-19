package DataBase;

import BackOffice.Customers.Address;
import DataBase.Singleton.DataWaiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressWaiter {

    public static void InsertAddress(Address address) throws SQLException {

        try  {
            String sql = "insert into Address (Cliente_Id,Address,City,Province,'Postal Code',AddressName,IsDefault)values("
                    + address.getClientId() + ","
                    + "'" + address.getAddress() + "',"
                    + "'" + address.getCity() + "',"
                    + "'" + address.getProvince() + "',"
                    + "'" + address.getPostalCode() + "',"
                    + "'" + address.getAddressName() + "',"
                    + address.isDefaultAddress() + ");";

            DataWaiter.GetInstance().ExecuteQuery(sql);

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    public static Address GetOneByID(String id) {
        List<Object> results = new ArrayList<>();
        Address address = new Address();
        try
        {
            // modificar el query con el case porque parseboolean no parsea de 1 y 0 a true y false...
            String query="select Cliente_ID,Address_Id,Address,Province,[Postal Code],CASE IsDefault WHEN 1 THEN 'true' ELSE 'false' END as IsDefault,AddressName, City From address where Address_id =" + id + ";";
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);

                address.setClientId(Integer.parseInt(cols.get(0).toString()));
                address.setAddressId(Integer.parseInt(cols.get(1).toString()));
                address.setAddress(cols.get(2).toString());
                address.setProvince(cols.get(3).toString());
                address.setPostalCode(cols.get(4).toString());
                address.setDefaultAddress(Boolean.parseBoolean(cols.get(5).toString()));
                address.setAddressName(cols.get(6).toString());
                address.setCity(cols.get(7).toString());
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get the Address - Waitress");
        }
        return address;
    }

    public static void DeleteAddressByID(String id) {
        try
        {
            String query="delete from address where Address_id =" + id + ";";
            DataWaiter.GetInstance().ExecuteQuery(query);

        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get the Address - Waitress");
        }
    }

    public static void updateDefaultAddressByID(String id,boolean defaultAd) {
        try
        {
            String query="update address set IsDefault="+defaultAd+" where Address_id =" + id + ";";
            DataWaiter.GetInstance().ExecuteQuery(query);
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to update the default Address");
        }
    }

    public static List<Address> GetAll(String clientid)
    {
        List<Address> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            // modificar el query con el case porque parseboolean no parsea de 1 y 0 a true y false...
            String query="select Cliente_ID,Address_Id,Address,Province,[Postal Code],CASE IsDefault WHEN 1 THEN 'true' ELSE 'false' END as IsDefault,AddressName, city From address where cliente_id =" + clientid + ";";
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Address address = new Address();
                address.setClientId(Integer.parseInt(cols.get(0).toString()));
                address.setAddressId(Integer.parseInt(cols.get(1).toString()));
                address.setAddress(cols.get(2).toString());
                address.setProvince(cols.get(3).toString());
                address.setPostalCode(cols.get(4).toString());
                address.setDefaultAddress(Boolean.parseBoolean(cols.get(5).toString()));
                address.setAddressName(cols.get(6).toString());
                address.setCity(cols.get(7).toString());
                list.add(address);
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the addresses by client - Waitress");
        }
        return list;
    }
    public static void DisplayAddress(String id) {

        for (Address address : GetAll(id)
        ) {
            System.out.println("Client id: " + address.getClientId());
            System.out.println("Address id: " + address.getAddressId());
            System.out.println("Address: " + address.getAddress());
            System.out.println("City: " + address.getCity());
            System.out.println("Province: " + address.getProvince());
            System.out.println("Postal code: " + address.getPostalCode());
            System.out.println("Default Address: " + address.isDefaultAddress());
            System.out.println("Address Name: " + address.getAddressName());
        }
    }

    public static void DisplayClientById(String id){



        System.out.println("Client id"+GetOneByID(id).getClientId());
        System.out.println("Address id: " + GetOneByID(id).getAddressId());
        System.out.println("Address: " + GetOneByID(id).getAddress());
        System.out.println("City: " + GetOneByID(id).getCity());
        System.out.println("Province: " + GetOneByID(id).getProvince());
        System.out.println("Postal code: " + GetOneByID(id).getPostalCode());
        System.out.println("Default Address: " + GetOneByID(id).isDefaultAddress());
        System.out.println("Address Name: " + GetOneByID(id).getAddressName());
    }

    public static Address GetAddressObject(String id) {
        Address addressObj = new Address();

        for (Address address : GetAll(id)
        ) {
           addressObj.setClientId(address.getClientId());
            addressObj.setAddress(address.getAddress());
            addressObj.setAddressId(address.getAddressId());
            addressObj.setProvince(address.getProvince());
            addressObj.setPostalCode(address.getPostalCode());
            addressObj.setDefaultAddress(Boolean.parseBoolean(address.getAddress()));
            addressObj.setAddressName(address.getAddressName());
            addressObj.setCity(address.getCity());
        }


        return addressObj;
    }
}
