package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "fuel_shipment")
@DiscriminatorValue("fuel_shipment")
public class FuelTankShipment extends Shipment<Double> {
    @Column(name = "volume_of_shipment_in_litres", nullable = false)
    private double volumeOfShipmentInLitres;

    @ManyToMany
    @Column(name = "clients")
    private Set<Client> clients;

    @ManyToOne
    private TransportCompany company;

    public FuelTankShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, double volumeOfShipmentInLitres,
                            TransportCompany company, Set<Client> clients) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setVolumeOfShipmentInLitres(volumeOfShipmentInLitres);
        this.company = company;
        this.clients = clients;
    }

    public FuelTankShipment() {
        super();
        volumeOfShipmentInLitres = 0;
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
        return volumeOfShipmentInLitres;
    }

    private void setVolumeOfShipmentInLitres(double volumeOfShipmentInLitres) {
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
