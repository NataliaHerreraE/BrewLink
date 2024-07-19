package BackOffice.CoffeShop.CoffeeCultureProductBuilder;


import java.math.BigDecimal;

// Create Builder
public class ProductCoffeeCulture {


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

public void Display()
{
    try
    {
        System.out.println("Factory: " + this.getCategory_Id() );
        System.out.println("Store Product: " + this.getStore_ID() );
        System.out.println("Prod name: " + this.getProdName());
        System.out.println("Was created successfully");
    }
    catch (Exception ex)
    {
        System.out.println("There was an error at the moment to display the Product ");
    }
}


}
