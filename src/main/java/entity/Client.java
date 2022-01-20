package entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client extends Person {
    // public double priceToPay;
    public Client(String name, int age, String UCN, String phoneNumber) {
        super(name, age, UCN, phoneNumber);
    }

    public Client() {

    }
}