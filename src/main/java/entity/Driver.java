package entity;

import enums.DriverQualification;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "driver")
@DiscriminatorValue("driver")
public class Driver extends Person {
    @Column(name = "salary")
    private double salary;

    @ManyToOne(targetEntity = TransportCompany.class)
    @JoinColumn(name = "working_for")
    private TransportCompany company;

    @OneToMany(mappedBy = "driver", targetEntity = Vehicle.class)
    @Column(name = "drives", nullable = false)
    private Set<Vehicle> vehicles;

    @ElementCollection(targetClass = DriverQualification.class, fetch = FetchType.EAGER)
    @JoinTable(name = "driver_qualifications", joinColumns = @JoinColumn(name = "driver_ucn"))
    @Column(name = "qualifications", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DriverQualification> qualifications;

    public Driver(String name, int age, String UCN, String phoneNumber, double salary, TransportCompany company, Set<DriverQualification> qualifications,
                  Set<Vehicle> vehicles) {
        super(name, age, UCN, phoneNumber);
        this.company = company;
        this.qualifications = qualifications;
        setSalary(salary);
        this.vehicles = vehicles;
    }

    public Driver(){
        super();
        company = new TransportCompany();
        qualifications = new TreeSet<>();
        salary = 0;
        vehicles = new TreeSet<>();
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

    public TransportCompany getWorkingFor() {
        return company;
    }

    public Set<DriverQualification> getQualifications() {
        return qualifications;
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
}