package BackOffice.CoffeShop;


import DataBase.ProductsWaiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Create Builder
public class Product {
     private int Category_Id;
    private int Product_Id;
    private String ProdName;
    private BigDecimal Price;
    private int Comission;
    private boolean Active;
    private int Store_ID;


    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int Category_Id) {
        this.Category_Id = Category_Id;
    }

    public int getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(int Product_Id) {
        this.Product_Id = Product_Id;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String ProdName) {
        this.ProdName = ProdName;
    }

    public BigDecimal getPrice() {
        return Price;
    }
    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }
    public int getComission() {
        return Comission;
    }

    public void setComission(int Comission) {
        this.Comission = Comission;
    }
    public boolean getActive() {
        return Active;
    }
    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public int getStore_ID() { return Store_ID;}

    public void setStore_ID(int Store_ID) { this.Store_ID = Store_ID;}

    public List<Product> GetAllProduct() {
        List<Product> list = new ArrayList<>();
        try {
            ProductsWaiter waiter = new ProductsWaiter();
            list = waiter.GetAllProduct();
        } catch(Exception ex) {
            System.out.println("Error retrieving all categories");
        }
        return list;
    }

    public List<Product> GetAllProduct(String parameter) {
        List<Product> list = new ArrayList<>();
        try {
            ProductsWaiter waiter = new ProductsWaiter();
            list = waiter.GetAllProduct(parameter);
        } catch(Exception ex) {
            System.out.println("Error retrieving all categories");
        }
        return list;
    }

    public Product GetOneProduct(String parameter) {
        Product product = new Product();
        try {
            ProductsWaiter waiter = new ProductsWaiter();
            product = waiter.GetOneProduct(parameter);
        } catch(Exception ex) {
            System.out.println("Error retrieving all categories");
        }
        return product;
    }


    public void DisplayAllProducts() {
        try {
            List<Product> list = GetAllProduct();

            System.out.println("Products:");
            for (Product prod : list) {
                System.out.println("Product ID: " + prod.getProduct_Id());
                System.out.println("Category ID: " + prod.getCategory_Id());
                System.out.println("Store ID: " + prod.getStore_ID());
                System.out.println("Product Name: " + prod.getProdName());
                System.out.println("Price: " + prod.getPrice());
                System.out.println("Commission: " + prod.getComission() + "%");
                System.out.println("Item Active: " + prod.getActive());
                System.out.println("-----------------------");
            }
        } catch(Exception ex) {
            System.out.println("Error displaying all products.");
        }
    }



    public boolean updateProduct() {
        ProductsWaiter waiter = new ProductsWaiter();
        return waiter.updateProduct(this);
    }

    public boolean deleteProduct(int productId) {
        ProductsWaiter waiter = new ProductsWaiter();
        return waiter.deleteProduct(productId);
    }

    public List<Product> GetProductForOrders(String type, String Store_ID, String prodName) {
        List<Product> list = new ArrayList<>();
        try
        {
            ProductsWaiter products = new ProductsWaiter();
            list = products.GetProducts(type,Store_ID,prodName);
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to retrieve the products");
        }
        return list;
    }
    public void DisplayAllProducts(List<Product> list) {
        try {
            System.out.println("Products:");
            for (Product prod : list) {
                String catName= Category.GetCategory(Integer.toString(prod.getCategory_Id())).getCatName();
                System.out.println("Store ID: " + Integer.toString(prod.getStore_ID()));
                System.out.println("Store: " + new Store().GetOneStore(Integer.toString(prod.getStore_ID())).getStore_name()) ;
                System.out.println("Category: " + Category.GetCategory(Integer.toString(prod.getCategory_Id())).getCatName());
                System.out.println("Product ID: " + prod.getProduct_Id());
                System.out.println("Product Name: " + prod.getProdName());
                System.out.println("Price: " + prod.getPrice());
                System.out.println("Commission: " + prod.getComission() + "%");
                System.out.println("Item Active: " + prod.getActive());
                System.out.println("-----------------------");
            }
        } catch(Exception ex) {
            System.out.println("Error displaying all products.");
        }
    }


}
