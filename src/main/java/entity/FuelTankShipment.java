package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;


public class FuelTankShipment extends Shipment {
    @Column(name = "", nullable = false)
    private int volumeOfShipmentInLitres;

    FuelTankShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int volumeOfShipmentInLitres) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setSizeInLitres(volumeOfShipmentInLitres);
    }

    private void setSizeInLitres(int volumeOfShipmentInLitres) {
        if (volumeOfShipmentInLitres < 0) {
            throw new IllegalArgumentException("The volume of the shipment cannot be less than zero!");
        }
        this.volumeOfShipmentInLitres = volumeOfShipmentInLitres;
    }

    public double getSizeOfShipment() {
        return volumeOfShipmentInLitres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuelTankShipment that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(that.volumeOfShipmentInLitres, volumeOfShipmentInLitres) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volumeOfShipmentInLitres);
    }

    @Override
    public String toString() {
        return super.toString() + "Volume of shipment: " + volumeOfShipmentInLitres + "l\n" + "Type of shipment: Fuel";
    }
}
