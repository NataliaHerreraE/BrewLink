package BackOffice.Management;

import BackOffice.DeliveryPartners.Delivery;
import DataBase.PaymentWaiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Payments {
    int paymentId;
    String payName;
    boolean active;

    // Lista estática para almacenar objetos PAYMENTS
    private static List<Payments> paymentsList = new ArrayList<>();

    public Payments(){}

    public Payments(int paymentId, String  payName, boolean active) {
        this.paymentId = paymentId;
        this.payName = payName;
        this.active = active;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Método para insertar un pago en la lista
    public static void insert(Payments payment) {
        paymentsList.add(payment);
    }

    // Método para mostrar todos los pagos
    public static void display() {
        for (Payments payment : paymentsList) {
            System.out.println(payment);
        }
    }

    // Método para eliminar un pago por paymentId
    public static void delete(int paymentId) {
        Optional<Payments> paymentToRemove = paymentsList.stream()
                .filter(payment -> payment.getPaymentId() == paymentId)
                .findFirst();

        paymentToRemove.ifPresent(paymentsList::remove);
    }

    // Método toString para mostrar la información del pago
    @Override
    public String toString() {
        return "PAYMENTS{" +
                "paymentId=" + paymentId +
                ", payName='" + payName + '\'' +
                ", active=" + active +
                '}';
    }

    public void DisplayAllPayments(){

        PaymentWaiter payments = new PaymentWaiter();


        for (var pay:payments.GetAll()
             ) {
            System.out.println(pay);
        }



    }

    public String GetPaymentsOption() {
        String opc = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your preferred Payment method");
        opc = scanner.nextLine();

        return opc;
    }



}
