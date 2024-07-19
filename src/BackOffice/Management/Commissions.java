package BackOffice.Management;

import DataBase.CoffeShopWaiter;
import DataBase.Singleton.DataWaiter;
import DataBase.DeliveryWaiter;

import java.util.ArrayList;
import java.util.List;

public  class Commissions {

    public static void DisplaySummary()
    {
        try
        {
            System.out.println("Total Commissions (Stores + Deliveries): " + GetSummary("1"));
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Total Stores Commissions: "+ GetSummary("2"));
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Total Commissions by Store: ");
            GetDetailedSummary("2");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Total Deliveries Commissions: "+ GetSummary("3"));
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Total Commissions by Delivery:");
            GetDetailedSummary("3");
            System.out.println("-------------------------------------------------------------------");

        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to display de summary");
        }
    }

    public static String GetSummary(String type)
    {
        String commission="0";
        String query = switch (type) {
            case "1" -> "select (sum(BrewComProducts) + sum(BrewComDelivery)) TotalCommissions from Orders;";
            case "2" -> "select sum(BrewComProducts) ProductsCommission from orders;";
            case "3" -> "select sum(BrewComDelivery) DeliveryCommission from orders;";
            default -> "";
        };
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData(query);
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                commission =cols.get(0).toString();
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
        return commission;
    }


    public static void GetDetailedSummary(String type)
    {
        String query = switch (type) {
            case "2" -> "select Store_ID, sum(BrewComProducts) ProductsCommission from orders group by Store_ID;";
            case "3" -> "select Delivery_ID, sum(BrewComDelivery) DeliveryCommision from orders group by Delivery_ID;";
            default -> "";
        };
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData(query);
            if (results.isEmpty())
                System.out.println("There are not commissions to display");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);

                if (type == "2") // Tienda
                    System.out.println("Store: " + new CoffeShopWaiter().GetOneStore(cols.get(0).toString()).getStore_name() + ", Earned commission: " + cols.get(1).toString());
                else if(type =="3")
                    System.out.println("Delivery: " + new DeliveryWaiter().GetOne(Integer.parseInt(cols.get(0).toString())).getDelname() + ", Earned commission: " + cols.get(1).toString());
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
    }


}
