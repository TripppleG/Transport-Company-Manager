package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goods_shipment")
public class GoodsShipment extends Shipment<Double> {
    @Column(name = "weight_of_shipment", nullable = false)
    private double weightOfShipmentInKilograms;

    public GoodsShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, double weightOfShipmentInKilograms) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setsWightOfShipmentInKilograms(weightOfShipmentInKilograms);
    }

    public GoodsShipment() {
        super();
        weightOfShipmentInKilograms = 0.0;
    }

    @Override
    public Double getShipmentSize() {
        return weightOfShipmentInKilograms;
    }

    private void setsWightOfShipmentInKilograms(double weightOfShipmentInKilograms) {
        if (weightOfShipmentInKilograms < 0) {
            throw new IllegalArgumentException("The wight of the shipment cannot be less than zero!");
        }
        this.weightOfShipmentInKilograms = weightOfShipmentInKilograms;
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
        return super.toString() + "Weight of shipment: " + weightOfShipmentInKilograms + "KG\nType of shipment: Goods";
    }
}
