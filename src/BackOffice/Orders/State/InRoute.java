package BackOffice.Orders.State;

import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

public class InRoute extends State{
    private static InRoute instance = new InRoute();

    private InRoute(){
        this.setDescStatus("In route to client");
    }


    public static InRoute Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        DataWaiter.GetInstance().ExecuteQuery("Update Orders set status = 6 where order_id = " +order.getOrder_Id() +" ;");
        order.setStatus(Delivered.Instance());
    }
}