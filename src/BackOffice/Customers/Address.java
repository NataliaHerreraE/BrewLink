package BackOffice.Customers;

import DataBase.AddressWaiter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static DataBase.AddressWaiter.DeleteAddressByID;
import static DataBase.AddressWaiter.updateDefaultAddressByID;

public class Address {

    int clientId;
    int addressId;
    String address;
    String city;
    String province;
    String postalCode;
    boolean defaultAddress;
    String addressName;

    // Lista estática para almacenar objetos ADDRESS
    private static List<Address> addressList = new ArrayList<>();

    public Address() {}

    public Address(int clientId, int addressId, String address, String city, String province, String postalCode, boolean defaultAddress, String addressName) {
        this.clientId = clientId;
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
        this.addressName = addressName;


    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    // Método para insertar una dirección en la lista
    public static void insert(Address address) {
        addressList.add(address);
    }

    // Método para mostrar todas las direcciones almacenadas
    public static void display(int usr) {
        String userId=String.valueOf(usr);
        for (Address address : AddressWaiter.GetAll(userId)) {
            System.out.println(address);
        }
    }

    // Método para eliminar una dirección por su addressId
    public static void delete(int addressId) {
        Optional<Address> addressToRemove = addressList.stream()
                .filter(address -> address.getAddressId() == addressId)
                .findFirst();

        addressToRemove.ifPresent(addressList::remove);
    }

    // Método toString para mostrar la información de la dirección
    @Override
    public String toString() {
        return "ADDRESS{" +
                "clientId=" + clientId +
                ", addressId=" + addressId +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", defaultAddress=" + defaultAddress +
                ", addressName='" + addressName + '\'' +
                '}';
    }
    public static void MenuAddress(int usr){
        if (usr != 0) {
            String opc = "";
            Scanner con = new Scanner(System.in);
            do {
                MenuEditAddress(usr);
                System.out.println("Option: ");
                opc = con.next();
                switch (opc) {
                    case "1":
                        // 1.- List Available Addresses
                        display(usr);
                        break;
                    case "2":
                        // 2.- Set Default Address
                        SetDefault(usr);
                        break;
                    case "3":
                        // 3.- Enter New Address
                        NewAddress(usr);
                        break;
                    case "4":
                        // 4.- Delete Address
                        DeleteAddress(usr);
                        break;
                    case "5":
                        //go back to previous Menu
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } while (!opc.equals("5"));
        }
        else {
            System.out.println("ERROR: you MUST be logged in to access to this menu...");
        }
    }
    public static void MenuEditAddress(int usr){
        //Runtime.getRuntime().exec("cls");
        if (usr != 0) {
            System.out.println("Address Menu ( Logged USER_ID: "+usr+" )");
        }else{
            System.out.println("Address Menu");
        }
        System.out.println("1.- List Available Addresses");
        System.out.println("2.- Set Default Address");
        System.out.println("3.- Enter New Address");
        System.out.println("4.- Delete Address");
        System.out.println("5.- Back");
    }
    public static void DeleteAddress(int usr){
        //Runtime.getRuntime().exec("cls");
        Scanner con = new Scanner(System.in);
        System.out.println("Select the Address to delete:");
        display(usr);
        System.out.println("id to delete:");
        String id= con.nextLine();
        DeleteAddressByID(id);
    }
    public static void SetDefault(int usr){
        //Runtime.getRuntime().exec("cls");
        String opc="";
        int curr_default=0;
        Scanner con = new Scanner(System.in);
        display(usr);
        curr_default = getCurrDefault(usr);
        System.out.println("Select the Address_Id to set as new default:");
        opc = con.nextLine();
        for (Address address : AddressWaiter.GetAll(String.valueOf(usr))) {
            if (usr==address.clientId && address.addressId==curr_default) {
                //address.defaultAddress=false;
                updateDefaultAddressByID(String.valueOf(curr_default),false);
            }
            if (usr==address.clientId && address.addressId==Integer.parseInt(opc)) {
                //address.defaultAddress=true;
                updateDefaultAddressByID(opc,true);
            }
        }
        System.out.println("Default address updated...\n List of available Addresses for Client:");
        display(usr);
    }
    public static int getCurrDefault(int usr){
        int addId=0;
        for (Address address : AddressWaiter.GetAll(String.valueOf(usr))) {
            if (usr==address.clientId && address.defaultAddress) {
                addId=address.addressId;
            }
        }
        return addId;
    }
    public static void NewAddress(int usr){
        Scanner con = new Scanner(System.in);
        System.out.println("Address Name(Alias): ");
        String adrsN= con.nextLine();
        System.out.println("Address: ");
        String ads= con.nextLine();
        System.out.println("City: ");
        String cty= con.nextLine();
        System.out.println("Province: ");
        String prvc= con.nextLine();
        System.out.println("Zip Code: ");
        String zc= con.nextLine();

        Address address = new Address(usr,1,ads,cty,prvc,zc,false,adrsN);
        try {
            AddressWaiter.InsertAddress(address);
            System.out.println("Address: "+adrsN+" Added successfully... ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String SummarizedDisplay()
    {
        return "Address Name:" + this.addressName + ", Address: "+ this.address +", City: " + this.city + ", Province: " + this.province + ", Postal Code: " + this.postalCode;
    }
}
