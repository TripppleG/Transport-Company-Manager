package Transport.company;

import java.time.LocalDate;
import java.util.Objects;

public class FuelTankShipment extends Shipment {
    double sizeInLitres;

    FuelTankShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int sizeInLitres) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setSizeInLitres(sizeInLitres);
    }

    private void setSizeInLitres(int sizeInLitres) {
        if (sizeInLitres < 0) {
            throw new IllegalArgumentException("The volume of the shipment cannot be less than zero!");
        }
        this.sizeInLitres = sizeInLitres;
    }

    public double getSizeOfShipment() {
        return sizeInLitres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuelTankShipment that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(that.sizeInLitres, sizeInLitres) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sizeInLitres);
    }

    @Override
    public String toString() {
        return super.toString() + "Volume of shipment: " + sizeInLitres + "l\n" + "Type of shipment: Fuel";
    }
}
