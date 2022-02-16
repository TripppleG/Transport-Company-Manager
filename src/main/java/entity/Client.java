package entity;

import javax.persistence.*;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table(name = "client")
@DiscriminatorValue("client")
public class Client extends Person {
    @Column(name = "has_paid_obligations", nullable = true)
    private boolean hasPaidObligations;

    @Column(name = "obligation_amount")
    public int obligationAmount;

    @ManyToMany(mappedBy = "clients", targetEntity = TransportCompany.class)
    private Set<TransportCompany> companies;

    @OneToMany(mappedBy = "client", targetEntity = Shipment.class, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;

    public Client(String name, String UCN, String phoneNumber) {
        super(name, UCN, phoneNumber);
        obligationAmount = 0;
    }

    public Client(String name, String UCN, String phoneNumber, Set<TransportCompany> companies) {
        super(name, UCN, phoneNumber);
        setObligationAmount();
        this.companies = companies;
    }

    public Client(String name, String UCN, Set<Shipment> shipments, String phoneNumber) {
        super(name, UCN, phoneNumber);
        this.shipments = shipments;
        setObligationAmount();
    }


    public Client(String name, String UCN, String phoneNumber, Set<TransportCompany> companies, Set<Shipment> shipments) {
        super(name, UCN, phoneNumber);
        setObligationAmount();
        this.companies = companies;
        this.shipments = shipments;
    }


    public Client() {
        super();
        hasPaidObligations = false;
    }

    public int getObligationAmount() {
        return obligationAmount;
    }

    public void setObligationAmount() {
        obligationAmount = 0;
        shipments.stream().forEach(shipment -> obligationAmount += shipment.getShipmentPrice());
    }

    public boolean isHasPaidObligations() {
        return hasPaidObligations;
    }

    public void setHasPaidObligations(boolean hasPaidObligations) {
        this.hasPaidObligations = hasPaidObligations;
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
        //String clientOfString = "Client of: ";
        // int counter = 1;
/*        for (TransportCompany tmp : companies) {
            clientOfString += tmp.getName();
            if (counter == companies.size()) {
                break;
            }
            counter++;
            clientOfString += ", ";
        }*/
        return super.toString(); /*+ "Type: Client\nHas the client paid their obligations: " + (hasPaidObligations ? "Yes" : "No") + '\n' + clientOfString + '\n';*/
    }

    void payShipment(Shipment shipment) {
        if (shipments.isEmpty()) {
            throw new EmptyStackException();
        }
        if (!shipments.contains(shipment)) {
            throw new NoSuchElementException("There is no such shipment");
        }

        shipments.remove(shipment);


    }

    void payShipment(long shipmentID) {
        for (Shipment s: shipments) {
            if (s.getShipmentId().equals(shipmentID)) {
                obligationAmount -= s.getShipmentPrice();
                if (obligationAmount == 0.0) {
                    hasPaidObligations = true;
                }
                shipments.remove(s);
                return;
            }
        }
        throw new NoSuchElementException("No shipment with ID " + shipmentID + " exists!");
    }
}