package BackOffice.CoffeShop.StarbucksProductBuilder;

import java.math.BigDecimal;

public interface ProductBuilderStarbucks {

    public void  BuildCategory_Id();
    public void  BuildProduct_Id();
    public void  BuildProdName(String prodName);
    public void  BuildPrice(BigDecimal price);
    public void  BuildCommission(int commission);
    public void  BuildActive();
    public void  BuildStore_ID();
    public ProductStarbucks getProductShop();

}
