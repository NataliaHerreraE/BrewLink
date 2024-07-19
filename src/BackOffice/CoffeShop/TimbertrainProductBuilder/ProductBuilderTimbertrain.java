package BackOffice.CoffeShop.TimbertrainProductBuilder;

import java.math.BigDecimal;

public interface ProductBuilderTimbertrain {

    public void  BuildCategory_Id();
    public void  BuildProduct_Id();
    public void  BuildProdName(String prodName);
    public void  BuildPrice(BigDecimal price);
    public void  BuildCommission(int commission);
    public void  BuildActive();
    public void  BuildStore_ID();
    public ProductTimbertrain getProductShop();

}
