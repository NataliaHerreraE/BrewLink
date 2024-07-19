package BackOffice.Orders;
import BackOffice.CoffeShop.Product;
import DataBase.OrderDetailWaiter;
import DataBase.OrdersWaiter;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDetail {
    static List<OrderDetail> OrderDetailList = new ArrayList<>();
    public OrderDetail(){


    }

    List<Product>ProductList=null;
    public OrderDetail(List<Product> productList){

        this.ProductList=productList;
    }



    public void Create()
    {

    }

    public void Modify()
    {

    }

    public void Delete()
    {

    }

    public void GetOne()
    {

    }

    public void GetAll()
    {

    }

    //Cambios Jose

    private int order_ID,product_ID,quantity;
    private Double comissionOrderDetails;
    private Double comissionProduct;
    private Double price;

    private Double deliveryCost;

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }



    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private Double total;

    private int  Category_Id,Product_Id;
    private String ProdName;
    boolean active;


    public static List<OrderDetail> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        OrderDetailList = orderDetailList;
    }


    //getter and setter
    public Double getComissionProduct() {
        return comissionProduct;
    }

    public void setComissionProduct(Double comissionProduct) {
        this.comissionProduct = comissionProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int category_Id) {
        Category_Id = category_Id;
    }

    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int product_Id) {
        Product_Id = product_Id;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getComissionOrderDetails() {
        return comissionOrderDetails;
    }

    public void setComissionOrderDetails(Double comissionOrderDetails) {
        this.comissionOrderDetails = comissionOrderDetails;
    }



    //methods

    public OrderDetail CreateNewOrderDetail(List<Product> prodcuts, String Store_ID )
    {


        double commision=0,totalOrder=0;
        boolean stop=false;
        Product product = new Product();
        OrderDetail orderD=null;




        while (!stop){

            //ask to the client for the productID
            int productId=Integer.parseInt(DisplayAddProducts());

            if (productId==0){
                stop=true;
                break;
            }

            orderD = new OrderDetail();

            //ask for the quantity
            int quantity=Integer.parseInt(DisplayQuantity());



            BigDecimal subTotal= BigDecimal.valueOf(0);

            //set the values in the object
            orderD.setProduct_ID(productId);
            orderD.setQuantity(quantity);
            orderD.setOrder_ID(OrdersWaiter.GetLastOrderID());
            product=GetProductByOrderId(orderD.getProduct_ID(),prodcuts);

            //Get the specific product by the ID

            orderD.setProdName(product.getProdName());
            orderD.setPrice(Double.valueOf(product.getPrice().toString()));

            if (product!=null){
                //calc total of the product price * quantity + comission
                commision = ((double) product.getComission() / 100) + 1;
                //calc the subtotal order
                subTotal=product.getPrice().multiply(BigDecimal.valueOf(quantity));
                total= Double.valueOf(String.valueOf(subTotal.multiply(BigDecimal.valueOf(commision)))) ;
                //set the commision
                orderD.setComissionProduct(commision);
                orderD.setComissionOrderDetails(commision);
                //set the total
                orderD.setTotal(total);
                setComissionProduct(commision);

                //add the object to the list with the total by each product * quantity + comission
                OrderDetailList.add(orderD);

                //set the current total
                totalOrder=TotalOrderDetail();

                //this function TotalOrder add all the total of each product and return the total of the order
                System.out.println("Total Order: " + String.format("%.2f", totalOrder));



            }else{
                return null;
            }



        }
        //set the final total amount

       // total=totalOrder;

       // orderD.setQuantity(TotalProductOrderDetail());

        return orderD;
    }

    public String DisplayAddProducts(){

        Scanner scanner = new Scanner(System.in);
        String idProduct;
        System.out.println("Write the ID product that you want to add or press 0 to finish");
        idProduct=scanner.next();

        if (IsNumber(idProduct)){
            return idProduct;

        }else{

            DisplayAddProducts();
        }



        return idProduct;
    }

    public String DisplayQuantity(){

        Scanner scanner = new Scanner(System.in);
        String quantity;
        System.out.println("Write the quantity");
        quantity=scanner.next();

        if (IsNumber(quantity)){
            return quantity;

        }else{

            DisplayQuantity();
        }



        return quantity;
    }

    public boolean IsNumber(String number){

        if (number.matches("[0-9]+"))
            return true;

        System.out.println("Sorry, enter a numerical value");
        return false;
    }


    public Product GetProductByOrderId(int product_ID,List<Product>productsL ){


        for (Product product:productsL
        ) {
            if (product.getProduct_Id()==product_ID){

                return  product;
            }
        }



        return null;

    }

    //total of the all product + comissions
    public double TotalOrderDetail(){
        double total=0;
        for (OrderDetail orderD: getOrderDetailList()
        ) {
            total+=orderD.total;
        }

        return total;

    }

    //calc the total product bought for the client
    private int TotalProductOrderDetail(){
        int totalProduct=0;
        for (OrderDetail order: getOrderDetailList()
        ) {
            totalProduct+=order.quantity;
        }

        return totalProduct;

    }

    private double TotalCommProductOrderDetail(){
        double totalProductCommision=0;
        for (OrderDetail order: getOrderDetailList()
        ) {
            totalProductCommision+=order.comissionProduct;
        }

        return totalProductCommision;

    }
    //inserta todo el detalle de la orden una por una
    public void InsertOrderDetails() throws SQLException {


        for (OrderDetail orderD:getOrderDetailList()
             ) {
                OrderDetailWaiter.InsertOrdersOrderDetail(orderD);
        }

    }



}
