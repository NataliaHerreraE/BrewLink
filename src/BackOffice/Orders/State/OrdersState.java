package BackOffice.Orders.State;

public enum OrdersState {
    InProgess,
    OrderGenerated, //1	CoffeShop
    Preparing, // 2	CoffeShop
    ReadyforDelivery, // 3	CoffeShop
    PickedUp, // 4	Delivery
    InRoute,	//  5 Delivery
    Delivered // 	Delivery
}
