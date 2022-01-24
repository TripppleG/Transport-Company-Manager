package entity;

import Enums.DriverQualification;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "driver")
@DiscriminatorValue("driver")
public class Driver extends Person {
    @Column(name = "salary")
    private double salary;

    @ElementCollection(targetClass = DriverQualification.class, fetch = FetchType.EAGER)
    @JoinTable(name = "driver_qualifications", joinColumns = @JoinColumn(name = "driver_ucn"))
    @Column(name = "qualifications", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DriverQualification> qualifications;

    public Driver(String name, int age, String UCN, String phoneNumber, double salary, Set<DriverQualification> qualifications) {
        super(name, age, UCN, phoneNumber);
        this.qualifications = qualifications;
        setSalary(salary);
    }

    public Driver(){
        super();
        qualifications = new TreeSet();
        salary = 0;
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