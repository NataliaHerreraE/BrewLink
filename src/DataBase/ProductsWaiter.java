package DataBase;

import BackOffice.CoffeShop.Product;
import DataBase.Singleton.DataWaiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsWaiter {

    public List<Product> GetProducts(String type, String Store_ID, String prodName) {
        List<Product> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        String query ="";

        if (type=="Store")
            query ="SELECT Category_Id, Product_Id, Store_ID, ProdName, Price, Comission, Active FROM Products where Store_ID =" + Store_ID + " and Active=1;";
        else if(type=="Description")
            query="SELECT Category_Id, Product_Id, Store_ID, ProdName, Price, Comission, Active FROM Products where ProdName like('%" + prodName + "%') and Active=1;";

        try {
            results = DataWaiter.GetInstance().GetData(query); // Fetch product data using the specific method for a single product
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                Product product = new Product();
                product.setProduct_Id(Integer.parseInt(cols.get(1).toString()));
                product.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                product.setStore_ID(Integer.parseInt(cols.get(2).toString())); // Set Store_ID
                product.setProdName(cols.get(3).toString());
                product.setPrice(new BigDecimal(cols.get(4).toString()));
                product.setComission(Integer.parseInt(cols.get(5).toString()));
                product.setActive((int) cols.get(6) == 1); // Assuming 1 for true and 0 for false
                list.add(product);
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving a specific product - From Waiter");
        }
        return list;
    }


    public List<Product> GetAllProduct() {
        List<Product> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Product_Id, Store_ID, ProdName, Price, Comission, Active FROM Products"); // Fetch product data using the specific method for products
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                Product product = new Product();
                product.setProduct_Id(Integer.parseInt(cols.get(1).toString()));
                product.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                product.setStore_ID(Integer.parseInt(cols.get(2).toString()));
                product.setProdName(cols.get(3).toString());
                product.setPrice(new BigDecimal(cols.get(4).toString()));
                product.setComission(Integer.parseInt(cols.get(5).toString()));
                product.setActive((int) cols.get(6) == 1); // Assuming 1 for true and 0 for false
                list.add(product);
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving products - Waiter from CoffeShopWaiter");
        }
        return list;
    }

    public Product GetOneProduct(String parameter) {
        Product product = new Product();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Product_Id, Store_ID, ProdName, Price, Comission, Active FROM Products WHERE Product_ID ="+ parameter +";"); // Fetch product data using the specific method for products
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;

                product.setProduct_Id(Integer.parseInt(cols.get(1).toString()));
                product.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                product.setStore_ID(Integer.parseInt(cols.get(2).toString()));
                product.setProdName(cols.get(3).toString());
                product.setPrice(new BigDecimal(cols.get(4).toString()));
                product.setComission(Integer.parseInt(cols.get(5).toString()));
                product.setActive((int) cols.get(6) == 1); // Assuming 1 for true and 0 for false

            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving products - Waiter from CoffeShopWaiter");
        }
        return product;
    }

    public List<Product> GetAllProduct(String parameter) {
        List<Product> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Product_Id, Store_ID, ProdName, Price, Comission, Active FROM Products WHERE ProdName like('%"+ parameter +"%');"); // Fetch product data using the specific method for products
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                Product product = new Product();
                product.setProduct_Id(Integer.parseInt(cols.get(1).toString()));
                product.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                product.setStore_ID(Integer.parseInt(cols.get(2).toString()));
                product.setProdName(cols.get(3).toString());
                product.setPrice(new BigDecimal(cols.get(4).toString()));
                product.setComission(Integer.parseInt(cols.get(5).toString()));
                product.setActive((int) cols.get(6) == 1); // Assuming 1 for true and 0 for false
                list.add(product);
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving products - Waiter from CoffeShopWaiter");
        }
        return list;
    }


    public boolean addFactoryBuilderProduct(Integer Category, Integer Store, String prodName,BigDecimal price, Integer commission,boolean active ) {
        boolean sucess=false;
        try
        {
            String sql ="INSERT INTO Products (Category_Id, Store_ID, ProdName, Price, Comission, Active) VALUES ("
                    + Category + ","
                    + Store + ","
                    + "'" + prodName + "',"
                    + price + ","
                    + commission + ","
                    + active + ");";

            DataWaiter.GetInstance().ExecuteQuery(sql);
        }
        catch (Exception ex)
        {
            System.out.println("There was an issue at the moment to retrieve");
        }

        return sucess;
    }


    public boolean updateProduct(Product product) {
        boolean success=false;
        try
        {
            String sql ="Update Products set ProdName = '"+ product.getProdName() +"', Price ="+ product.getPrice() +", Comission ="+ product.getComission() +", Active = "+ product.getActive() +"  where product_id = "+ product.getProduct_Id() +";";
            DataWaiter.GetInstance().ExecuteQuery(sql);
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("There was an issue at the moment to retrieve");
        }

        return success;
    }

    public boolean deleteProduct(int productId) {
        boolean sucess=false;
        try
        {
            String sql ="DELETE FROM Products WHERE Product_Id="+ productId +";";
            DataWaiter.GetInstance().ExecuteQuery(sql);
            sucess=true;
        }
        catch (Exception ex)
        {
            System.out.println("There was an issue at the moment to retrieve");
        }

        return sucess;
    }


}
