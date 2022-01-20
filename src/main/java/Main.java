//import dao.DriverDAO;
//import dao.VehicleDAO;
import Enums.DriverQualification;
import dao.DriverDAO;
import entity.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        //Shipment a = new PeopleShipment("dsdas", "dsadas", LocalDate.now(), LocalDate.of(2032, 12, 12), 12.53, 10);
//
//        Vehicle b = new Vehicle("Opel", "Vectra123", VehicleType.BUS, "C3223BA");
//        //Vehicle d = new Vehicle("Opel", "Vectra1", VehicleType.BUS, "CA3223BA");
//
//        //HashSet<Vehicle> arr = new HashSet<>();
//        //arr.add(b);
//        //arr.add(d);
//        VehicleDAO.saveVehicle(b);
        Set<DriverQualification> a = new TreeSet<>();
        a.add(DriverQualification.MORE_THAN_12_PEOPLE);
        a.add(DriverQualification.HAZARD_CARGO);
        Driver p = new Driver("pesho", 27, "9411031703", "+359897838108", a);
        DriverDAO.saveDriver(p);
    }
}