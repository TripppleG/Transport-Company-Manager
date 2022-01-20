package entity;

import java.time.LocalDate;
import java.util.Objects;


public class GoodsShipment extends Shipment {
    //@Column(name = "weightOfShipment", nullable = false)
    private int weightOfShipmentInKilograms;

    GoodsShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int weightOfShipmentInKilograms) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setsWightOfShipmentInKilograms(weightOfShipmentInKilograms);
    }

    private void setsWightOfShipmentInKilograms(int weightOfShipmentInKilograms) {
        if (weightOfShipmentInKilograms < 0) {
            throw new IllegalArgumentException("The wight of the shipment cannot be less than zero!");
        }
        this.weightOfShipmentInKilograms = weightOfShipmentInKilograms;
    }

    public double getSizeOfShipment() {
        return weightOfShipmentInKilograms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodsShipment that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(that.weightOfShipmentInKilograms, weightOfShipmentInKilograms) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weightOfShipmentInKilograms);
    }

    @Override
    public String toString() {
        return super.toString() + "Weight of shipment: " + weightOfShipmentInKilograms + "KG\n" + "Type of shipment: Goods";
    }
}
