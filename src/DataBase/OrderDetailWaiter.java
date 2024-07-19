package DataBase;

import BackOffice.Orders.OrderDetail;

import DataBase.Singleton.DataWaiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailWaiter {

    //get all the order details
    public List<OrderDetail> GetAllOrderDetails(){
        //Order_ID,Product_Id,Quantity, Comission
        String query="select Order_ID,Product_Id,Quantity,Comission,total,price from OrderDetail";
        List<Object> results = new ArrayList<>();
        List<OrderDetail> list = new ArrayList<>();
        //DataWaiter dataWaiter = new DataWaiter();



        results= DataWaiter.GetInstance().GetData(query);

        for (int i =0; i< results.size(); i++)
        {
            List<Object> cols = new ArrayList<>();
            cols = (List<Object>) results.get(i);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_ID(Integer.parseInt(cols.get(0).toString()));
            orderDetail.setProduct_ID(Integer.parseInt(cols.get(1).toString())); // Get the detail of the product
            orderDetail.setQuantity(Integer.parseInt(cols.get(2).toString()));
            orderDetail.setComissionOrderDetails(Double.parseDouble(cols.get(3).toString()));
            orderDetail.setTotal(Double.parseDouble(cols.get(4).toString()));
            orderDetail.setPrice(Double.parseDouble(cols.get(4).toString()));
            list.add(orderDetail);
        }

        return  list;
    }

    //Display all the orderDetail by id
    public void DisplayOrdersDetailsById(int id){
        //Order_ID, Product_Id,Quantity, Commission
        OrderDetail orderD=GetAllOrderDetailsById(id);


        System.out.println("Order ID: "+ orderD.getOrder_ID());
        System.out.println("Product_ID: "+ orderD.getProduct_ID());
        System.out.println("Quantity: "+ orderD.getQuantity());
        System.out.println("Commission: "+ orderD.getComissionOrderDetails());


        System.out.println(" ");

    }




    //get the object with the all orderderDetail by id
    public OrderDetail GetAllOrderDetailsById(int id){

        String query="select Order_ID,Product_Id,Quantity,Comission from OrderDetail where Order_ID = "+id+" ";
        List<Object> results = new ArrayList<>();

        //DataWaiter dataWaiter = new DataWaiter();



        results= DataWaiter.GetInstance().GetData(query);
        OrderDetail orderDetail = new OrderDetail();

        List<Object> cols = new ArrayList<>();
        cols = (List<Object>) results.get(0);


        orderDetail.setOrder_ID(Integer.parseInt(cols.get(0).toString()));
        orderDetail.setProduct_ID(Integer.parseInt(cols.get(1).toString()));
        orderDetail.setQuantity(Integer.parseInt(cols.get(2).toString()));
        orderDetail.setComissionOrderDetails(Double.parseDouble(cols.get(3).toString()));




        return  orderDetail;
    }

    //display all the orderDetail
    public void DisplayOrdersDetails(int id){
        //Order_ID, Product_Id,Quantity, Commission
        OrderDetail OrderD= GetAllOrderDetailsById(id);


        System.out.println("Order ID: "+ OrderD.getOrder_ID());
        System.out.println("Product_ID: "+ OrderD.getProduct_ID());
        System.out.println("Quantity: "+ OrderD.getQuantity());
        System.out.println("Commission: "+ OrderD.getComissionOrderDetails());


        System.out.println(" ");

    }






    //get the object with Orderdetail and products
    public List<OrderDetail> GetAllOrderDetailsWithProducts(){
        //Order_ID,Product_Id,Quantity, Comission
        String query="select OD.Order_ID," +
                "OD.Product_Id," +
                "Quantity," +
                "OD.Comission," +
                "Category_Id," +
                "ProdName," +
                "Price," +
                "PRO.Comission," +
                "Active " +
                "from OrderDetail as OD " +
                "INNER join Products as PRO " +
                "on OD.Product_Id=PRO.Product_Id ";
        List<Object> results = new ArrayList<>();
        List<OrderDetail> list = new ArrayList<>();
        //DataWaiter dataWaiter = new DataWaiter();

        //

        results= DataWaiter.GetInstance().GetData(query);

        for (int i =0; i< results.size(); i++)
        {
            List<Object> cols = new ArrayList<>();
            cols = (List<Object>) results.get(i);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_ID(Integer.parseInt(cols.get(0).toString()));
            orderDetail.setProduct_ID(Integer.parseInt(cols.get(1).toString()));
            orderDetail.setQuantity(Integer.parseInt(cols.get(2).toString()));
            orderDetail.setComissionOrderDetails(Double.parseDouble(cols.get(3).toString()));
            orderDetail.setCategory_Id(Integer.parseInt(cols.get(4).toString()));
            orderDetail.setProdName(cols.get(5).toString());
            orderDetail.setPrice(Double.parseDouble(cols.get(6).toString()));
            orderDetail.setComissionProduct(Double.parseDouble(cols.get(7).toString()));
            orderDetail.setActive(Boolean.parseBoolean(cols.get(8).toString()));
            list.add(orderDetail);
        }

        return  list;
    }

    //display the orderDetails with the products
    public void DisplayOrdersDetailsWithProducts(){
        //Order_ID, Product_Id,Quantity, Commission
        for (OrderDetail item: GetAllOrderDetailsWithProducts()
        ) {

            System.out.println("Order ID: "+ item.getOrder_ID());
            System.out.println("Product_ID: "+ item.getProduct_ID());
            System.out.println("Quantity: "+ item.getQuantity());
            System.out.println("Commission: "+ item.getComissionOrderDetails());
            System.out.println("Category_Id: "+ item.getOrder_ID());
            System.out.println("ProdName: "+ item.getProdName());
            System.out.println("Price: "+ item.getPrice());
            System.out.println("Commission: "+ item.getComissionProduct());
            System.out.println("Active: "+ item.isActive());



            System.out.println(" ");

        }



    }


    //insert

    public static void InsertOrdersOrderDetail(OrderDetail ordersD) throws SQLException {
/*
* Order_ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Product_Id INTEGER,
    Quantity INTEGER,
    Comission NUMERIC(5,2),*/

        String sql = "insert into OrderDetail (Order_ID,Product_Id," +
                "Quantity,Comission) values(?,?,?,?)  ";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\BrewLinkDB\\testproject.db"); PreparedStatement pstmt = conn.prepareStatement(sql);){






            pstmt.setInt(1, ordersD.getOrder_ID());
            pstmt.setInt(2, ordersD.getProduct_ID());
            pstmt.setInt(3, ordersD.getQuantity());
            pstmt.setDouble(4, ordersD.getComissionOrderDetails());




            pstmt.executeUpdate();




        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }


}