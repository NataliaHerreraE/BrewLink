package BackOffice.CoffeShop.AbstractFactory;

import BackOffice.CoffeShop.CoffeeCultureProductBuilder.ProductCoffeeCulture;
import BackOffice.CoffeShop.SecondCupProductBuilder.ProductSecondCup;
import BackOffice.CoffeShop.StarbucksProductBuilder.ProductStarbucks;
import BackOffice.CoffeShop.TimHortonsProductBuilder.ProductTimHortons;
import BackOffice.CoffeShop.TimbertrainProductBuilder.ProductTimbertrain;

import java.math.BigDecimal;

public abstract class AbstractFactory {

    private static AbstractFactory drinksFactory = new DrinksFactory();
    private static AbstractFactory bakeryFactory = new BakeryFactory();

    public static AbstractFactory factory(FactoryType type){
        AbstractFactory factory = null;
        switch (type)
        {
            case Drinks:
                factory = drinksFactory;
                break;
            case Bakery:
                factory = bakeryFactory;
                break;
        }
        return factory;
    }

    public abstract ProductCoffeeCulture createCoffeeCulture(String prodName, BigDecimal price, int commission);

    public abstract ProductSecondCup createSecondCup(String prodName, BigDecimal price, int commission);

    public abstract ProductStarbucks createStarbucks(String prodName, BigDecimal price, int commission);

    public abstract ProductTimbertrain createTimbertrain(String prodName, BigDecimal price, int commission);

    public abstract ProductTimHortons createTimHortons(String prodName, BigDecimal price, int commission);



}
