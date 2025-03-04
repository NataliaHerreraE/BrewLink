package BackOffice.CoffeShop.CoffeeCultureProductBuilder;

import java.math.BigDecimal;

public class BuilderCoffeeCultureProduct {

    private ProductBuilderCoffeeCulture productShopBuilder;

    public BuilderCoffeeCultureProduct(ProductBuilderCoffeeCulture productShopBuilder)
    {
        this.productShopBuilder = productShopBuilder;
    }

    public void createProductShop(String prodName, BigDecimal price, int commission)
    {
        this.productShopBuilder.BuildCategory_Id();
        this.productShopBuilder.BuildStore_ID();
        this.productShopBuilder.BuildProduct_Id();
        this.productShopBuilder.BuildProdName(prodName);
        this.productShopBuilder.BuildPrice(price);
        this.productShopBuilder.BuildCommission(commission);
        this.productShopBuilder.BuildActive();


    }

    public ProductCoffeeCulture getProduct() { return this.productShopBuilder.getProductShop(); }


}
