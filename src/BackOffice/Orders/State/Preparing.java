package BackOffice.Orders.State;

import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

public class Preparing extends State{
    private static Preparing instance = new Preparing();

    private Preparing(){
        this.setDescStatus("Preparing");
    }


    public static Preparing Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        DataWaiter.GetInstance().ExecuteQuery("Update Orders set status = 3 where order_id = " +order.getOrder_Id() +" ;");
        order.setStatus(ReadyForDelivery.Instance());
    }
}