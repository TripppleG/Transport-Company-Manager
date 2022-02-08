package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "people_transport")
public class PeopleShipment extends Shipment<Integer> {
    @Column(name = "number_of_people", nullable = false)
    private int numberOfPeople;

    public PeopleShipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, int numberOfPeople) {
        super(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
        setNumberOfPeople(numberOfPeople);
    }

    public PeopleShipment() {
        super();
        numberOfPeople = 0;
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
