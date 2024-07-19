package DataBase;

import BackOffice.Orders.State.*;
import BackOffice.Orders.Order;
import DataBase.Singleton.DataWaiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class OrdersWaiter {

    public static void InsertOrders(Order orders) throws SQLException {


        String sql = "insert into Orders (Client_ID,Address_ID," +
                "Store_ID,Delivery_ID," +
                "Payment_ID,TotalProducts," +
                "BrewComProducts,DeliveryCost," +
                "BrewComDelivery," +
                "TotalOrder," +
                "Status," +
                "Date)values(?,?,?,?,?,?,?,?,?,?,?,?)  ";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BrewLinkDB\\testproject.db"); PreparedStatement pstmt = conn.prepareStatement(sql);) {


            pstmt.setInt(1, orders.getClient().getClientId());
            pstmt.setInt(2, orders.getAddress().getAddressId());
            pstmt.setInt(3, orders.getStore().getStore_ID());
            pstmt.setInt(4, orders.getDelivery().getDeliveryId());
            pstmt.setInt(5, orders.getPayments().getPaymentId());
            pstmt.setDouble(6, orders.getTotalProducts());
            pstmt.setDouble(7, orders.getBrewComProducts());
            pstmt.setDouble(8, orders.getDeliveryCost());
            pstmt.setDouble(9, orders.getBrewComDelivery());
            pstmt.setDouble(10, orders.getTotalOrder());
            pstmt.setString(11, orders.getStatus().getCurrentState().toString()); // JA changed
            pstmt.setObject(12, orders.getDate());


            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }


    public List<Order> GetOrders() {

        String sql = "select Order_ID," +
                "Client_ID," +
                "Address_ID," +
                "Store_ID," +
                "Delivery_ID," +
                "Payment_ID," +
                "TotalProducts," +
                "BrewComProducts," +
                "DeliveryCost," +
                "BrewComDelivery," +
                "TotalOrder," +
                "Status," +
                "Date " +
                " from Orders";

        List<Object> result = DataWaiter.GetInstance().GetData(sql);
        List<Order> orderList = new ArrayList<>();
        List<Object> cols = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            try {

                Order order = new Order();

                cols = (List<Object>) result.get(i);

                order.setOrder_Id(Integer.parseInt(cols.get(0).toString()));
                /*order.setClient(Integer.parseInt(cols.get(1).toString())); //Process to get client
                order.setAddress(Integer.parseInt(cols.get(2).toString())); //Process to get Address
                order.setStore(Integer.parseInt(cols.get(3).toString())); // PRocess to get Store
                order.setDelivery(Integer.parseInt(cols.get(4).toString())); // PRocess to get Delivery
                order.setPayments(Integer.parseInt(cols.get(5).toString())); // Process to get Payment*/
                order.setClient(new ClientsWaiter().GetOneByID(cols.get(1).toString())); // We are getting client
                order.setAddress(new AddressWaiter().GetOneByID(cols.get(2).toString())); // We are getting the address
                order.setStore(new CoffeShopWaiter().GetOneStore(cols.get(3).toString())); // we are getting the store
                order.setDelivery(new DeliveryWaiter().GetOne(Integer.parseInt(cols.get(4).toString()))); // we are getting the delivery
                order.setPayments(new PaymentWaiter().GetOne(Integer.parseInt(cols.get(5).toString()))); // we are getting the Payments
                order.setTotalProducts(Integer.parseInt(cols.get(6).toString()));
                order.setBrewComProducts(Double.parseDouble(cols.get(7).toString()));
                order.setDeliveryCost(Double.parseDouble(cols.get(8).toString()));
                order.setBrewComDelivery(Double.parseDouble(cols.get(9).toString()));
                order.setTotalOrder(Double.parseDouble(cols.get(10).toString()));
                order.setStatus(OrderSetStatus(cols.get(11).toString()));
                order.setDate(cols.get(12).toString());


                orderList.add(order);
            } catch (NumberFormatException | DateTimeException | NullPointerException ex) {

                System.out.printf("exemption" + ex.toString());
            }


        }


        return orderList;

    }


    private State OrderSetStatus(String statusDB) {
        State status = null;
        try {
            switch (statusDB) {
                case "1":
                    status = OrderGenerated.Instance();
                    break;
                case "2":
                    status = Preparing.Instance();
                    break;
                case "3":
                    status = ReadyForDelivery.Instance();
                    break;
                case "4":
                    status = PickedUp.Instance();
                    break;
                case "5":
                    status = InRoute.Instance();
                    break;
                case "6":
                    status = Delivered.Instance();
                    break;

            }
        } catch (Exception ex) {
            System.out.println("There was an error at the moment to retrieve the order status");
        }
        return status;
    }


    public void DisplayOrders() {

        List<Order> Orders = GetOrders();

        for (var order : Orders
        ) {
            System.out.printf("Order Id: %d%n", order.getOrder_Id());
            System.out.printf("Client: %s%n", order.getClient().SummarizedDisplay());
            System.out.printf("Address: %s%n", order.getAddress().SummarizedDisplay());
            System.out.printf("Store: %s%n", order.getStore().getStore_name());
            System.out.printf("Delivery: %s%n", order.getDelivery().getDelname());
            System.out.printf("Payment: %s%n", order.getPayments().getPayName());
            System.out.printf("Total Product: %.2f%n", order.getTotalProducts());
            System.out.printf("BrewCom Products: %.2f%n", order.getBrewComProducts());
            System.out.printf("Delivery Cost: %.2f%n", order.getDeliveryCost());
            System.out.printf("BrewCom Delivery: %.2f%n", order.getBrewComDelivery());
            System.out.printf("Total Order: %.2f%n", order.getTotalOrder());
            System.out.printf("Status: %s%n", order.getStatus());
            System.out.printf("Date: %s%n", order.getDate().toString());

            System.out.println("");
        }
    }


    public Order GetOrderByID(int orderId) {


        String sql = "select Order_ID," +
                "Client_ID," +
                "Address_ID," +
                "Store_ID," +
                "Delivery_ID," +
                "Payment_ID," +
                "TotalProducts," +
                "BrewComProducts," +
                "DeliveryCost," +
                "BrewComDelivery," +
                "TotalOrder," +
                "Status," +
                "Date " +
                " from Orders where Order_Id=" + orderId + " ";
        Order order = new Order();
        List<Object> result = DataWaiter.GetInstance().GetData(sql);
        List<Object> cols = new ArrayList<>();


        for (int i = 0; i < result.size(); i++) {
            try {


                cols = (List<Object>) result.get(i);

                order.setOrder_Id(Integer.parseInt(cols.get(0).toString()));
                order.setClient(new ClientsWaiter().GetOneByID(cols.get(1).toString())); // We are getting client
                order.setAddress(new AddressWaiter().GetOneByID(cols.get(2).toString())); // We are getting the address
                order.setStore(new CoffeShopWaiter().GetOneStore(cols.get(3).toString())); // we are getting the store
                order.setDelivery(new DeliveryWaiter().GetOne(Integer.parseInt(cols.get(4).toString()))); // we are getting the delivery
                order.setPayments(new PaymentWaiter().GetOne(Integer.parseInt(cols.get(5).toString()))); // we are getting the Payments
                order.setTotalProducts(Integer.parseInt(cols.get(6).toString()));
                order.setBrewComProducts(Double.parseDouble(cols.get(7).toString()));
                order.setDeliveryCost(Double.parseDouble(cols.get(8).toString()));
                order.setBrewComDelivery(Double.parseDouble(cols.get(9).toString()));
                order.setTotalOrder(Double.parseDouble(cols.get(10).toString()));
                //order.setStatus(cols.get(11).toString());
                order.setStatus(OrderSetStatus(cols.get(11).toString()));
                order.setDate(cols.get(12).toString());


            } catch (NumberFormatException | DateTimeException | NullPointerException ex) {

                System.out.printf("exemption" + ex.toString());
            }


        }


        return order;


    }

    public void DisplayOrderByID(int id) {

        Order order = GetOrderByID(id);


        System.out.printf("Order Id: %d%n", order.getOrder_Id());
        System.out.printf("Client: %s%n", order.getClient().SummarizedDisplay());
        System.out.printf("Address: %s%n", order.getAddress().SummarizedDisplay());
        System.out.printf("Store: %s%n", order.getStore().getStore_name());
        System.out.printf("Delivery: %s%n", order.getDelivery().getDelname());
        System.out.printf("Payment: %s%n", order.getPayments().getPayName());
        System.out.printf("Total Product: %.2f%n", order.getTotalProducts());
        System.out.printf("BrewCom Products: %.2f%n", order.getBrewComProducts());
        System.out.printf("Delivery Cost: %.2f%n", order.getDeliveryCost());
        System.out.printf("BrewCom Delivery: %.2f%n", order.getBrewComDelivery());
        System.out.printf("Total Order: %.2f%n", order.getTotalOrder());
        System.out.printf("Status: %s%n", order.getStatus());
        System.out.printf("Date: %s%n", order.getDate().toString());

        System.out.println("");
    }

    public void DeleteOrderById(int id) {
        boolean success = false;
        String querry = "delete from orders where order_id=" + id + " ";

        success = DataWaiter.GetInstance().ExecuteQuery(querry);

        if (success) {

            System.out.println("Order deleted successfully...");
        }

    }


    ///////////////////////////////////// Javier creates in order to implement State design pattern
    public List<Order> GetOrders(String type, String status, String Store_id, String Delivery_id) {

        Integer intStatus = Integer.parseInt(status);
        String condition = "";

        if (type == "Coffee" && (intStatus >= 1 && intStatus <= 3))
            condition = " where Store_id =" + Store_id + " and status = " + status + " order by order_id";
        else if (type == "Coffee" && (intStatus >= 4))
            condition = " where Store_id = " + Store_id + " and status >= " + status + " order by order_id";
        else if (type == "Delivery" && (intStatus <= 2))
            condition = " where Delivery_ID = " + Delivery_id + " and status <= " + status + " order by order_id";
        else if (type == "Delivery" && (intStatus >= 3 && intStatus <= 6))
            condition = " where Delivery_ID = " + Delivery_id + " and status = " + status + " order by order_id";
        else if (type == "Client" && (intStatus >= 1 && intStatus <= 5))
            condition = " where Client_ID = " + Delivery_id + " and status = " + status + " order by order_id";
        else if (type == "Client" && status == "0")
            condition = " where Client_ID = " + Delivery_id + " order by order_id";


        String sql = "select Order_ID," +
                "Client_ID," +
                "Address_ID," +
                "Store_ID," +
                "Delivery_ID," +
                "Payment_ID," +
                "TotalProducts," +
                "BrewComProducts," +
                "DeliveryCost," +
                "BrewComDelivery," +
                "TotalOrder," +
                "Status," +
                "Date " +
                " from Orders " + condition + ";";
        //System.out.println(sql);
        List<Object> result = DataWaiter.GetInstance().GetData(sql);
        List<Order> orderList = new ArrayList<>();
        List<Object> cols = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            try {

                Order order = new Order();

                cols = (List<Object>) result.get(i);

                order.setOrder_Id(Integer.parseInt(cols.get(0).toString()));
                /*order.setClient(Integer.parseInt(cols.get(1).toString())); //Process to get client
                order.setAddress(Integer.parseInt(cols.get(2).toString())); //Process to get Address
                order.setStore(Integer.parseInt(cols.get(3).toString())); // PRocess to get Store
                order.setDelivery(Integer.parseInt(cols.get(4).toString())); // PRocess to get Delivery
                order.setPayments(Integer.parseInt(cols.get(5).toString())); // Process to get Payment*/
                order.setClient(new ClientsWaiter().GetOneByID(cols.get(1).toString())); // We are getting client
                order.setAddress(new AddressWaiter().GetOneByID(cols.get(2).toString())); // We are getting the address
                order.setStore(new CoffeShopWaiter().GetOneStore(cols.get(3).toString())); // we are getting the store
                order.setDelivery(new DeliveryWaiter().GetOne(Integer.parseInt(cols.get(4).toString()))); // we are getting the delivery
                order.setPayments(new PaymentWaiter().GetOne(Integer.parseInt(cols.get(5).toString()))); // we are getting the Payments
                order.setTotalProducts(Integer.parseInt(cols.get(6).toString()));
                order.setBrewComProducts(Double.parseDouble(cols.get(7).toString()));
                order.setDeliveryCost(Double.parseDouble(cols.get(8).toString()));
                order.setBrewComDelivery(Double.parseDouble(cols.get(9).toString()));
                order.setTotalOrder(Double.parseDouble(cols.get(10).toString()));
                order.setStatus(OrderSetStatus(cols.get(11).toString()));
                order.setDate(cols.get(12).toString());


                orderList.add(order);
            } catch (NumberFormatException | DateTimeException | NullPointerException ex) {

                System.out.printf("exemption" + ex.toString());
            }


        }


        return orderList;

    }


    public static int GetLastOrderID() {

            int lastOrderId=0;

            String sql = "SELECT Order_ID FROM Orders ORDER BY Order_ID DESC LIMIT 1";

            List<Object> result = DataWaiter.GetInstance().GetData(sql);
            List<Object> cols = new ArrayList<>();


            for (int i = 0; i < result.size(); i++) {
                try {


                    cols = (List<Object>) result.get(i);

                    lastOrderId= Integer.parseInt(cols.get(0).toString());


                } catch (NumberFormatException | DateTimeException | NullPointerException ex) {

                    System.out.printf("exemption" + ex.toString());
                }


            }
            return lastOrderId;

        }

    }
