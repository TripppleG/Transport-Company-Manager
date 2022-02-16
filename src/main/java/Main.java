import dao.*;
import entity.*;
import enums.DriverQualification;
import enums.VehicleType;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Shipment> shipments = new TreeSet<>();

        Shipment goodsShipment = new GoodsShipment("Sofia", "Varna", LocalDate.now(), LocalDate.of(2022, 2, 19), 15.45, 3.5);
        Shipment fuelTankShipment = new FuelTankShipment("Varna", "Plovdiv", LocalDate.now(), LocalDate.of(2022, 2, 20), 5.32, 4.6);
        Shipment peopleShipment = new PeopleShipment("Varna", "Sofia", LocalDate.now(), LocalDate.of(2022, 2, 18), 5.32, 16);
        shipments.add(fuelTankShipment);
        shipments.add(goodsShipment);
        shipments.add(peopleShipment);

        Set<Client> clients = new TreeSet<>();
        Client client = new Client("Gosho", "9411031703", "+359895721658");
        clients.add(client);

        Set<Vehicle> vehicles = new TreeSet<>();
        Vehicle vehicle = new Vehicle("VW", "Sharan", VehicleType.VAN, "BH3286AP");
        vehicles.add(vehicle);

        Set<DriverQualification> qualifications = new TreeSet<>();
        qualifications.add(DriverQualification.MORE_THAN_12_PEOPLE);
        qualifications.add(DriverQualification.FUEL_TANK);
        qualifications.add(DriverQualification.HAZARD_CARGO);

        Driver driver = new Driver("Pesho", "9711173421", "+359895743822", 1500, qualifications);
        Set<Driver> drivers = new TreeSet<>();
        drivers.add(driver);

        Set<TransportCompany> transportCompanies = new TreeSet<>();
        TransportCompany transportCompany = new TransportCompany("Google AD", "2021673915", "Sofia", 100000, drivers, clients, vehicles, shipments);
        transportCompanies.add(transportCompany);
        System.out.println(transportCompany);

        ShipmentDAO.saveShipments(shipments);
        ClientDAO.saveClients(clients);
        VehicleDAO.saveVehicles(vehicles);
        DriverDAO.saveDrivers(drivers);
        TransportCompanyDAO.saveTransportCompanies(transportCompanies);

        List<Shipment> listOfShipments = new ArrayList<>();
        List<TransportCompany> listOfCmp = new ArrayList<>();
        List<Driver> listOfDr = new ArrayList<>();

        // TransportCompanyManager t = new TransportCompanyManager(transportCompanies);
        //t.addTransportCompany(transportCompany);
        //t.addTransportCompany(transportCompany2);
        //TransportCompanyDAO.saveTransportCompany(transportCompany);
        //TransportCompanyDAO.saveTransportCompany(transportCompany2);

        //TransportCompany transportCompany2 = new TransportCompany("Microsoft AD", "2032618432", "Sofia", 500000, drivers, clients, vehicles, shipments);
        //transportCompanies.add(transportCompany2);
    }
}