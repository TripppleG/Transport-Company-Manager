package entity;

import enums.DriverQualification;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "client")
@DiscriminatorValue("client")
public class Client extends Person {
    @Column(name = "has_paid_obligations", nullable = false)
    private boolean hasPaidObligations;

    @ManyToMany(mappedBy = "clients", targetEntity = TransportCompany.class)
    @Column(name = "client_of", nullable = false)
    private Set<TransportCompany> clientOf;

    @ManyToMany(mappedBy = "clients", targetEntity = FuelTankShipment.class)
    @Column(name = "fuel_tank_shipments")
    private Set<FuelTankShipment> fuelTankShipments;

    @ManyToMany(mappedBy = "clients", targetEntity = GoodsShipment.class)
    @Column(name = "goods_shipments")
    private Set<GoodsShipment> goodsShipments;

    @ManyToMany(mappedBy = "clients", targetEntity = PeopleShipment.class)
    @Column(name = "people_shipments")
    private Set<PeopleShipment> peopleShipments;

    @ManyToMany(targetEntity = TransportCompany.class)
    private Map<TransportCompany, Double> obligations;

    public Client(String name, int age, String UCN, String phoneNumber, boolean hasPaidObligations, Set<TransportCompany> clientOf,
                  Set<FuelTankShipment> fuelTankShipments, Set<GoodsShipment> goodsShipments, Set<PeopleShipment> peopleShipments) {
        super(name, age, UCN, phoneNumber);
        this.hasPaidObligations = hasPaidObligations;
        this.clientOf = clientOf;
        this.fuelTankShipments = new TreeSet<>();
        this.goodsShipments = new TreeSet<>();
        this.peopleShipments = new TreeSet<>();
    }

    public Client() {
        super();
        hasPaidObligations = false;
        clientOf = new TreeSet<>();
        fuelTankShipments = new TreeSet<>();
        goodsShipments = new TreeSet<>();
        peopleShipments = new TreeSet<>();
    }

    void payingObligations(TransportCompany transportCompany){
        obligations.replace(transportCompany, 0.0);
        if (obligations.values().stream().allMatch(v -> v.equals(0.0))) {
            hasPaidObligations = true;
        }
    }

    public Set<FuelTankShipment> getFuelTankShipments() {
        return fuelTankShipments;
    }

    public Set<GoodsShipment> getGoodsShipments() {
        return goodsShipments;
    }

    public Set<PeopleShipment> getPeopleShipments() {
        return peopleShipments;
    }

    public boolean getHasPaidObligations() {
        return hasPaidObligations;
    }

    public Set<TransportCompany> getClientOf() {
        return clientOf;
    }

    @Override
    public String toString() {
        String clientOfString = "Client of: ";
        int counter = 1;
        for (TransportCompany tmp : clientOf) {
            clientOfString += tmp.getName();
            if (counter == clientOf.size()) {
                break;
            }
            counter++;
            clientOfString += ", ";
        }
        return super.toString() + "Type: Client\nHas the client paid their obligations: " + (hasPaidObligations ? "Yes" : "No") + '\n' + clientOfString + '\n';
    }
}