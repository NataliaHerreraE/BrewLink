package BackOffice.Orders.State;

import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

public class PickedUp extends State{
    private static PickedUp instance = new PickedUp();

    private PickedUp(){
        this.setDescStatus("Picked up by delivery");
    }


    public static PickedUp Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        DataWaiter.GetInstance().ExecuteQuery("Update Orders set status = 5 where order_id = " +order.getOrder_Id() +" ;");
        order.setStatus(InRoute.Instance());
    }
}