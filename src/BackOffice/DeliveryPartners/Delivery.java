package BackOffice.DeliveryPartners;

import BackOffice.CoffeShop.Store;
import BackOffice.Orders.OrderDetail;
import DataBase.CoffeShopWaiter;
import DataBase.DeliveryWaiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Delivery {

    int deliveryId;
    String delname;
    float comission;

    Delivery delivery;

    // Lista estática para almacenar objetos DELIVERIES
    private static List<Delivery> deliveriesList = new ArrayList<>();

    public Delivery(){}

    public Delivery(int deliveryId, String delname, float comission) {
        this.deliveryId = deliveryId;
        this.delname = delname;
        this.comission = comission;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDelname() {
        return delname;
    }

    public void setDelname(String delname) {
        this.delname = delname;
    }

    public float getComission() {
        return comission;
    }

    public void setComission(float comission) {
        this.comission = comission;
    }

    // Método para insertar un delivery en la lista
    public static void insert(Delivery delivery) {
        deliveriesList.add(delivery);
    }

    // Método para mostrar todos los deliveries
    public static void display() {
        for (Delivery delivery : deliveriesList) {
            System.out.println(delivery);
        }
    }

    // Método para eliminar un delivery por deliveryId
    public static void delete(int deliveryId) {
        Optional<Delivery> deliveryToRemove = deliveriesList.stream()
                .filter(delivery -> delivery.getDeliveryId() == deliveryId)
                .findFirst();

        deliveryToRemove.ifPresent(deliveriesList::remove);
    }

    // Método toString para mostrar la información del delivery
    @Override
    public String toString() {
        return "DELIVERIES{" +
             //   "deliveryId=" + deliveryId +
                ", Delivery Name='" + delname + '\'' +
                ", comission=" + comission +
                '}';
    }



    // JAvier Alcántara Changes
    public void DisplayAll()
    {
        try {
            List<Delivery> list = GetAll();
            for (Delivery delivery:list) {
                System.out.println("Delivery ID: " + delivery.getDeliveryId() + " Delivery name:" + delivery.getDelname());

            }
        } catch(Exception ex) {
            System.out.println("Error at the moment to display all the Deliveries");
        }
    }

    public List<Delivery> GetAll() {
        List<Delivery> list = new ArrayList<>();
        try {
            DeliveryWaiter waiter = new DeliveryWaiter() ;
            list = waiter.GetAll();
        } catch(Exception ex) {
            System.out.println("Error at the moment to get all the Deliveries");
        }
        return list;
    }

    public Delivery GetOne(Integer deliveryId) {
         delivery = new Delivery();
        try {
            DeliveryWaiter waiter = new DeliveryWaiter() ;
            delivery = waiter.GetOne(deliveryId);

        } catch(Exception ex) {
            System.out.println("Error at the moment to get one Delivery");
        }
        return delivery;
    }


    public Delivery GetDeliveryOption(){
       // this.delivery = new Delivery();

        String opc="";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select your preferred delivery method");
        opc=scanner.nextLine();

        //get delivery object
        delivery=GetOne(Integer.valueOf(opc));
        //set the value to the const
      //  delivery= new Delivery(delivery.getDeliveryId(),delivery.getDelname(),delivery.getComission());
        //show the description of the delivery selected
        System.out.println(delivery.toString());





        opc=ValidateDeliveryOpc(opc);


        return delivery;
    }


    public String ValidateDeliveryOpc(String opc){

        Scanner scanner = new Scanner(System.in);
        OrderDetail orderDetail = new OrderDetail();



        toString();

        System.out.println("Do you want to Select This option? Press enter to continue or 0 to return to the Delivery option");
        opc=scanner.nextLine();

        if (opc.isEmpty()){
            return opc;
        } else if (orderDetail.IsNumber(opc)) {

            if (opc.equals("0")){

                GetDeliveryOption();

            }
        }else{
            System.out.println("Sorry, You entered a wrong opc");
            GetDeliveryOption();
        }

        return "0";
    }

}
