//import dao.DriverDAO;
//import dao.VehicleDAO;
import Enums.DriverQualification;
import dao.DriverDAO;
import dao.GoodsShipmentDAO;
import dao.PeopleShipmentDAO;
import dao.PersonDAO;
import entity.*;

import java.util.Set;
import java.util.TreeSet;

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
        //VehicleDAO.saveVehicle(b);
//        Set<DriverQualification> a = new TreeSet<>();
//        a.add(DriverQualification.MORE_THAN_12_PEOPLE);
//        a.add(DriverQualification.HAZARD_CARGO);
//        Person p = new Driver("pesho", 27, "9411031703", "+359897838108", 15.5, a);
//        DriverDAO.saveDriver((Driver) p);
        PeopleShipment m = new PeopleShipment();
        System.out.println(m.getShipmentAmount());
//        Person a = new Driver();
//        PersonDAO.savePerson(a);
        PeopleShipmentDAO.savePeopleShipment(m);

    }
}