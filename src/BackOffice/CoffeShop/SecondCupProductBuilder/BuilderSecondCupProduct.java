package BackOffice.CoffeShop.SecondCupProductBuilder;

import java.math.BigDecimal;

public class BuilderSecondCupProduct {

    private ProductBuilderSecondCup productShopBuilder;

    public BuilderSecondCupProduct(ProductBuilderSecondCup productShopBuilder)
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

    public ProductSecondCup getProduct() { return this.productShopBuilder.getProductShop(); }


}
