package BackOffice.Orders.State;

import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

public class ReadyForDelivery extends State{
    private static ReadyForDelivery instance = new ReadyForDelivery();

    private ReadyForDelivery(){
        this.setDescStatus("Ready for delivery");
    }


    public static ReadyForDelivery Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        DataWaiter.GetInstance().ExecuteQuery("Update Orders set status = 4 where order_id = " +order.getOrder_Id() +" ;");
        order.setStatus(PickedUp.Instance());
    }
}