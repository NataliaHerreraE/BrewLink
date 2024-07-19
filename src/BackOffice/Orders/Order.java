package BackOffice.Orders;

import BackOffice.CoffeShop.Product;
import BackOffice.CoffeShop.Store;
import BackOffice.Customers.Address;
import BackOffice.Customers.Clients;
import BackOffice.DeliveryPartners.Delivery;
import BackOffice.Management.Payments;
import BackOffice.Orders.State.*;
import DataBase.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    //// Cambios Jose


    public Order(){

    }

    private int order_Id;

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    private int client_id;
    private Clients client;
    private Address address;

    private Store store;
    private Delivery delivery;
    private Payments payments;
    private double totalProducts, brewComProducts, deliveryCost, brewComDelivery, totalOrder;
    private State status;
    Order nOrder;
    String paymentOpc;
    public List<OrderDetail> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        OrderDetailList = orderDetailList;
    }

    List<OrderDetail>OrderDetailList;
    List<Product> products = new ArrayList<>();

    public List<Order> getOrderList() {
        return OrderList;

    }

    public void setOrderList(List<Order> orderList) {
        OrderList = orderList;
    }

    List<Order>OrderList = new ArrayList<>();

    public Order(Clients client, Store store, Delivery delivery, Payments payments, int quantity, Double comissionProduct, Double deliveryCost, int i, Double total, State status, String date) {





    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    OrderDetail orderDetail = new OrderDetail(getProducts());
    PaymentWaiter paymentWaiter = new PaymentWaiter();
    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }


    String date;

    public int getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public double getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(double totalProducts) {
        this.totalProducts = totalProducts;
    }

    public double getBrewComProducts() {
        return brewComProducts;
    }

    public void setBrewComProducts(double brewComProducts) {
        this.brewComProducts = brewComProducts;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getBrewComDelivery() {
        return brewComDelivery;
    }

    public void setBrewComDelivery(double brewComDelivery) {
        this.brewComDelivery = brewComDelivery;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order(Clients client, Address address, Store store, Delivery delivery, Payments payments, int totalProducts, double brewComProducts, double deliveryCost, double brewComDelivery, double totalOrder, OrdersState status,
                 String date) {

        this.client = client;
        this.address = address;
        this.store = store;
        this.delivery = delivery;
        this.payments = payments;
        this.totalProducts = totalProducts;
        this.brewComProducts = brewComProducts;
        this.deliveryCost = deliveryCost;
        this.brewComDelivery = brewComDelivery;
        this.totalOrder = totalOrder;
       // this.status = OrderSetStatus(status.toString()); // JA
        this.date = date;
    }

    public Order(List<OrderDetail>orderDlist) {

    this.OrderDetailList=orderDlist;
    }



    private State OrderSetStatus(String statusDB) {
       // State status = setStatus(statusDB);
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
            pstmt.setString(11, orders.getStatus().getCurrentState().toString()); //JA
            pstmt.setObject(12, orders.getDate());


            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    /*
        public List<Order> GetOrders(){

            String sql="select Order_ID," +
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

            List<Object> result = DataWaiter.GetData(sql);
            List<Order> orderList = new ArrayList<>();
            List<Object> cols = new ArrayList<>();

            for (int i = 0; i < result.size(); i++) {
                try{

                    Order order = new Order();

                    cols = (List<Object>) result.get(i);

                    order.setOrder_Id( Integer.parseInt(cols.get(0).toString()));
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
                    order.setStatus(OrderSetStatus(cols.get(11).toString())); // JA
                    order.setDate(cols.get(12).toString());


                    orderList.add(order);
                }catch (NumberFormatException | DateTimeException | NullPointerException ex){

                    System.out.printf("exemption"+ex.toString());
                }


            }


            return orderList;

        }
    */
    public List<Order> GetOrders(String type, String status, String Store_id, String Delivery_id) {
        List<Order> orders = new ArrayList<>();
        try {
            OrdersWaiter ordersWaiter = new OrdersWaiter();
            orders = ordersWaiter.GetOrders(type, status, Store_id, Delivery_id);
        } catch (Exception ex) {
            System.out.println("There was an error at the moment to retrieve the orders to track");
        }
        return orders;
    }

    public boolean DisplayOrders(String type, String status, String Store_id, String Delivery_id) {
        boolean success = false;
        try {
            List<Order> orders = new ArrayList<>();
            orders = GetOrders(type, status, Store_id, Delivery_id);
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    String text = "Order #: " + order.getOrder_Id();
                    text += "\n" + order.getClient().SummarizedDisplay();
                    text += "\n" + order.getAddress().SummarizedDisplay();
                    text += "\nStore: " + order.getStore().getStore_name();
                    text += "\nDelivery: " + order.getDelivery().getDelname();
                    text += "\nStatus: " + order.getStatus().getDescStatus();
                    System.out.println(text);
                    success = true;
                }
            } else {
                System.out.println("There are not orders to Display");
            }
        } catch (Exception ex) {
            System.out.println("There was an error at the moment to display the orders to track");
        }
        return success;
    }

    public boolean DisplayOrders(List<Order> orders) {
        boolean success = false;
        try {
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    String text = "***** ORDER #: " + order.getOrder_Id() + " *****";
                    text += "\n" + order.getClient().SummarizedDisplay();
                    text += "\n" + order.getAddress().SummarizedDisplay();
                    text += "\nStore: " + order.getStore().getStore_name();
                    text += "\nPayment: " + order.getPayments().getPayName();
                    text += "\nDelivery: " + order.getDelivery().getDelname();
                    text += "\nStatus: " + order.getStatus().getDescStatus();
                    text += "\n";
                    System.out.println(text);
                    success = true;
                }
            } else {
                System.out.println("There are not orders to Display");
            }
        } catch (Exception ex) {
            System.out.println("There was an error at the moment to display the orders to track");
        }
        return success;
    }


    public void DisplayOrders() {

        List<Order> Orders = new OrdersWaiter().GetOrders();

        for (var order : Orders
        ) {
            System.out.printf("Order Id: %d%n", order.getOrder_Id());
            System.out.printf("Client: %s%n", order.getClient().getFirstName());
            System.out.printf("Address: %s%n", order.getAddress().getAddressName());
            System.out.printf("Store: %s%n", order.getStore().getStore_name());
            System.out.printf("Delivery: %s%n", order.getDelivery().getDelname());
            System.out.printf("Payment: %s%n", order.getPayments().getPayName());
            System.out.printf("Total Product: %.2f%n", order.getTotalProducts());
            System.out.printf("BrewCom Products: %.2f%n", order.getBrewComProducts());
            System.out.printf("Delivery Cost: %.2f%n", order.getDeliveryCost());
            System.out.printf("BrewCom Delivery: %.2f%n", order.getBrewComDelivery());
            System.out.printf("Total Order: %.2f%n", order.getTotalOrder());
            System.out.printf("Status: %s%n", order.getStatus().getDescStatus());
            System.out.printf("Date: %s%n", order.getDate().toString());

            System.out.println("");
        }


    }

    public void CreateOrder(int client_id) {
        String option = "";
        Scanner myObj = new Scanner(System.in);
        Payments payments = new Payments();

        nOrder = new Order();
        delivery = new Delivery();
        this.client_id=client_id;
        store= new Store();
        Delivery deliveryObj = new Delivery();
        payments = new Payments();
        getClientObject();
        getAddressObject();


        try {
            do {
                //Search product by description
                System.out.println("Please type the description of one item to buy:");
                option = myObj.nextLine();
                Product product = new Product();


                        //String type, String Store_ID, String prodName
                        products = product.GetProductForOrders("Description", "", option);
                        if (!products.isEmpty()) {
                            product.DisplayAllProducts(products);
                            do {
                        System.out.println("Please type the STORE ID where you want to buy:");
                        System.out.println("(0 to exit)");
                        option = myObj.nextLine();
                        products = product.GetProductForOrders("Store", option, "");
                        product.DisplayAllProducts(products);
                        this.store.setStore_ID(Integer.parseInt(option));
                        if (!option.isEmpty()) {





                            //Order orderCreated = new Order(getClient(),store.GetOneStore(option),delivery.GetDeliveryOption(),payments,orderDetail.getQuantity(),orderDetail.getComissionOrderDetails(), (double) delivery.getComission(),0,totalOrder,status,date);


                            orderDetail=orderDetail.CreateNewOrderDetail(products, String.valueOf(store.getStore_ID()));
                            delivery.DisplayAll();
                            //get the opc choose for the client;
                            delivery = delivery.GetDeliveryOption();

                            //crea el objeto order para guardarlo en la bd con toda la info

                            PaymentWaiter paymentWaiter = new PaymentWaiter();
                            //desplega la lista de pagos disponibles
                            payments .DisplayAllPayments();
                            //guarda el metodo de pago
                            paymentOpc = payments.GetPaymentsOption();

                            createNewOrderObject();

                            //metodo para insertar en la bd
                            OrdersWaiter.InsertOrders(nOrder);

                            //metodo para insertar cada una de los productos en el detalle de la orden
                            // orderDetail.InsertOrderDetails();

                            return;
                        }




                    } while (!option.equals("0"));
                } else {
                    System.out.println("There are not products to show with description: " + option);
                }
                System.out.println("(0 to exit) or press any other char to continue");
                option = myObj.nextLine();
            }
            while (!option.equals("0"));
        } catch (Exception ex) {
            System.out.println("There was an error at the moment to create a product" + ex);
        }



    }
//este es el objeto que se va a guardar en la bd
    private void createNewOrderObject() {
        LocalDateTime time = LocalDateTime.now();

        nOrder.setClient(client);
        nOrder.order_Id=OrdersWaiter.GetLastOrderID()+1;
        nOrder.store= store;
        nOrder.delivery = getDelivery();
        nOrder.payments = payments;
        nOrder.setTotalProducts(orderDetail.getQuantity());
        nOrder.setClient_id(client.getClientId());
        nOrder.deliveryCost = delivery.getComission();
        nOrder.setTotalOrder(orderDetail.TotalOrderDetail()+delivery.getComission());//total with delivery include
        nOrder.brewComProducts=orderDetail.getComissionProduct();
        nOrder.setAddress(address);
        nOrder.setPayments(payments);
        this.status = OrderGenerated.Instance();
        nOrder.date = time.toString();
        nOrder.setOrderDetailList(OrderDetail.getOrderDetailList());
        nOrder.setPayments(paymentWaiter.GetOne(Integer.parseInt(paymentOpc)));






    }

    Clients getClientObject(){
        client= new Clients();
        ClientsWaiter clientsWaiter = new ClientsWaiter();



        client= clientsWaiter.GetOneByID(String.valueOf( client_id));

        return  client;
    }

    void getAddressObject(){

        address=new Address();


        address = AddressWaiter.GetAddressObject(String.valueOf(client_id));



    }










}



