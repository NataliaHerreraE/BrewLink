package BackOffice.CoffeShop.TimHortonsProductBuilder;

import BackOffice.CoffeShop.AbstractFactory.FactoryType;

import java.math.BigDecimal;

public class BuilderTimHortonsProduct {

    private ProductBuilderTimHortons productShopBuilder;

    public BuilderTimHortonsProduct(ProductBuilderTimHortons productShopBuilder)
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

    public ProductTimHortons getProduct() { return this.productShopBuilder.getProductShop(); }


}
