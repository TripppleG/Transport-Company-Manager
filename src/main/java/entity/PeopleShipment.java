package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "people_transport")
@DiscriminatorValue("people_transport")
public class PeopleShipment extends Shipment<Integer> {
    @Column(name = "number_of_people", nullable = false)
    private int numberOfPeople;

    @ManyToMany
    @Column(name = "clients")
    private Set<Client> clients;

    @ManyToOne(targetEntity = TransportCompany.class)
    private TransportCompany company;

    public PeopleShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int numberOfPeople,
                          TransportCompany company, Set<Client> clients) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setNumberOfPeople(numberOfPeople);
        this.company = company;
        this.clients = clients;
    }

    public PeopleShipment() {
        super();
        numberOfPeople = 0;
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
    public Integer getShipmentSize() {
        return numberOfPeople;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 1) {
            throw new IllegalArgumentException("The number of people cannot be less than one!");
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleShipment that)) return false;
        if (!super.equals(o)) return false;
        return numberOfPeople == that.numberOfPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPeople);
    }

    @Override
    public String toString() {
        return super.toString() + "Number of people: " + numberOfPeople + "\nType of shipment: People transport";
    }
}
