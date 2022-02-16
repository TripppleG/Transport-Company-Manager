package entity;

import dao.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "transport_company")
public class TransportCompany implements Comparable<TransportCompany> {
    @Id
    @Column(name = "bulstat", nullable = true)
    private String bulstat;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "income", nullable = true)
    private double income;

    @OneToMany(mappedBy = "company", targetEntity = Driver.class, cascade = CascadeType.ALL)
    private Set<Driver> drivers;

    @ManyToMany(targetEntity = Client.class)
    private Set<Client> clients;

    @OneToMany(mappedBy = "company", targetEntity = Vehicle.class, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company", targetEntity = Shipment.class, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;

    @Column(name = "number_of_completed_shipments")
    private int numberOfCompletedShipments;

    public TransportCompany(String name, String bulstat, String address, double income, Set<Driver> drivers, Set<Client> clients, Set<Vehicle> vehicles, Set<Shipment> shipments) {
        setName(name);
        setBulstat(bulstat);
        setIncome(income);
        this.address = address;
        this.drivers = drivers;
        this.clients = clients;
        this.vehicles = vehicles;
        this.shipments = shipments;
        numberOfCompletedShipments = 0;
    }

    public TransportCompany(String name, String bulstat, String address, double income) {
        setName(name);
        setBulstat(bulstat);
        this.address = address;
        setIncome(income);
        numberOfCompletedShipments = 0;
    }

    public TransportCompany() {
        name = "";
        bulstat = "";
        address = "";
        income = 0;
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
        String bulstatRegex = "[0-9]{9,10}";
        if (!bulstat.matches(bulstatRegex)) {
            throw new IllegalArgumentException("Invalid bulstat number!");
        }
        this.bulstat = bulstat;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        if (income < 0) {
            throw new IllegalArgumentException("The income can't be less than zero!");
        }
        this.income = income;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Shipment> getShipments() {
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
        printCompany += "Name: " + name;
        printCompany += "\nBulstat: " + bulstat;
        printCompany += "\nIncome: " + income;
        printCompany += "\n\nDrivers:";
        for (Driver d : drivers) {
            printCompany += '\n' + d.toString();
        }
        printCompany += "\nClients:";
        for (Client c : clients) {
            printCompany = '\n' + c.toString() + '\n';
        }
        printCompany += "\nShipments:";
        for (Shipment s : shipments) {
            printCompany += '\n' + s.toString() + '\n';
        }
        printCompany += "\n\n";
        return printCompany;
    }

    @Override
    public int compareTo(TransportCompany o) {
        return name.compareTo(o.name);
    }

    // Add functionalities
    public void addClient(Client client) {
        clients.add(client);
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void addShipment(Shipment shipment) {
        shipments.add(shipment);
    }

    // Remove functionalities
    public void removeClient(String ucn) {
        for (Client client : clients) {
            if (client.getUCN().equals(ucn)) {
                clients.remove(client);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No client with UCN " + ucn + "exists!");
    }

    public void removeClient(Client client){
        clients.remove(client);
    }

    public void removeDriver(String ucn) {
        for (Driver driver : drivers) {
            if (driver.getUCN().equals(ucn)) {
                drivers.remove(driver);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No driver with UCN " + ucn + "exists!");
    }


    public void removeDriver(Driver driver){
        drivers.remove(driver);
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void removeVehicle(String licenseNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicenseNumber().equals(licenseNumber)) {
                vehicles.remove(vehicle);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No vehicle with license number " + licenseNumber + " exists!");
    }

    public void removeShipment(long id) {
        for (Shipment shipment : shipments) {
            if (shipment.getShipmentId().equals(id)) {
                shipments.remove(shipment);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No shipment with ID " + id + " exists");
    }

    public void removeAllClients() {
        clients.clear();
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void removeAllDrivers() {
        drivers.clear();
        TransportCompanyDAO.saveTransportCompany(this);
    }

    public void removeAllVehicles() {
        vehicles.clear();
        TransportCompanyDAO.saveTransportCompany(this);
    }

    // UpdateFunctionalities
    public void updateClient(String ucnOfClientToBeUpdated, Client toBeUpdatedWith) {
        for (Client c : clients) {
            if (c.getUCN().equals(ucnOfClientToBeUpdated)) {
                c = toBeUpdatedWith;
                ClientDAO.saveOrUpdateClient(c);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No client with UCN " + ucnOfClientToBeUpdated + " exists!");
    }

    public void updateDriver(String ucnOfDriverToBeUpdated, Driver toBeUpdatedWith) {
        for (Driver d : drivers) {
            if (d.getUCN().equals(ucnOfDriverToBeUpdated)) {
                d = toBeUpdatedWith;
                DriverDAO.saveOrUpdateDriver(d);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No driver with UCN " + ucnOfDriverToBeUpdated + " exists!");
    }

    public void updateVehicle(String licenseNumberOfVehicleToBeUpdated, Vehicle toBeUpdatedWith) {
        for (Vehicle v : vehicles) {
            if (v.getLicenseNumber().equals(licenseNumberOfVehicleToBeUpdated)) {
                v = toBeUpdatedWith;
                VehicleDAO.saveOrUpdateVehicle(v);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No vehicle with license number " + licenseNumberOfVehicleToBeUpdated + " exists!");
    }

    public void updateShipment(long idOfShipmentToBeUpdated, Shipment toBeUpdatedWith) {
        for (Shipment s : shipments) {
            if (s.getShipmentId().equals(idOfShipmentToBeUpdated)) {
                s = toBeUpdatedWith;
                ShipmentDAO.saveOrUpdateShipment(s);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No shipment with id " + idOfShipmentToBeUpdated + " exists!");
    }

    public void completeShipment(long shipmentID){
        for (Shipment s: shipments) {
            if (s.getShipmentId().equals(shipmentID)) {
                income += s.getShipmentPrice();
                numberOfCompletedShipments++;
                shipments.remove(s);
                TransportCompanyDAO.saveTransportCompany(this);
                return;
            }
        }
        throw new NoSuchElementException("No shipment with ID " + shipmentID + " exists!");
    }
}