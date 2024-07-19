package BackOffice.CoffeShop.TimbertrainProductBuilder;
import java.math.BigDecimal;

public class IBakeryTimbertrain implements ProductBuilderTimbertrain {

    private final ProductTimbertrain product;

    public IBakeryTimbertrain() {
        this.product = new ProductTimbertrain();
    }

    @Override
    public void BuildCategory_Id() {
                product.setCategory_Id(2);
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
    public ProductTimbertrain getProductShop() {
        return this.product;
    }
}
