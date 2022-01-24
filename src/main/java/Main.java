import Enums.DriverQualification;
import Enums.VehicleType;
import dao.DriverDAO;
import dao.GoodsShipmentDAO;
import dao.PeopleShipmentDAO;
import dao.VehicleDAO;
import entity.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
//      Shipment a = new PeopleShipment("dsdas", "dsadas", LocalDate.now(), LocalDate.of(2032, 12, 12), 12.53, 10);
//      Vehicle b = new Vehicle("Mercedes", "C-class", VehicleType.BUS, "C3223BA");
//      Vehicle d = new Vehicle("Opel", "Vectra1", VehicleType.BUS, "CA3223BA");
//
//      HashSet<Vehicle> arr = new HashSet<>();
//      arr.add(b);
//      arr.add(d)
//      VehicleDAO.saveVehicle(b);
        Set<DriverQualification> a = new TreeSet<>();
        a.add(DriverQualification.MORE_THAN_12_PEOPLE);
        a.add(DriverQualification.HAZARD_CARGO);
        Driver pesho = new Driver("Pesho", 22, "6110118823", "+359897838108", 900.25, a);
        Driver gosho = new Driver("Gosho", 19, "9411031703", "+359897838107", 900.27, a);
        Driver misho = new Driver("Misho", 22, "9904034395", "+359897838109", 900.21, a);
        Set<Driver> b = new TreeSet<>();
        b.add(pesho);
        b.add(gosho);
        b.add(misho);
        DriverDAO.saveDrivers(b);
    }
}