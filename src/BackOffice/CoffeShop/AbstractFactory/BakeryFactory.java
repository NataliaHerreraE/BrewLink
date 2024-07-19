package BackOffice.CoffeShop.AbstractFactory;

import BackOffice.CoffeShop.CoffeeCultureProductBuilder.BuilderCoffeeCultureProduct;
import BackOffice.CoffeShop.CoffeeCultureProductBuilder.IBakeryCoffeeCulture;
import BackOffice.CoffeShop.CoffeeCultureProductBuilder.ProductCoffeeCulture;
import BackOffice.CoffeShop.SecondCupProductBuilder.BuilderSecondCupProduct;
import BackOffice.CoffeShop.SecondCupProductBuilder.IBakerySecondCup;
import BackOffice.CoffeShop.SecondCupProductBuilder.ProductSecondCup;
import BackOffice.CoffeShop.StarbucksProductBuilder.BuilderStarbucksProduct;
import BackOffice.CoffeShop.StarbucksProductBuilder.IBakeryStarbucks;
import BackOffice.CoffeShop.StarbucksProductBuilder.ProductStarbucks;
import BackOffice.CoffeShop.TimHortonsProductBuilder.BuilderTimHortonsProduct;
import BackOffice.CoffeShop.TimHortonsProductBuilder.IBakeryTimHortons;
import BackOffice.CoffeShop.TimHortonsProductBuilder.ProductTimHortons;
import BackOffice.CoffeShop.TimbertrainProductBuilder.BuilderTimbertrainProduct;
import BackOffice.CoffeShop.TimbertrainProductBuilder.IBakeryTimbertrain;
import BackOffice.CoffeShop.TimbertrainProductBuilder.ProductTimbertrain;
import DataBase.ProductsWaiter;
import DataBase.Singleton.DataWaiter;

import java.math.BigDecimal;

public class BakeryFactory extends AbstractFactory {

    @Override
    public ProductCoffeeCulture createCoffeeCulture(String prodName, BigDecimal price, int commission) {
        IBakeryCoffeeCulture iBakery = new IBakeryCoffeeCulture();
        BuilderCoffeeCultureProduct builder = new BuilderCoffeeCultureProduct(iBakery);
        builder.createProductShop(prodName,price,commission);
        ProductCoffeeCulture product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductSecondCup createSecondCup(String prodName, BigDecimal price, int commission) {
        IBakerySecondCup iBakery = new IBakerySecondCup();
        BuilderSecondCupProduct builder = new BuilderSecondCupProduct(iBakery);
        builder.createProductShop(prodName,price,commission);
        ProductSecondCup product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductStarbucks createStarbucks(String prodName, BigDecimal price, int commission) {
        IBakeryStarbucks iBakery = new IBakeryStarbucks();
        BuilderStarbucksProduct builder = new BuilderStarbucksProduct(iBakery);
        builder.createProductShop(prodName,price,commission);
        ProductStarbucks product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductTimbertrain createTimbertrain(String prodName, BigDecimal price, int commission) {
        IBakeryTimbertrain iBakery = new IBakeryTimbertrain();
        BuilderTimbertrainProduct builder = new BuilderTimbertrainProduct(iBakery);
        builder.createProductShop(prodName,price,commission);
        ProductTimbertrain product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductTimHortons createTimHortons(String prodName, BigDecimal price, int commission) {
        IBakeryTimHortons iBakery = new IBakeryTimHortons();
        BuilderTimHortonsProduct builder = new BuilderTimHortonsProduct(iBakery);
        builder.createProductShop(prodName,price,commission);
        ProductTimHortons product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }
}
