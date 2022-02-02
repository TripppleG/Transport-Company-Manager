package entity;

import dao.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "transport_company")
public class TransportCompany implements Comparable<TransportCompany> {
    @Id
    @Column(name = "bulstat")
    private String bulstat;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "income", nullable = false)
    private double income;

    @OneToMany(mappedBy = "company", targetEntity = Driver.class)
    @Column(name = "drivers")
    private Set<Driver> drivers;

    @ManyToMany(targetEntity = Client.class)
    @Column(name = "clients")
    private Set<Client> clients;

    @OneToMany(mappedBy = "company", targetEntity = Vehicle.class)
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company", targetEntity = FuelTankShipment.class)
    @Column(name = "fuel_tank_shipments")
    private Set<FuelTankShipment> fuelTankShipments;

    @OneToMany(mappedBy = "company", targetEntity = GoodsShipment.class)
    @Column(name = "goods_shipments")
    private Set<GoodsShipment> goodsShipments;

    @OneToMany(mappedBy = "company", targetEntity = PeopleShipment.class)
    @Column(name = "people_shipments")
    private Set<PeopleShipment> peopleShipments;

    @ManyToMany(targetEntity = Client.class)
    @Column(name = "clients_obligations")
    private Map<Client, Double> clientsObligations;

    public TransportCompany(String name, String bulstat, double income, Set<Driver> drivers, Set<Client> clients, Set<Vehicle> vehicles, Set<FuelTankShipment> fuelTankShipments,
                            Set<GoodsShipment> goodsShipments, Set<PeopleShipment> peopleShipments, Map<Client, Double> clientsObligations) {
        this.clientsObligations = clientsObligations;
        setName(name);
        setBulstat(bulstat);
        setIncome(income);
        this.drivers = drivers;
        this.clients = clients;
        this.vehicles = vehicles;
        this.fuelTankShipments = fuelTankShipments;
        this.goodsShipments = goodsShipments;
        this.peopleShipments = peopleShipments;
    }

    public TransportCompany() {
        name = "";
        bulstat = "";
        income = 0;
        drivers = new TreeSet<>();
        clients = new TreeSet<>();
        vehicles = new TreeSet<>();
        fuelTankShipments = new TreeSet<>();
        goodsShipments = new TreeSet<>();
        peopleShipments = new TreeSet<>();
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

    public Set<FuelTankShipment> getFuelTankShipments() {
        return fuelTankShipments;
    }

    public Set<GoodsShipment> getGoodsShipments() {
        return goodsShipments;
    }

    public Set<PeopleShipment> getPeopleShipments() {
        return peopleShipments;
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
        printCompany +=  "\nDrivers:";
        for (Driver d : drivers) {
            printCompany += '\n' + d.toString();
        }
        printCompany +=  "\nClients:";
        for (Client c : clients) {
            printCompany = '\n' + c.toString();
        }
        printCompany +=  "\nFuel Tank Shipments:";
        for (FuelTankShipment fts : fuelTankShipments) {
            printCompany += '\n' + fts.toString();
        }
        printCompany +=  "\nGoods Shipments:";
        for (GoodsShipment gs : goodsShipments) {
            printCompany += '\n' + gs.toString();
        }
        printCompany +=  "\nPeople Shipments:";
        for (PeopleShipment ps : peopleShipments) {
            printCompany += '\n' + ps.toString();
        }
        printCompany += "\n\n";
        return printCompany;
    }

    @Override
    public int compareTo(TransportCompany o) {
        return name.compareTo(o.name);
    }

    // Add functionalities
    public void addClient(Client client){
        ClientDAO.saveClient(client);
    }

    public void addDriver(Driver driver){
        DriverDAO.saveDriver(driver);
    }

    public void addVehicle(Vehicle vehicle){
        VehicleDAO.saveVehicle(vehicle);
    }

    public void addGoodsShipment(GoodsShipment goodsShipment){
        GoodsShipmentDAO.saveGoodsShipment(goodsShipment);
    }

    public void addFuelTankShipment(FuelTankShipment fuelTankShipment) {
        FuelTankShipmentDAO.saveFuelTankShipment(fuelTankShipment);
    }

    public void addPeopleShipment(PeopleShipment peopleShipment) {
        PeopleShipmentDAO.savePeopleShipment(peopleShipment);
    }

    // Remove functionalities
    public void removeClient(String ucn){
        ClientDAO.deleteClient(ClientDAO.getClient(ucn));
    }

    public void removeDriver(String ucn){
        DriverDAO.deleteDriver(DriverDAO.getDriver(ucn));
    }

    public void removeVehicle(String licenseNumber){
        VehicleDAO.deleteVehicle(VehicleDAO.getVehicle(licenseNumber));
    }

    public void removeGoodsShipment(long id){
        GoodsShipmentDAO.deleteGoodsShipment(GoodsShipmentDAO.getGoodsShipment(id));
    }

    public void removeFuelTankShipment(long id) {
        FuelTankShipmentDAO.deleteFuelTankShipment(FuelTankShipmentDAO.getFuelTankShipment(id));
    }

    public void removePeopleShipment(long id) {
        PeopleShipmentDAO.deletePeopleShipment(PeopleShipmentDAO.getPeopleShipment(id));
    }

    public void removeAllClients(){
        ClientDAO.deleteClients(this.clients);
    }

    public void removeAllDrivers(){
        DriverDAO.deleteDrivers(this.drivers);
    }

    public void removeAllVehicles(){
        VehicleDAO.deleteVehicles(this.vehicles);
    }

    public void remoteAllGoodsShipments(){
        GoodsShipmentDAO.deleteGoodsShipments(this.goodsShipments);
    }

    public void removeAllFuelTankShipments() {
        FuelTankShipmentDAO.deleteFuelTankShipments(this.fuelTankShipments);
    }

    public void removeAllPeopleShipment() {
        PeopleShipmentDAO.deletePeopleShipments(this.peopleShipments);
    }

    // UpdateFunctionalities
    public void updateClient(String ucnOfClientToBeUpdated, Client toBeUpdatedWith){
        for(Client c: clients) {
            if (c.getUCN().equals(ucnOfClientToBeUpdated)) {
                c = toBeUpdatedWith;
                ClientDAO.saveOrUpdateClient(ClientDAO.getClient(toBeUpdatedWith.UCN));
                return;
            }
        }
        throw new NoSuchElementException("No client with UCN " + ucnOfClientToBeUpdated + " exists!");
    }

    public void updateDriver(String ucnOfDriverToBeUpdated, Driver toBeUpdatedWith){
        for(Driver d: drivers) {
            if (d.getUCN().equals(ucnOfDriverToBeUpdated)) {
                d = toBeUpdatedWith;
                DriverDAO.saveOrUpdateDriver(DriverDAO.getDriver(toBeUpdatedWith.UCN));
                return;
            }
        }
        throw new NoSuchElementException("No driver with UCN " + ucnOfDriverToBeUpdated + " exists!");
    }

    public void updateVehicle(String licenseNumberOfVehicleToBeUpdated, Vehicle toBeUpdatedWith){
        for(Vehicle v: vehicles) {
            if (v.getLicenseNumber().equals(licenseNumberOfVehicleToBeUpdated)) {
                v = toBeUpdatedWith;
                VehicleDAO.saveOrUpdateVehicle(VehicleDAO.getVehicle(toBeUpdatedWith.getLicenseNumber()));
                return;
            }
        }
        throw new NoSuchElementException("No vehicle with license number " + licenseNumberOfVehicleToBeUpdated + " exists!");
    }

    public void updateGoodsShipment(long idOfShipmentToBeUpdated, GoodsShipment toBeUpdatedWith){
        for(GoodsShipment gs: goodsShipments) {
            if (gs.getShipmentId().equals(idOfShipmentToBeUpdated)) {
                gs = toBeUpdatedWith;
                GoodsShipmentDAO.saveOrUpdateGoodsShipment(GoodsShipmentDAO.getGoodsShipment(toBeUpdatedWith.getShipmentId()));
                return;
            }
        }
        throw new NoSuchElementException("No shipment with id " + idOfShipmentToBeUpdated + " exists!");
    }

    public void updateFuelTankShipment(long idOfShipmentToBeUpdated, FuelTankShipment toBeUpdatedWith){
        for(FuelTankShipment fts: fuelTankShipments) {
            if (fts.getShipmentId().equals(idOfShipmentToBeUpdated)) {
                fts = toBeUpdatedWith;
                FuelTankShipmentDAO.saveOrUpdateFuelTankShipment(FuelTankShipmentDAO.getFuelTankShipment(toBeUpdatedWith.getShipmentId()));
                return;
            }
        }
        throw new NoSuchElementException("No shipment with id " + idOfShipmentToBeUpdated + " exists!");
    }

    public void updatePeopleShipment(long idOfShipmentToBeUpdated, PeopleShipment toBeUpdatedWith){
        for(PeopleShipment ps: peopleShipments) {
            if (ps.getShipmentId().equals(idOfShipmentToBeUpdated)) {
                ps = toBeUpdatedWith;
                PeopleShipmentDAO.saveOrUpdatePeopleShipment(PeopleShipmentDAO.getPeopleShipment(toBeUpdatedWith.getShipmentId()));
                return;
            }
        }
        throw new NoSuchElementException("No shipment with id " + idOfShipmentToBeUpdated + " exists!");
    }
}