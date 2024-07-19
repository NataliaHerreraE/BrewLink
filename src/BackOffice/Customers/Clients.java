package BackOffice.Customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Clients {
    int clientId;
    String email;
    String password;
    String firstName;
    String middleName;
    String lastName;
    String phone;

    public Clients() {}

    public Clients(int clientId, String email, String password, String firstName, String middleName, String lastName, String phone) {
        this.clientId = clientId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Lista estática para almacenar objetos CLIENT
    private static List<Clients> clientList = new ArrayList<>();

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Método para insertar un cliente en la lista
    public static void insert(Clients client) {
        clientList.add(client);
    }

    // Método para mostrar todos los clientes
    public static void display() {
        for (Clients client : clientList) {
            System.out.println(client);
        }
    }

    // Método para eliminar un cliente por clientId
    public static void delete(int clientId) {
        Optional<Clients> clientToRemove = clientList.stream()
                .filter(client -> client.getClientId() == clientId)
                .findFirst();

        clientToRemove.ifPresent(clientList::remove);
    }

    // Método toString para mostrar la información del cliente
    @Override
    public String toString() {
        return "CLIENT{" +
                "clientId=" + clientId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                '}';
    }

    public String SummarizedDisplay()
    {
        return "Client:" + this.lastName + " " + this.firstName + " " + this.middleName + ", Phone: "+ this.phone;
    }


}
