package FrontOffice;

import BackOffice.CoffeShop.Store;
import BackOffice.DeliveryPartners.Delivery;
import BackOffice.Orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryMenu {



    public void DisplayMenu()
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String option;
        do
        {
            System.out.println("=======================================================================================================================");
            System.out.println("============================================== DELIVERIES MENU ========================================================");
            System.out.println("======================== Select your store in order to prepare and deliver your orders ================================");
            System.out.println("=======================================================================================================================");
            new Delivery().DisplayAll();
            System.out.println("(0 for Exit)                                                                                                          =");
            System.out.println("=======================================================================================================================");
            System.out.print("Option: ");
            option = myObj.nextLine();  // Read user input

            switch (option) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    DisplayMenuByDelivery(option);
                    break;
                case "0":
                    break;
                default:
                    // code block
                    System.out.println("--------------------------------------------\n\nInvalid option selected \nValid options are 1 to 7...\n\n--------------------------------------------\n");
                    break;
            }
        }while(!(option.equals("0")));
    }


    public void DisplayMenuByDelivery(String DeliveryID)
    {
        Delivery delivery = new Delivery().GetOne(Integer.parseInt(DeliveryID));
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String option;
        do
        {
            System.out.println("=======================================================================================================================");
            System.out.println("======================================== MENU " + delivery.getDelname() + " ==============================================================");
            System.out.println("==================================== Select your preferred action =====================================================");
            System.out.println("=======================================================================================================================");
            System.out.println("=1 or 2.- See your new orders assigned                                                                                =");
            System.out.println("=3.- See your orders ready to deliver                                                                                 =");
            System.out.println("=4.- See your order ready to start route                                                                              =");
            System.out.println("=5.- Orders ready to mark as delivered                                                                                =");
            System.out.println("=6.- See your orders completed                                                                                        =");
            System.out.println("(0 for Exit)                                                                                                          =");
            System.out.println("=======================================================================================================================");
            System.out.print("Option: ");
            option = myObj.nextLine();  // Read user input
            Order order =new Order();
            List<Order> orders=new ArrayList<>();
            switch (option) {
                case "1":
                    orders = order.GetOrders("Delivery","2","",DeliveryID );
                    if (!orders.isEmpty()) {
                        ShowOrderHistorical(orders, "2", delivery);
                    }
                    else
                        System.out.println("There are not orders to Display");
                    break;
                case "3":
                case "4":
                case "5":
                    orders = order.GetOrders("Delivery",option,"",DeliveryID );
                    if (!orders.isEmpty()) {
                        ShowOrderDetail(orders, DeliveryID, option, delivery);
                    }
                    else
                        System.out.println("There are not orders to Display");
                    break;
                case "6":
                    orders = order.GetOrders("Delivery",option,"",DeliveryID );
                    if (!orders.isEmpty()) {
                        ShowOrderHistorical(orders, option, delivery);
                        //orders = order.GetOrders("Coffee", option, StoreID, "");
                    }
                    else
                        System.out.println("There are not orders to Display");
                    break;
                default:
                    // code block
                    System.out.println("--------------------------------------------\n\nInvalid option selected \nValid options are 1 to 7...\n\n--------------------------------------------\n");
                    break;
            }
        }while(!(option.equals("0")));
    }

    private void ShowOrderDetail(List<Order> orders, String DeliveryID,String Status, Delivery delivery)
    {
        try {
            Order order = new Order();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String option;
            do
            {
                System.out.println("=======================================================================================================================");
                System.out.println("============================================== MAIN " + delivery.getDelname() + " ==============================================================");
                System.out.println("============================= Select the order that you want to advance ===============================================");
                System.out.println("=======================================================================================================================\n");
                order.DisplayOrders(orders);
                System.out.println("(0 for Exit)                                                                                                             =");
                System.out.println("=======================================================================================================================");
                System.out.print("Option: ");
                option = myObj.nextLine();  // Read user input

                Integer intOption =-1;
                //validamos que la entrada sea numérica
                try {
                    intOption =Integer.parseInt(option);
                } catch (NumberFormatException e) {
                    System.out.println("Please type a valid ORDER ID number");
                }
                //Validate if the data exists and then perform the change of the status
                if (intOption > 0 )
                {
                    boolean exists =false;
                    for(Order orderaux: orders)
                    {
                        if (orderaux.getOrder_Id()== intOption)
                        {
                            orderaux.getStatus().changeStatus(orderaux);
                            System.out.println("Status changed to:" + orderaux.getStatus().getDescStatus());
                            exists=true;
                            orders.remove(orderaux);
                            if (orders.isEmpty())
                                option="0";
                            break;
                        }
                    }
                    if (!exists)
                        System.out.println("ORDER ID not found");
                }


            }while(!(option.equals("0")));
        }
        catch (Exception ex)
        {

        }
        // ask to type the order they wanna update the
        // validate if the order has the StoreID and Status with a count
        //if yes then
    }

    private void ShowOrderHistorical(List<Order> orders,String Status, Delivery delivery)
    {
        try {
            Order order = new Order();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String option;
            do
            {
                System.out.println("=======================================================================================================================");
                System.out.println("============================================== MAIN " + delivery.getDelname() + " ==============================================================");
                System.out.println("========================================= Track your  orders ==========================================================");
                System.out.println("=======================================================================================================================\n");
                order.DisplayOrders(orders);
                System.out.println("(0 for Exit)                                                                                                             =");
                System.out.println("=======================================================================================================================");
                System.out.print("Option: ");
                option = myObj.nextLine();  // Read user input

                if (!option.equals("0"))
                    System.out.println("Type a valid option");
            }while(!(option.equals("0")));
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to track the Store Orders");
        }
        // ask to type the order they wanna update the
        // validate if the order has the StoreID and Status with a count
        //if yes then
    }



}
