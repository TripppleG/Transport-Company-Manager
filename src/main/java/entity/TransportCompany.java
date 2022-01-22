package entity;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.lang.annotation.Inherited;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "transport_company")
public class TransportCompany implements Comparable<TransportCompany> {
    private String name;
    private String address;

    @Id
    @Column(name = "id")
    private String bulstat;
    @OneToMany
    @Column(name = "drivers")
    private Set<Driver> drivers;
    @OneToMany
    @Column(name = "clients")
    private Set<Client> clients;
    @OneToMany
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;
//    @OneToMany
//    @Column(name = "shipments")
   // private Set<Shipment> shipments;

    public TransportCompany(String name, String bulstat, Set<Driver> drivers, Set<Client> clients, Set<Vehicle> vehicles, Set<Shipment> shipments) {
        setName(name);
        setBulstat(bulstat);
        this.drivers = drivers;
        this.clients = clients;
        this.vehicles = vehicles;
        //this.shipments = shipments;
    }



    public TransportCompany() {
        name = "";
        bulstat = "";
        drivers = new TreeSet<>();
        clients = new TreeSet<>();
        vehicles = new TreeSet<>();
        //shipments = new TreeSet<>();
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

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

//    public void setShipments(Set<Shipment> shipments) {
//        this.shipments = shipments;
//    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

//    public Set<Shipment> getShipments() {
//        return shipments;
//    }

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
//        for (Shipment s : shipments) {
//            printCompany = printCompany.concat('\n' + s.toString());
//        }
        printCompany = printCompany.concat("\n\n");
        return printCompany;
    }

    @Override
    public int compareTo(TransportCompany o) {
        return name.compareTo(o.name);
    }
}
