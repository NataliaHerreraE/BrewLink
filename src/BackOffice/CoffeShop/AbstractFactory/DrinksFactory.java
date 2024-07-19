package BackOffice.CoffeShop.AbstractFactory;

import BackOffice.CoffeShop.CoffeeCultureProductBuilder.BuilderCoffeeCultureProduct;
import BackOffice.CoffeShop.CoffeeCultureProductBuilder.IDrinksCoffeeCulture;
import BackOffice.CoffeShop.CoffeeCultureProductBuilder.ProductCoffeeCulture;
import BackOffice.CoffeShop.SecondCupProductBuilder.BuilderSecondCupProduct;
import BackOffice.CoffeShop.SecondCupProductBuilder.IDrinksSecondCup;
import BackOffice.CoffeShop.SecondCupProductBuilder.ProductSecondCup;
import BackOffice.CoffeShop.StarbucksProductBuilder.BuilderStarbucksProduct;
import BackOffice.CoffeShop.StarbucksProductBuilder.IDrinksStarbucks;
import BackOffice.CoffeShop.StarbucksProductBuilder.ProductStarbucks;
import BackOffice.CoffeShop.TimHortonsProductBuilder.BuilderTimHortonsProduct;
import BackOffice.CoffeShop.TimHortonsProductBuilder.IBakeryTimHortons;
import BackOffice.CoffeShop.TimHortonsProductBuilder.IDrinksTimHortons;
import BackOffice.CoffeShop.TimHortonsProductBuilder.ProductTimHortons;
import BackOffice.CoffeShop.TimbertrainProductBuilder.BuilderTimbertrainProduct;
import BackOffice.CoffeShop.TimbertrainProductBuilder.IDrinksTimbertrain;
import BackOffice.CoffeShop.TimbertrainProductBuilder.ProductTimbertrain;
import DataBase.ProductsWaiter;

import java.math.BigDecimal;

public class DrinksFactory extends AbstractFactory{


    @Override
    public ProductCoffeeCulture createCoffeeCulture(String prodName, BigDecimal price, int commission) {
        IDrinksCoffeeCulture iDrinks = new IDrinksCoffeeCulture();
        BuilderCoffeeCultureProduct builder = new BuilderCoffeeCultureProduct(iDrinks);
        builder.createProductShop(prodName,price,commission);
        ProductCoffeeCulture product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductSecondCup createSecondCup(String prodName, BigDecimal price, int commission) {
        IDrinksSecondCup iDrinks = new IDrinksSecondCup();
        BuilderSecondCupProduct builder = new BuilderSecondCupProduct(iDrinks);
        builder.createProductShop(prodName,price,commission);
        ProductSecondCup product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductStarbucks createStarbucks(String prodName, BigDecimal price, int commission) {
        IDrinksStarbucks iDrinks = new IDrinksStarbucks();
        BuilderStarbucksProduct builder = new BuilderStarbucksProduct(iDrinks);
        builder.createProductShop(prodName,price,commission);
        ProductStarbucks product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductTimbertrain createTimbertrain(String prodName, BigDecimal price, int commission) {
        IDrinksTimbertrain iDrinks = new IDrinksTimbertrain();
        BuilderTimbertrainProduct builder = new BuilderTimbertrainProduct(iDrinks);
        builder.createProductShop(prodName,price,commission);
        ProductTimbertrain product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }

    @Override
    public ProductTimHortons createTimHortons(String prodName, BigDecimal price, int commission) {
        IDrinksTimHortons iDrinks = new IDrinksTimHortons();
        BuilderTimHortonsProduct builder = new BuilderTimHortonsProduct(iDrinks);
        builder.createProductShop(prodName,price,commission);
        ProductTimHortons product = builder.getProduct();
        ProductsWaiter productsWaiter = new ProductsWaiter();
        if (productsWaiter.addFactoryBuilderProduct(product.getCategory_Id(), product.getStore_ID(),product.getProdName(),product.getPrice(),product.getComission(), product.getActive() ))
            System.out.println("Product added successfully to the database");
        return product;
    }
}
