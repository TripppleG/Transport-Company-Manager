package Transport.company;

import java.util.HashSet;
import java.util.Objects;

public class TransportCompany implements Comparable<TransportCompany> {

    String name;
    String address;
    String bulstat;
    HashSet<Driver> drivers;
    HashSet<Client> clients;
    HashSet<Vehicle> vehicles;
    HashSet<Shipment> shipments;

    public TransportCompany(String name, String bulstat, HashSet<Driver> drivers, HashSet<Client> clients, HashSet<Vehicle> vehicles, HashSet<Shipment> shipments) {
        setName(name);
        setBulstat(bulstat);
        this.drivers = drivers;
        this.clients = clients;
        this.vehicles = vehicles;
        this.shipments = shipments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new NullPointerException("Address is null!");
        }
        this.address = address;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        String nameRegex = "([A-Za-z0-9]+ )+(ET|OOD|EOOD|EAD|AD)$";
        if (!name.matches(nameRegex)) {
            throw new IllegalArgumentException("Invalid company name!");
        }
        this.name = name;
    }

    public String getBulstat() {
        return bulstat;
    }

    private void setBulstat(String bulstat) {
        String bulstatRegex = "[0-9]{10}";
        if (!bulstat.matches(bulstatRegex)) {
            throw new IllegalArgumentException("Invalid bulstat number!");
        }
        this.bulstat = bulstat;
    }

    public HashSet<Driver> getDrivers() {
        return drivers;
    }

    public HashSet<Client> getClients() {
        return clients;
    }

    public HashSet<Vehicle> getVehicles() {
        return vehicles;
    }

    public HashSet<Shipment> getShipments() {
        return shipments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportCompany that)) return false;
        return bulstat.equals(that.bulstat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bulstat);
    }

    @Override
    public String toString() {
        String printCompany = "";
        printCompany = printCompany.concat("Name: " + name + '\n');
        printCompany = printCompany.concat("Bulstat: " + bulstat + '\n');
        printCompany = printCompany.concat("Drivers:\n");
        for (Driver d : drivers) {

            printCompany = printCompany.concat('\n' + d.toString());
        }
        for (Client c : clients) {
            printCompany = printCompany.concat('\n' + c.toString());
        }
        for (Shipment s : shipments) {
            printCompany = printCompany.concat('\n' + s.toString());
        }
        printCompany = printCompany.concat("\n\n");
        return printCompany;
    }

    @Override
    public int compareTo(TransportCompany o) {
        return name.compareTo(o.name);
    }
}
