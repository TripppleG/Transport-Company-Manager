package entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "fuel_shipment")
@DiscriminatorValue("fuel_shipment")
public class FuelTankShipment extends Shipment<Double> {
    @Column(name = "volume_of_shipment_un_litres", nullable = false)
    private double volumeOfShipmentInLitres;

    public FuelTankShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int volumeOfShipmentInLitres) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setVolumeOfShipmentInLitres(volumeOfShipmentInLitres);
    }

    public FuelTankShipment() {
        super();
        volumeOfShipmentInLitres = 0;
    }

    @Override
    public Double getShipmentAmount() {
        return volumeOfShipmentInLitres;
    }

    private void setVolumeOfShipmentInLitres(int volumeOfShipmentInLitres) {
        if (volumeOfShipmentInLitres < 0) {
            throw new IllegalArgumentException("The volume of the shipment cannot be less than zero!");
        }
        this.volumeOfShipmentInLitres = volumeOfShipmentInLitres;
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
        return super.toString() + "Volume of shipment: " + volumeOfShipmentInLitres + "l\nType of shipment: Fuel";
    }
}
