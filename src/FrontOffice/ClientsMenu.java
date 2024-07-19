package FrontOffice;
import BackOffice.Customers.Address;
import BackOffice.Customers.Clients;
import BackOffice.DeliveryPartners.Delivery;
import BackOffice.Management.Payments;
import BackOffice.Orders.Order;
import BackOffice.Orders.OrderDetail;
import DataBase.AddressWaiter;
import DataBase.ClientsWaiter;
import DataBase.Singleton.DataWaiter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static BackOffice.Customers.Address.MenuAddress;

public class ClientsMenu {

    public static void MainMenuClient(int[] val)
    {
        //objects
        Order order = new Order();
        Delivery delivery =  new Delivery();
        OrderDetail orderDetail = new OrderDetail();

        List<Order> orders = new ArrayList<>();
        int usr=val[0];
        String opc="";
        Scanner con = new Scanner(System.in);
        do {
            ClientMenu(usr);
            System.out.println ("Option: ");
            opc = con.next();
            switch (opc) {
                case "1":
                    // Call login function
                    if (usr == 0)
                        usr = Login(usr);
                    break;
                case "2":
                    // Call register new Client menu
                    if (usr == 0)
                        usr = Register();
                    break;
                case "3":
                    // Call logout function
                    if (usr != 0)
                        usr = 0;
                    break;
                case "4":
                    // call Edit Delivery Addresses
                    if (usr != 0)
                        MenuAddress(usr);
                    break;
                case "5":
                    //go back to previous Menu
                    //JOse Register a new Order
                    if (usr != 0)

                        order.CreateOrder(usr);
                        Payments.display();



                    break;
                case "6": // current
                    //go back to previous Menu
                    if (usr != 0) {
                        orders = order.GetOrders("Client", "1", "", Integer.toString(usr));
                        if (!orders.isEmpty()) {
                            order.DisplayOrders(orders);
                        } else {
                            System.out.println("There are not orders to display");
                        }
                    }
                    break;
                case "7": //histoical
                    //go back to previous Menu
                    if (usr != 0) {
                        orders = order.GetOrders("Client", "0", "", Integer.toString(usr));
                        if (!orders.isEmpty()) {
                            order.DisplayOrders(orders);
                        } else {
                            System.out.println("There are not orders to display");
                        }
                    }
                    break;
                case "0":
                    //go back to previous Menu
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (!opc.equals("0"));

    }
    public void LoggedMenu()
    {
        System.out.println("Addresses");
        System.out.println("Orders");
        /*Todo lo que puede hacer una vez loggeado*/
    }
    public static void ClientMenu(int usr){
        //Runtime.getRuntime().exec("cls");
        if (usr != 0) {
            System.out.println("Client Menu ( Logged USER_ID: "+usr+" )");
        }else{
            System.out.println("Client Menu");
        }



        if (usr == 0) {
            System.out.println("1.- Login");
            System.out.println("2.- Register");
        }
        else
        {
            System.out.println("3.- Logout");
            System.out.println("4.- Edit Delivery Address");
            System.out.println("5.- Register a new Order");
            System.out.println("6.- See current order");
            System.out.println("7.- See historical your orders");
        }
        System.out.println("(0 for exit)");
    }

    public static int Login(int usrId){
        if (usrId==0) {
            Scanner con = new Scanner(System.in);
            System.out.println("Login Menu");
            System.out.println("User: ");
            String usr = con.nextLine();
            System.out.println("Pasword: ");
            String pwd = con.nextLine();
            usrId = validateUser(usr, pwd);
            if (usrId == 0) {
                System.out.println("Incorrect user/password, please try to login again...");
            }
        }
        else{
            System.out.println("To login with a different user, please logout first...");
        }
        return usrId;
    }
    public static int validateUser(String usr,String pwd){
        int usrId=0;
        String DBpasswd;
        DBpasswd= ClientsWaiter.getUserPaswd(usr);
        if (pwd.equals(DBpasswd)){
            usrId=ClientsWaiter.getUserIdFromClient(usr);
        }

        return usrId;
    }


    public static int Register(){
        Scanner con = new Scanner(System.in);
        System.out.println("Email: ");
        String email= con.nextLine();
        System.out.println("Password: ");
        String pwd= con.nextLine();
        System.out.println("First Name: ");
        String FN= con.nextLine();
        System.out.println("Meddle Name: ");
        String MN= con.nextLine();
        System.out.println("Last Name: ");
        String LN= con.nextLine();
        System.out.println("Phone: ");
        String phn= con.nextLine();
        System.out.println("Address Name(Alias): ");
        String adrsN= con.nextLine();
        System.out.println("Address: ");
        String ads= con.nextLine();
        System.out.println("City: ");
        String cty= con.nextLine();
        System.out.println("Province: ");
        String prvc= con.nextLine();
        System.out.println("Zip Code: ");
        String zc= con.nextLine();


        System.out.println("Add the user to the data base an go to the ordering menu ");
        Clients client = new Clients(1,email,pwd,FN,MN,LN,phn);
        try {
            ClientsWaiter.InsertClient(client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int usr=ClientsWaiter.getUserIdFromClient(email);
        Address address = new Address(usr,1,ads,cty,prvc,zc,true,adrsN);
        try {
            AddressWaiter.InsertAddress(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }


}
