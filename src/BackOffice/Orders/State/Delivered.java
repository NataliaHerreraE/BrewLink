package BackOffice.Orders.State;

import BackOffice.Orders.Order;

public class Delivered extends State{
    private static Delivered instance = new Delivered();

    private Delivered(){
        this.setDescStatus("Delivered");
    }


    public static Delivered Instance()
    {
        return instance;
    }

    @Override
    public void changeStatus(Order order) {
        System.out.println("This Order is closed");
    }
}
