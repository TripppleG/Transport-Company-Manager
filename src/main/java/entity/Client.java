package entity;

import enums.DriverQualification;
import jdk.jfr.BooleanFlag;
import org.hibernate.type.BooleanType;

import javax.persistence.*;
import javax.print.DocFlavor;
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
    private Set<TransportCompany> companies;

    @OneToMany(targetEntity = Shipment.class)
    @JoinColumn(name = "shipments")
    private Set<Shipment> shipments;

    public Client(String name, String UCN, String phoneNumber, boolean hasPaidObligations) {
        super(name,  UCN, phoneNumber);
        this.hasPaidObligations = hasPaidObligations;
    }

    public Client(String name, String UCN, String phoneNumber, boolean hasPaidObligations, Set<TransportCompany> companies, Set<Shipment> shipments) {
        super(name, UCN, phoneNumber);
        this.hasPaidObligations = hasPaidObligations;
        this.companies = companies;
        this.shipments = shipments;
    }

    public Client() {
        super();
        hasPaidObligations = false;
        //companies = new TreeSet<>();
        //shipments = new TreeSet<>();
    }

    public Set<Shipment> getShipments() {
        return shipments;
    }

    public boolean getHasPaidObligations() {
        return hasPaidObligations;
    }

    public Set<TransportCompany> getClientOf() {
        return companies;
    }

    @Override
    public String toString() {
        String clientOfString = "Client of: ";
        int counter = 1;
        for (TransportCompany tmp : companies) {
            clientOfString += tmp.getName();
            if (counter == companies.size()) {
                break;
            }
            counter++;
            clientOfString += ", ";
        }
        return super.toString() + "Type: Client\nHas the client paid their obligations: " + (hasPaidObligations ? "Yes" : "No") + '\n' + clientOfString + '\n';
    }
}