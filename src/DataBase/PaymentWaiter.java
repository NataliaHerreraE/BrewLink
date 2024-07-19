package DataBase;

import BackOffice.Management.Payments;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class PaymentWaiter {

    public List<Payments> GetAll()
    {
        List<Payments> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try
        {
            results = DataWaiter.GetInstance().GetData("select Payment_id, PayName, Active From Payments where active = 1;");
            for (int i =0; i< results.size(); i++)
            {
                List<Object> cols = new ArrayList<>();
                cols = (List<Object>) results.get(i);
                Payments payments = new Payments();
                payments.setPaymentId(Integer.parseInt(cols.get(0).toString()));
                payments.setPayName(cols.get(1).toString());
                payments.setActive((Boolean.parseBoolean(cols.get(2).toString())));
                list.add(payments);
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to get all the stores - Waitres");
        }
        return list;
    }

    public Payments GetOne(int paymentid) {
        Payments payments = new Payments();;
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("select Payment_id, PayName, Active From Payments where active = 1 and Payment_id=" + paymentid + ";");
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                payments.setPaymentId(Integer.parseInt(cols.get(0).toString()));
                payments.setPayName(cols.get(1).toString());
                payments.setActive((Boolean.parseBoolean(cols.get(2).toString())));
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving a specific Payment - Waiter");
        }
        return payments;
    }

}
