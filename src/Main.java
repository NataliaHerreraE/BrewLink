import BackOffice.Customers.Address;
import BackOffice.Management.Payments;
import BackOffice.Orders.Order;
import DataBase.AddressWaiter;
import DataBase.OrderDetailWaiter;
import DataBase.OrdersWaiter;
import FrontOffice.CoffeMenu;
import FrontOffice.Menu;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*/Prueba Jose
        OrderDetailWaiter orderDetail = new OrderDetailWaiter();
        orderDetail.DisplayOrdersDetailsById(1);
        orderDetail.DisplayOrdersDetailsWithProducts();
        //Prueba Natty
        System.out.println("PRUEBA MENU PRINCIPAL");*/


        Menu menu = new Menu();
        menu.MainMenu(); // Llama al método CoffeeShopSwitch para manejar las opciones del menú


        System.out.println("Saliendo del programa...");
    }
}
