package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "goods_shipment")
@DiscriminatorValue("goods_shipment")
public class GoodsShipment extends Shipment<Double> {
    @Column(name = "weight_of_shipment", nullable = false)
    private double weightOfShipmentInKilograms;

    @ManyToMany
    @Column(name = "clients")
    private Set<Client> clients;

    @ManyToOne
    private TransportCompany company;

    public GoodsShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, double weightOfShipmentInKilograms,
                         TransportCompany company, Set<Client> clients) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setsWightOfShipmentInKilograms(weightOfShipmentInKilograms);
        this.company = company;
        this.clients = clients;
    }

    public GoodsShipment() {
        super();
        weightOfShipmentInKilograms = 0;
        company = new TransportCompany();
        clients = new TreeSet<>();
    }

    public Set<Client> getClients() {
        return clients;
    }

    public TransportCompany getCompany() {
        return company;
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
