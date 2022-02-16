package entity;

import enums.DriverQualification;

import javax.persistence.*;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table(name = "driver")
@DiscriminatorValue("driver")
public class Driver extends Person {
    @Column(name = "salary")
    private double salary;

    @ManyToOne(targetEntity = TransportCompany.class, fetch = FetchType.LAZY)
    private TransportCompany company;

    @OneToMany(mappedBy = "driver", targetEntity = Vehicle.class, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "driver", targetEntity = Shipment.class, cascade = CascadeType.ALL)
    private Set<Shipment> shipments;

    @Column(name = "number_of_completed_shipments")
    private int numberOfCompletedShipments;

    @ElementCollection(targetClass = DriverQualification.class, fetch = FetchType.EAGER)
    @JoinTable(name = "driver_qualifications", joinColumns = @JoinColumn(name = "driver_ucn"))
    @Column(name = "qualifications", nullable = true)
    @Enumerated(EnumType.STRING)
    private Set<DriverQualification> qualifications;

    public Driver(String name, String UCN, String phoneNumber, double salary, Set<DriverQualification> qualifications) {
        super(name, UCN, phoneNumber);
        this.qualifications = qualifications;
        setSalary(salary);
        numberOfCompletedShipments = 0;
    }

    public Driver(String name, String UCN, String phoneNumber, double salary, Set<DriverQualification> qualifications, Set<Vehicle> vehicles, TransportCompany company,
                  Set<Shipment> shipments) {
        super(name, UCN, phoneNumber);
        setSalary(salary);
        this.vehicles = vehicles;
        this.qualifications = qualifications;
        this.company = company;
        this.shipments = shipments;
        numberOfCompletedShipments = 0;
    }


    public Driver(){
        super();
        salary = 0;
        numberOfCompletedShipments = 0;
    }

    public int getNumberOfCompletedShipments() {
        return numberOfCompletedShipments;
    }

    void incrementNumberOfCompletedShipments(){
        numberOfCompletedShipments++;
    }

    public double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        if (salary < 650) {
            throw new IllegalArgumentException("Salary can't be less than the minimal wage!");
        }
        this.salary = salary;
    }


    public TransportCompany getCompany() {
        return company;
    }

    public Set<DriverQualification> getQualifications() {
        return qualifications;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Shipment> getShipments() {
        return shipments;
    }

    public Set<DriverQualification> getQualification() {
        return qualifications;
    }

    private void setQualification(Set<DriverQualification> qualification) {
        this.qualifications = qualification;
    }

    @Override
    public String toString() {
        String listOfQualifications = "";
        int counter = 1;
        for (DriverQualification dq : qualifications) {
            listOfQualifications += dq.name();
            if (counter == qualifications.size()) {
                break;
            }
            counter++;
            listOfQualifications += ", ";
        }
        return super.toString() + "\nSalary: " + salary + "\nQualifications: " + listOfQualifications + '\n';
    }

    public void completeShipment(long shipmentID){
        for (Shipment s: shipments) {
            if (s.getShipmentId().equals(shipmentID)) {
                numberOfCompletedShipments++;
                shipments.remove(s);
                return;
            }
        }
        throw new NoSuchElementException("No shipment with ID " + shipmentID + " exists!");
    }
}