package BackOffice.Orders.State;

import BackOffice.Orders.Order;

import java.util.List;

public abstract class State {
    private OrdersState currentState;

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    private String descStatus;
    public OrdersState getCurrentState() {
        return currentState;
    }

    public abstract void changeStatus(Order order);
}
