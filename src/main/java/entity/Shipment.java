package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class Shipment<T> implements Comparable<Shipment> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipment_id")
    protected Long shipment_id;

    @Column(name = "departing_address", nullable = false)
    protected String departingAddress;

    @Column(name = "arrival_address", nullable = false)
    protected String arrivalAddress;

    @Column(name = "departing_date", nullable = false)
    protected LocalDate departingDate;

    @Column(name = "arrival_date", nullable = false)
    protected LocalDate arrivalDate;

    @Column(name = "shipment_price", nullable = false)
    protected double shipmentPrice;

    protected Shipment() {
        departingAddress = "";
        arrivalAddress = "";
        departingDate = LocalDate.of(1, 1, 1);
        arrivalDate = LocalDate.of(1, 1, 1);
    }

    public Shipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice) {
        setDepartingAddress(departingAddress);
        setArrivalAddress(arrivalAddress);
        setDepartingDate(departingDate);
        setArrivalDate(arrivalDate);
        setShipmentPrice(shipmentPrice);
    }

    public Long getShipment_id() {
        return shipment_id;
    }

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
            throw new IllegalArgumentException("The price can't be less than 0.00!");
        }
        this.shipmentPrice = shipmentPrice;
    }

    public abstract T getShipmentAmount();

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