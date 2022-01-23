package entity;

import dao.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

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

    @OneToMany
    @Column(name = "drivers")
    private Set<Driver> drivers;

    @OneToMany
    @Column(name = "clients")
    private Set<Client> clients;

    @OneToMany
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;

    @OneToMany
    @Column(name = "fuel_tank_shipments")
    private Set<FuelTankShipment> fuelTankShipments;

    @OneToMany
    @Column(name = "goods_shipments")
    private Set<GoodsShipment> goodsShipments;

    @OneToMany
    @Column(name = "people_shipments")
    private Set<PeopleShipment> peopleShipments;

    public TransportCompany(String name, String bulstat, Set<Driver> drivers, Set<Client> clients, Set<Vehicle> vehicles, Set<FuelTankShipment> fuelTankShipments,
                            Set<GoodsShipment> goodsShipments, Set<PeopleShipment> peopleShipments ) {
        setName(name);
        setBulstat(bulstat);
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

    public void addAllVehicles(){
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

}