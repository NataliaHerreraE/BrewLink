package BackOffice.CoffeShop;

import DataBase.CoffeShopWaiter;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private int store_ID;
    private String store_name;

    public int getStore_ID() {
        return store_ID;
    }

    public void setStore_ID(int store_ID) {
        this.store_ID = store_ID;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }


    public List<Store> GetAll() {
        List<Store> list = new ArrayList<>();
        try {
            CoffeShopWaiter waiter = new CoffeShopWaiter() ;
            list = waiter.GetAll();
        } catch(Exception ex) {
            System.out.println("Error at the moment to get all the stores");
        }
        return list;
    }

    public List<Store> GetAll(String param) {
        List<Store> list = new ArrayList<>();
        try {
            CoffeShopWaiter waiter = new CoffeShopWaiter() ;
            list = waiter.GetAll(param);
        } catch(Exception ex) {
            System.out.println("Error at the moment to get all the stores");
        }
        return list;
    }

    public Store GetOneStore(String param) {
        Store store = new Store();
        try {
            CoffeShopWaiter waiter = new CoffeShopWaiter() ;
            store = waiter.GetOneStore(param);
        } catch(Exception ex) {
            System.out.println("Error at the moment to get all the stores");
        }
        return store;
    }


    public void DisplayAll()
    {
        try {
            List<Store> list = GetAll();
            for (Store coffepartner:list) {
                System.out.println("Store ID: " + coffepartner.getStore_ID() + " Store name:" + coffepartner.getStore_name());

            }
        } catch(Exception ex) {
            System.out.println("Error at the moment to display all the stores");
        }
    }



}
