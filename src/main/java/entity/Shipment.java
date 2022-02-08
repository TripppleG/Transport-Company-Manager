package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "shipment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Shipment<T> implements Comparable<Shipment> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipment_id")
    protected long shipmentId;

    @Column(name = "departing_address"/*, nullable = false*/)
    protected String departingAddress;

    @Column(name = "arrival_address"/*, nullable = false*/)
    protected String arrivalAddress;

    @Column(name = "departing_date"/*, nullable = false*/)
    protected LocalDate departingDate;

    @Column(name = "arrival_date"/*, nullable = false*/)
    protected LocalDate arrivalDate;

    @Column(name = "shipment_price"/*, nullable = false*/)
    protected double shipmentPrice;

    @ManyToOne
    @JoinColumn(name = "client", nullable = true)
    protected Client client;

    @ManyToOne(targetEntity = Driver.class)
    @JoinColumn(name = "driver", nullable = true)
    protected Driver driver;

    @ManyToOne(targetEntity = TransportCompany.class)
    @JoinColumn(name = "company", nullable = true)
    protected TransportCompany company;

    public Shipment() {
        departingAddress = "";
        arrivalAddress = "";
        departingDate = LocalDate.of(1, 1, 1);
        arrivalDate = LocalDate.of(1, 1, 1);
        shipmentPrice = 0;
    }

    public Shipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice) {
        setDepartingAddress(departingAddress);
        setArrivalAddress(arrivalAddress);
        setDepartingDate(departingDate);
        setArrivalDate(arrivalDate);
        setShipmentPrice(shipmentPrice);
        //this.client = new Client();
        //this.driver = new Driver();
        //this.company = new TransportCompany();
    }

    public Shipment(String departingAddress, String arrivalAddress, LocalDate departingDate, LocalDate arrivalDate, double shipmentPrice, Client client, Driver driver, TransportCompany company) {
        setDepartingAddress(departingAddress);
        setArrivalAddress(arrivalAddress);
        setDepartingDate(departingDate);
        setArrivalDate(arrivalDate);
        setShipmentPrice(shipmentPrice);
        this.client = client;
        this.driver = driver;
        this.company = company;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public Client getClient() {
        return client;
    }

    public Driver getDriver() {
        return driver;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public String getDepartingAddress() {
        return departingAddress;
    }

    private void setDepartingAddress(String departingAddress) {
        this.departingAddress = departingAddress;
    }

    public String getArrivalAddress() {
        return arrivalAddress;
    }

    private void setArrivalAddress(String arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }

    public LocalDate getDepartingDate() {
        return departingDate;
    }

    private void setDepartingDate(LocalDate departingDate) {
        if (departingDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The date of departure cannot be set to before the current date!");
        }
        this.departingDate = departingDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    private void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The date of departure cannot be set to before the current date!");
        }
        if (arrivalDate.isBefore(departingDate)) {
            throw new IllegalArgumentException("The date of arrival cannot be set to before the date of departure!");
        }
        this.arrivalDate = arrivalDate;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    private void setShipmentPrice(double shipmentPrice) {
        if (shipmentPrice < 0) {
            throw new IllegalArgumentException("The price can't be less than 0.00!");
        }
        this.shipmentPrice = shipmentPrice;
    }

    public abstract T getShipmentSize();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment shipment)) return false;
        return Double.compare(shipment.shipmentPrice, shipmentPrice) == 0 && departingAddress.equals(shipment.departingAddress) && arrivalAddress.equals(shipment.arrivalAddress) && departingDate.equals(shipment.departingDate) && arrivalDate.equals(shipment.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departingAddress, arrivalAddress, departingDate, arrivalDate, shipmentPrice);
    }

    @Override
    public int compareTo(Shipment o) {
        return arrivalDate.compareTo(o.arrivalDate);
    }


    @Override
    public String toString() {
        return "Departing address: " + departingAddress + '\n' +
                "Arrival address: " + arrivalAddress + '\n' +
                "Departing Date: " + departingDate + '\n' +
                "Arrival date: " + arrivalDate + '\n' +
                "ShipmentPrice: " + shipmentPrice + '\n';
    }
}