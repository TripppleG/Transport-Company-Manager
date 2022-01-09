package Transport.company;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Shipment implements Comparable<Shipment> {
    private String departingAddress;
    private String arrivalAddress;
    private LocalDate departingDate;
    private LocalDate arrivalDate;
    private double shipmentPrice;

    public Shipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice) {
        setDepartingAddress(departingAddress);
        setArrivalAddress(arrivalAddress);
        setDepartingDate(departingDate);
        setArrivalDate(arrivalDate);
        setShipmentPrice(shipmentPrice);
    }

//    public int getShipmentID() {
//        return shipmentID;
//    }

    public String getDepartingAddress() {
        return departingAddress;
    }

    private void setDepartingAddress(String departingAddress) {
        this.departingAddress = departingAddress;
    }

    public String getArrivalAddress() {
        return arrivalAddress;
    }

    private void setArrivalAddress(String arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }

    public LocalDate getDepartingDate() {
        return departingDate;
    }

    private void setDepartingDate(LocalDate departingDate) {
        if (departingDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The date of departure cannot be set to before the current date!");
        }
        this.departingDate = departingDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    private void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The date of departure cannot be set to before the current date!");
        }
        if (arrivalDate.isBefore(departingDate)) {
            throw new IllegalArgumentException("The date of arrival cannot be set to before the date of departure!");
        }
        this.arrivalDate = arrivalDate;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    private void setShipmentPrice(double shipmentPrice) {
        if (shipmentPrice < 0) {
            throw new IllegalArgumentException("The price cannot be less than 0");
        }
        this.shipmentPrice = shipmentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment shipment)) return false;
        return Double.compare(shipment.shipmentPrice, shipmentPrice) == 0 && departingAddress.equals(shipment.departingAddress) && arrivalAddress.equals(shipment.arrivalAddress) && departingDate.equals(shipment.departingDate) && arrivalDate.equals(shipment.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
    }

    @Override
    public int compareTo(Shipment o) {
        return arrivalDate.compareTo(o.arrivalDate);
    }


    @Override
    public String toString() {
        return "Departing address: " + departingAddress + '\n' +
                "Arrival address: " + arrivalAddress + '\n' +
                "Departing Date: " + departingDate + '\n' +
                "Arrival date: " + arrivalDate + '\n' +
                "ShipmentPrice: " + shipmentPrice + '\n';
    }
}
