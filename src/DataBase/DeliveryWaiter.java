package DataBase;

import BackOffice.DeliveryPartners.Delivery;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class DeliveryWaiter {
    public List<Delivery> GetAll()
    {
        List<Delivery> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData("select Delivery_ID,DelName,Comission From deliveries;");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Delivery delivery = new Delivery();
                delivery.setDeliveryId(Integer.parseInt(cols.get(0).toString()));
                delivery.setDelname(cols.get(1).toString());
                delivery.setComission((float)Double.parseDouble(cols.get(2).toString()));
                list.add(delivery);
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
        return list;
    }

    public Delivery GetOne(int deliveryId) {
        Delivery delivery = new Delivery();;
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("select Delivery_ID,DelName,Comission From deliveries where Delivery_id = "+ deliveryId +";");
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                delivery.setDeliveryId(Integer.parseInt(cols.get(0).toString()));
                delivery.setDelname(cols.get(1).toString());
                delivery.setComission((float)Double.parseDouble(cols.get(2).toString()));
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving a specific Delivery - Waiter");
        }
        return delivery;
    }
}
