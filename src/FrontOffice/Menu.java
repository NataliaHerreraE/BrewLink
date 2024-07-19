package FrontOffice;

import BackOffice.CoffeShop.Store;

import java.util.Scanner;

public class Menu {

    public void MainMenu()
    {
        int[] usr = {0};// Variable which contains userID, if no user logged in it MUST be 0
        int deliveryID=0;// Variable to store deliveryID selected by customer
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String option;
        do
        {
            System.out.println("=======================================================================================================================");
            System.out.println("============================================== MAIN MENU ==============================================================");
            System.out.println("============================= Select your role in order to start the game =============================================");
            System.out.println("=======================================================================================================================");
            System.out.println("1. Clients                                                                                                            =");
            System.out.println("2. Coffee Shop                                                                                                        =");
            System.out.println("3. Deliveries                                                                                                         =");
            System.out.println("4. Management                                                                                                         =");
            System.out.println("(0 for Exit)                                                                                                           =");
            System.out.println("=======================================================================================================================");
            System.out.print("Option: ");
            option = myObj.nextLine();  // Read user input

            switch (option) {
                case "1":
                    //menu de clientes
                    ClientsMenu.MainMenuClient(usr);
                    break;
                case "2":
                    CoffeMenu coffeMenu = new CoffeMenu();
                    coffeMenu.DisplayMenu();
                    break;
                case "3":
                    //menu de deliveries
                    DeliveryMenu deliveryMenu = new DeliveryMenu();
                    deliveryMenu.DisplayMenu();
                    break;
                case "4":
                    ManagementMenu mgt = new ManagementMenu();
                    mgt.ManagementMenu(); // Enter Management Menu
                    break;
                    /*Scanner scanner = new Scanner(System.in);
                    int coffeeShopChoice = scanner.nextInt();
                    mgt.performOperation(coffeeShopChoice);*/
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(!(option.equals("0")));
    }
/*
    public void MenubyRole() {


        while (true) {
            System.out.println("Please select an option: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //menu de clientes
                    ClientsMenu.MainMenuClient();
                    break;
                case 2:
                    CoffeMenu coffeMenu = new CoffeMenu();
                    coffeMenu.DisplayMenu();
                    break;
                case 3:
                    //menu de deliveries
                    break;
                case 4:
                    // Call methods directly from CoffeMenu for Coffee Shops operations
                    ManagementMenu mgt = new ManagementMenu();
                    mgt.DisplayMenu(); // Display Coffee Shops menu
                    int coffeeShopChoice = scanner.nextInt();
                    mgt.performOperation(coffeeShopChoice);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            // Add logic to exit the loop or return to Main Menu as needed
        }
    }
    */


}
