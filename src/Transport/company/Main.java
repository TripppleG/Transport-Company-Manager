package Transport.company;

import java.time.LocalDate;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Shipment a = new PeopleShipment("dsdas", "dsadas", LocalDate.now(), LocalDate.of(2032, 12, 12), 12.53, 10);

        Vehicle b = new Vehicle("Opel", "Vectra123", VehicleType.BUS, "C3223BA");
        Vehicle d = new Vehicle("Opel", "Vectra1", VehicleType.BUS, "CA3223BA");

        HashSet<Vehicle> arr = new HashSet<>();
        arr.add(b);
        arr.add(d);

    }
}