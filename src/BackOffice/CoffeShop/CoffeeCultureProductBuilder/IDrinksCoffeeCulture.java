package BackOffice.CoffeShop.CoffeeCultureProductBuilder;
import java.math.BigDecimal;

public class IDrinksCoffeeCulture implements ProductBuilderCoffeeCulture {

    private final ProductCoffeeCulture product;

    public IDrinksCoffeeCulture() {
        this.product = new ProductCoffeeCulture();
    }

    @Override
    public void BuildCategory_Id() {
                product.setCategory_Id(1);
    }

    @Override
    public void BuildProduct_Id() {
        product.setProduct_Id(0);
    }

    @Override
    public void BuildProdName(String prodName) {
        product.setProdName(prodName);
    }

    @Override
    public void BuildPrice(BigDecimal price) {
        product.setPrice(price);
    }

    @Override
    public void BuildCommission(int commission) {
        product.setComission(commission);
    }

    @Override
    public void BuildActive() {
        product.setActive(true);
    }

    @Override
    public void BuildStore_ID() {
        //Change the value according the product type beacuse is Tim Hortons
        product.setStore_ID(1);
    }

    @Override
    public ProductCoffeeCulture getProductShop() {
        return this.product;
    }
}
