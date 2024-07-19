package DataBase;

import BackOffice.CoffeShop.Store;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class CoffeShopWaiter {

    //==========STORE===========
    public List<Store> GetAll()
    {
        List<Store> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData("select store_id, StoreName From Stores");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Store coffepartner = new Store();
                coffepartner.setStore_ID(Integer.parseInt(cols.get(0).toString()));
                coffepartner.setStore_name(cols.get(1).toString());
                list.add(coffepartner);
            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
        return list;
    }

    public List<Store> GetAll(String param)
    {
        List<Store> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData("SELECT Store_ID, StoreName FROM Stores WHERE StoreName like '%"+ param +"%';");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Store coffepartner = new Store();
                coffepartner.setStore_ID(Integer.parseInt(cols.get(0).toString()));
                coffepartner.setStore_name(cols.get(1).toString());
                list.add(coffepartner);
            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
        return list;
    }

    public Store GetOneStore(String searchParam) {
        Store store = new Store();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Store_ID, StoreName FROM Stores WHERE Store_ID = "+ searchParam + ";"); // Fetch store data using the specific method for a single store
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                store.setStore_ID(Integer.parseInt(cols.get(0).toString()));
                store.setStore_name(cols.get(1).toString());
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving a specific store - Waiter");
        }
        return store ;
    }



}
