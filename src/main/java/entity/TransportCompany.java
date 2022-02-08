package entity;

import dao.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "transport_company")
public class TransportCompany implements Comparable<TransportCompany> {
    @Id
    @Column(name = "bulstat", nullable = false)
    private String bulstat;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "income", nullable = false)
    private double income;

    @OneToMany(/*mappedBy = "company",*/ targetEntity = Driver.class)
    private Set<Driver> drivers;

    @ManyToMany(targetEntity = Client.class)
    @JoinColumn(name = "clients")
    private Set<Client> clients;

    @OneToMany(targetEntity = Vehicle.class)
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;

    @ManyToOne(targetEntity = TransportCompanyManager.class)
    private TransportCompanyManager manager;

    @OneToMany(targetEntity = Shipment.class)
    private Set<Shipment> shipments;

    public TransportCompany(String name, String bulstat, String address, double income, Set<Driver> drivers, Set<Client> clients, Set<Vehicle> vehicles,
                            Set<Shipment> shipments) {
        setName(name);
        setBulstat(bulstat);
        setIncome(income);
        this.address = address;
        this.drivers = drivers;
        this.clients = clients;
        this.vehicles = vehicles;
        this.shipments = shipments;
    }

    public TransportCompany() {
        name = "";
        bulstat = "";
        address = "";
        income = 0;
        //shipments = new TreeSet<>();
        //drivers = new TreeSet<>();
        //clients = new TreeSet<>();
        //vehicles = new TreeSet<>();
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

    public TransportCompanyManager getManager() {
        return manager;
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
        printCompany += "\nDrivers:";
        for (Driver d : drivers) {
            printCompany += '\n' + d.toString();
        }
        printCompany += "\nClients:";
        for (Client c : clients) {
            printCompany = '\n' + c.toString();
        }
        printCompany += "\nGoods Shipment:";
        for (Shipment s : shipments) {
            printCompany += '\n' + s.toString();
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
        ClientDAO.saveClient(client);
    }

    public void addDriver(Driver driver) {
        DriverDAO.saveDriver(driver);
    }

    public void addVehicle(Vehicle vehicle) {
        VehicleDAO.saveVehicle(vehicle);
    }

    public void addShipment(Shipment shipment) {
        ShipmentDAO.saveShipment(shipment);
    }

    // Remove functionalities
    public void removeClient(String ucn) {
        ClientDAO.deleteClient(ClientDAO.getClient(ucn));
    }

    public void removeDriver(String ucn) {
        DriverDAO.deleteDriver(DriverDAO.getDriver(ucn));
    }

    public void removeVehicle(String licenseNumber) {
        VehicleDAO.deleteVehicle(VehicleDAO.getVehicle(licenseNumber));
    }

    public void removeShipment(long id) {
        ShipmentDAO.deleteShipment(ShipmentDAO.getShipment(id));
    }

    public void removeAllClients() {
        ClientDAO.deleteClients(this.clients);
    }

    public void removeAllDrivers() {
        DriverDAO.deleteDrivers(this.drivers);
    }

    public void removeAllVehicles() {
        VehicleDAO.deleteVehicles(this.vehicles);
    }

    // UpdateFunctionalities
    public void updateClient(String ucnOfClientToBeUpdated, Client toBeUpdatedWith) {
        for (Client c : clients) {
            if (c.getUCN().equals(ucnOfClientToBeUpdated)) {
                c = toBeUpdatedWith;
                ClientDAO.saveOrUpdateClient(ClientDAO.getClient(toBeUpdatedWith.UCN));
                return;
            }
        }
        throw new NoSuchElementException("No client with UCN " + ucnOfClientToBeUpdated + " exists!");
    }

    public void updateDriver(String ucnOfDriverToBeUpdated, Driver toBeUpdatedWith) {
        for (Driver d : drivers) {
            if (d.getUCN().equals(ucnOfDriverToBeUpdated)) {
                d = toBeUpdatedWith;
                DriverDAO.saveOrUpdateDriver(DriverDAO.getDriver(toBeUpdatedWith.UCN));
                return;
            }
        }
        throw new NoSuchElementException("No driver with UCN " + ucnOfDriverToBeUpdated + " exists!");
    }

    public void updateVehicle(String licenseNumberOfVehicleToBeUpdated, Vehicle toBeUpdatedWith) {
        for (Vehicle v : vehicles) {
            if (v.getLicenseNumber().equals(licenseNumberOfVehicleToBeUpdated)) {
                v = toBeUpdatedWith;
                VehicleDAO.saveOrUpdateVehicle(VehicleDAO.getVehicle(toBeUpdatedWith.getLicenseNumber()));
                return;
            }
        }
        throw new NoSuchElementException("No vehicle with license number " + licenseNumberOfVehicleToBeUpdated + " exists!");
    }

    public void updateShipment(long idOfShipmentToBeUpdated, Shipment toBeUpdatedWith) {
        for (Shipment s : shipments) {
            if (s.getShipmentId().equals(idOfShipmentToBeUpdated)) {
                s = toBeUpdatedWith;
                ShipmentDAO.saveOrUpdateShipment(ShipmentDAO.getShipment(toBeUpdatedWith.getShipmentId()));
                return;
            }
        }
        throw new NoSuchElementException("No shipment with id " + idOfShipmentToBeUpdated + " exists!");
    }
}