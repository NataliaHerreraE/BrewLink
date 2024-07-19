package BackOffice.Management;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class StoresDeliveries {

    int storeId;
    int deliveryId;
    boolean active;

    private static List<StoresDeliveries> storesDeliveriesList = new ArrayList<>();

    public StoresDeliveries() {}

    public StoresDeliveries(int storeId, int deliveryId, boolean active) {
        this.storeId = storeId;
        this.deliveryId = deliveryId;
        this.active = active;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static void insert(StoresDeliveries storeDelivery) {
        storesDeliveriesList.add(storeDelivery);
    }

    public static void display() {
        for (StoresDeliveries storeDelivery : storesDeliveriesList) {
            System.out.println(storeDelivery);
        }
    }

    public static void delete(int storeId, int deliveryId) {
        Optional<StoresDeliveries> storeDeliveryToRemove = storesDeliveriesList.stream()
                .filter(storeDelivery -> storeDelivery.getStoreId() == storeId && storeDelivery.getDeliveryId() == deliveryId)
                .findFirst();

        storeDeliveryToRemove.ifPresent(storesDeliveriesList::remove);
    }

    @Override
    public String toString() {
        return "STORES_DELIVERIES{" +
                "storeId=" + storeId +
                ", deliveryId=" + deliveryId +
                ", active=" + active +
                '}';
    }





}
