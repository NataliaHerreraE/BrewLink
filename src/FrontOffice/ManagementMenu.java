package FrontOffice;

import BackOffice.CoffeShop.AbstractFactory.AbstractFactory;
import BackOffice.CoffeShop.AbstractFactory.FactoryType;
import BackOffice.CoffeShop.AbstractFactory.ProductType;
import BackOffice.CoffeShop.Category;
import BackOffice.CoffeShop.CoffeeCultureProductBuilder.ProductCoffeeCulture;
import BackOffice.CoffeShop.Product;
import BackOffice.CoffeShop.SecondCupProductBuilder.ProductSecondCup;
import BackOffice.CoffeShop.StarbucksProductBuilder.ProductStarbucks;
import BackOffice.CoffeShop.Store;
import BackOffice.CoffeShop.TimHortonsProductBuilder.ProductTimHortons;
import BackOffice.CoffeShop.TimbertrainProductBuilder.ProductTimbertrain;
import DataBase.ProductsWaiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import BackOffice.Management.Commissions;
public class ManagementMenu {


    public void DisplayMenu()
    {
        // Store partner = new Store();
        //partner.DisplayAll();

        // Category category = new Category();
        //category.DisplayAllCategories();

        //Product product = new Product();
        //product.DisplayAllProducts();

        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║        ManagementMenu         ║");
        System.out.println("╠═══════════════════════════════╣");
        System.out.println("║ 1. Display all products       ║");
        System.out.println("║ 2. Display all categories     ║");
        System.out.println("║ 3. Display all stores         ║");
        System.out.println("║ 4. Display specific product   ║");
        System.out.println("║ 5. Display specific category  ║");
        System.out.println("║ 6. Display specific store     ║");
        System.out.println("║ 7. Add a new product          ║");
        System.out.println("║ 8. Update an existing product ║");
        System.out.println("║ 9. Delete a product           ║");
        System.out.println("║ 10.Display Commissions        ║");
        System.out.println("║ 0. Exit                       ║");
        System.out.println("╚═══════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    public void ManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            DisplayMenu();
            choice = scanner.nextInt();
            performOperation(choice);
        } while (choice != 0);

        // When exiting Management Menu, return to the Main Menu
        Menu mainMenu = new Menu();
        mainMenu.MainMenu();
    }

    public void performOperation(int choice) {
        Product product = new Product();
        switch (choice) {
            case 1:
                new Product().DisplayAllProducts();
                break;
            case 2:
                new Category().DisplayAllCategories();
                break;
            case 3:
                new Store().DisplayAll();
                break;
            case 4:
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the product name to search: ");
                String searchParam = scanner.nextLine();
                List<Product> specificProduct = product.GetAllProduct(searchParam);
                // Display the specific product or perform actions based on the retrieved data
                for (Product p : specificProduct) {
                    System.out.println("Product ID: " + p.getProduct_Id());
                    System.out.println("Category ID: " + p.getCategory_Id());
                    System.out.println("Product Name: " + p.getProdName());
                    System.out.println("Price: " + p.getPrice());
                    System.out.println("Commission: " + p.getComission());
                    System.out.println("Active: " + p.getActive());
                    // Display other product details as needed
                    System.out.println("-----------------------");
                }
                break;
            case 5:
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Enter the category name to search: ");
                String searchParam2 = scanner2.nextLine();
                List<Category> specificCategory = new Category().GetAllCategories(searchParam2);
                // Display the specific category or perform actions based on the retrieved data
                for (Category c : specificCategory) {
                    System.out.println("Category ID: " + c.getCategory_Id());
                    System.out.println("Store ID: " + c.getStore_ID());
                    System.out.println("Category Name: " + c.getCatName());
                    // Display other category details as needed
                    System.out.println("-----------------------");
                }
                break;
            case 6:
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Enter the store name to search: ");
                String searchParam3 = scanner3.nextLine();

                // Declaring specificStore to store the retrieved store data
                List<Store> stores = new Store().GetAll(searchParam3);
                for (Store store : stores) {
                    System.out.println("Store ID: " + store.getStore_ID());
                    System.out.println("Store Name: " + store.getStore_name());
                }
                // Display other store details as needed
                break;

            case 7:
                productForm();
                break;

            case 8:
                updateProduct();
                break;

            case 9:
                deleteProduct();
                break;
            case 10:
                Commissions.DisplaySummary();
                break;
            case 0:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    private void productForm()
    {
        try
        {
            Scanner addScanner = new Scanner(System.in);
            AbstractFactory factory=null;
            ProductType productType = null;

            boolean input = false;
            System.out.println("Welcome --- Create a new product");
            //Select a valid Factory
            do{
                System.out.println("Please choose a factory type:");
                for (FactoryType type_factory : FactoryType.values()) {
                    System.out.println(type_factory);
                }

                System.out.println("Enter your factory:");
                String fac = addScanner.nextLine();

                for (FactoryType type_factory : FactoryType.values()) {
                    if (fac.toUpperCase().equals(type_factory.toString().toUpperCase()))
                    {
                        factory = AbstractFactory.factory(type_factory);
                        input =true;
                        break;
                    }
                }
                if (!input)
                    System.out.println("Please type a valid Factory.");
            }while (!input);

            //Select a valid Builder
            input = false;
            do{
                System.out.println("Please choose a Product type:");
                for (ProductType type : ProductType.values()) {
                    System.out.println(type);
                }
                System.out.println("Enter your product type:");
                String prod = addScanner.nextLine();


                for (ProductType type : ProductType.values()) {
                    if (prod.toUpperCase().equals(type.toString().toUpperCase()))
                    {
                        productType = type;
                        input =true;
                        break;
                    }
                }
                if (!input)
                    System.out.println("Please type a valid Product Type.");
            }while (!input);

            // Product Name
            String productName="";
            input = false;
            do{

                System.out.println("Enter Product Name: ");
                productName = addScanner.nextLine();
                if (!productName.trim().isEmpty())
                    input = true;

                if (!input)
                    System.out.println("Please type a valid Product Name");
            }while (!input);


            // Price
            BigDecimal price;
            input = false;
            do{

                System.out.println("Enter Price: ");
                price = addScanner.nextBigDecimal();
                if (Double.parseDouble(String.valueOf(price))  > 0)
                    input = true;

                if (!input)
                    System.out.println("Please type a valid Price");
            }while (!input);

            // Commission
            input = false;
            int commission;
            do{

                System.out.println("Enter Commission: ");
                commission = addScanner.nextInt();
                if (commission > 0)
                    input = true;

                if (!input)
                    System.out.println("Please type a valid Commision");
            }while (!input);


            switch (productType)
            {
                case TimHortons:
                    ProductTimHortons productTimHortons = factory.createTimHortons(productName,price, commission);
                    productTimHortons.Display();
                    break;
                case SecondCup:
                    ProductSecondCup productSecondCup = factory.createSecondCup(productName,price, commission);
                    productSecondCup.Display();
                    break;
                case Starbucks:
                    ProductStarbucks productStarbucks = factory.createStarbucks(productName,price, commission);
                    productStarbucks.Display();
                    break;
                case CoffeeCulture:
                    ProductCoffeeCulture productCoffeeCulture = factory.createCoffeeCulture(productName,price, commission);
                    productCoffeeCulture.Display();
                    break;
                case Timbertrain:
                    ProductTimbertrain productTimbertrain = factory.createTimbertrain(productName,price, commission);
                    productTimbertrain.Display();
                    break;


            }


        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to save a new product");
        }
    }

    private void updateProduct()
    {
        try
        {
            Scanner updateScanner = new Scanner(System.in);

            System.out.println("Enter Product ID to update: ");
            int productIdToUpdate = updateScanner.nextInt();
            updateScanner.nextLine(); // Consume newline

            // Fetch the product with the provided ID
            List<Product> existingProductList = new ArrayList<>();
            existingProductList.add(new Product().GetOneProduct(String.valueOf(productIdToUpdate)));
            if (!existingProductList.isEmpty()) {
                Product existingProduct = existingProductList.get(0);
                boolean input;
                String productName="";
                input = false;
                do{

                    System.out.println("(current value: " + existingProduct.getProdName() + ") - Enter new product name: ");
                    productName = updateScanner.nextLine();
                    if (!productName.trim().isEmpty()) {
                        existingProduct.setProdName(productName);
                        input = true;
                    }
                    if (!input)
                        System.out.println("Please type a valid Product Name");
                }while (!input);


                // Price
                BigDecimal price;
                input = false;
                do{

                    System.out.println("(current value: " + existingProduct.getPrice() + ") - Enter new price: ");
                    price = updateScanner.nextBigDecimal();
                    if (Double.parseDouble(String.valueOf(price))  > 0){
                        existingProduct.setPrice(price);
                        input = true;
                    }

                    if (!input)
                        System.out.println("Please type a valid Price");
                }while (!input);

                // Commission
                input = false;
                int commission;
                do{

                    System.out.println("(current value: " + existingProduct.getComission() + ") - Enter new commission: ");
                    commission = updateScanner.nextInt();
                    if (commission > 0){
                        existingProduct.setComission(commission);
                        input = true;
                    }

                    if (!input)
                        System.out.println("Please type a valid Commision");
                }while (!input);


                boolean updated = new ProductsWaiter().updateProduct(existingProduct);
                if (updated) {
                    System.out.println("Product updated successfully!");
                } else {
                    System.out.println("Failed to update the product.");
                }
            } else {
                System.out.println("Product not found!");}
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to update the product");
        }
    }

    private void deleteProduct()
    {
        try
        {
            Scanner deleteScanner = new Scanner(System.in);
            System.out.println("Enter the ID of the product to delete: ");
            int productIdToDelete = deleteScanner.nextInt();
            deleteScanner.nextLine(); // Consume newline
            boolean deleted = new Product().deleteProduct(productIdToDelete);
            if (deleted) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Failed to delete the product.");
            }
        }
        catch (Exception ex)
        {
            System.out.println("There was an error at the moment to delete the product");
        }
    }
}


