package BackOffice.Orders.State;

import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

public  class OrderGenerated extends State{
    private static OrderGenerated instance = new OrderGenerated();

    private OrderGenerated(){
        this.setDescStatus("Order Generated");
    }


    public static OrderGenerated Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        DataWaiter.GetInstance().ExecuteQuery("Update Orders set status = 2 where order_id = " +order.getOrder_Id() +" ;");
        order.setStatus(Preparing.Instance());
    }
}
