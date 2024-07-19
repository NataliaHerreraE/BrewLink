package BackOffice.CoffeShop.TimbertrainProductBuilder;

import java.math.BigDecimal;

public class BuilderTimbertrainProduct {

    private ProductBuilderTimbertrain productShopBuilder;

    public BuilderTimbertrainProduct(ProductBuilderTimbertrain productShopBuilder)
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

    public ProductTimbertrain getProduct() { return this.productShopBuilder.getProductShop(); }


}
