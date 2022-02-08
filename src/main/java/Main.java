import dao.*;
import entity.*;
import enums.DriverQualification;
import enums.VehicleType;
import net.bytebuddy.asm.Advice;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Shipment> shipments = new TreeSet<>();

        Shipment goodsShipment = new GoodsShipment("Sofia", "Varna", LocalDate.now(), LocalDate.of(2022, 2, 19), 15.45, 3.5);
        Shipment fuelTankShipment = new FuelTankShipment("Varna", "Plovdiv", LocalDate.now(), LocalDate.of(2022, 2, 20), 5.32, 4.6);
        Shipment peopleShipment = new PeopleShipment("Varna", "Plovdiv", LocalDate.now(), LocalDate.of(2022, 2, 17), 5.32, 16);
        shipments.add(fuelTankShipment);
        shipments.add(goodsShipment);
        shipments.add(peopleShipment);
        ShipmentDAO.saveShipments(shipments);

        Client client = new Client("Gosho", "9411031703", "+359895721658", false);
        Set<Client> clients = new TreeSet<>();
        clients.add(client);
        ClientDAO.saveClients(clients);

        Vehicle vehicle = new Vehicle("VW", "Sharan", VehicleType.VAN, "BH3286AP");
        Set<Vehicle> vehicles = new TreeSet<>();
        vehicles.add(vehicle);
        VehicleDAO.saveVehicles(vehicles);

        Set<DriverQualification> qualifications = new TreeSet<>();
        qualifications.add(DriverQualification.MORE_THAN_12_PEOPLE);
        qualifications.add(DriverQualification.FUEL_TANK);
        qualifications.add(DriverQualification.HAZARD_CARGO);

        Driver driver = new Driver("Pesho", "9711173421", "+359895743822", 1500, qualifications, vehicles);
        Set<Driver> drivers = new TreeSet<>();
        drivers.add(driver);
        DriverDAO.saveDrivers(drivers);


        TransportCompany transportCompany = new TransportCompany("Google AD", "2021673915", "Sofia", 100000, drivers, clients, vehicles, shipments);
        //TransportCompany transportCompany2 = new TransportCompany("Microsoft AD", "2032618432", "Sofia", 500000, drivers, clients, vehicles, shipments);
        Set<TransportCompany> transportCompanies = new TreeSet<>();
        transportCompanies.add(transportCompany);
        //transportCompanies.add(transportCompany2);
        TransportCompanyDAO.saveTransportCompanies(transportCompanies);
        System.out.println(ShipmentDAO.countOfAllShipments(transportCompany));

        TransportCompanyManager t = new TransportCompanyManager(transportCompanies);
        //t.addTransportCompany(transportCompany);
        //t.addTransportCompany(transportCompany2);
        //TransportCompanyDAO.saveTransportCompany(transportCompany);
        //TransportCompanyDAO.saveTransportCompany(transportCompany2);
        //TransportCompanyManagerDAO.saveTransportCompanyManager(t);

    }
}