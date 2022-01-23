package entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@DiscriminatorValue("client")
public class Client extends Person {
    @Column(name = "has_paid_obligations", nullable = false)
    boolean hasPaidObligations;

    public Client(String name, int age, String UCN, String phoneNumber, boolean hasPaidObligations) {
        super(name, age, UCN, phoneNumber);
        this.hasPaidObligations = hasPaidObligations;
    }

    public Client() {
        super();
        hasPaidObligations = false;
    }

    public boolean isHasPaid() {
        return hasPaidObligations;
    }

    @Override
    public String toString() {
        return super.toString() + "Has the client paid their obligations: " + (hasPaidObligations ? "Yes" : "No");
    }

}