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
    double salary;

    @ElementCollection(targetClass = DriverQualification.class)
    @JoinTable(name = "driver_qualifications", joinColumns = @JoinColumn(name = "driver_ucn"))
    @Column(name = "qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DriverQualification> qualification;

    public Driver(String name, int age, String UCN, String phoneNumber, double salary, Set<DriverQualification> qualification) {
        super(name, age, UCN, phoneNumber);
        this.qualification = qualification;
        setSalary(salary);
    }

    public Driver(){
        qualification = new TreeSet();
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
        return qualification;
    }

    private void setQualification(Set<DriverQualification> qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        String listOfQualifications = "";
        int counter = 0;
        for (DriverQualification dq : qualification) {
            listOfQualifications += dq.name();
            if (counter == qualification.size()) {
                break;
            }
            counter++;
            listOfQualifications += ", ";
        }
        return super.toString() + "\nSalary: " + salary + "\nQualifications: " + listOfQualifications;
    }
}
