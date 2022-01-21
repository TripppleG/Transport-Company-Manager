package entity;

import Enums.DriverQualification;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "driver")
public class Driver extends Person {
    @Column(name = "salary")
    double salary;

    @Column(name = "qualifications")
    @ElementCollection
    private Set<DriverQualification> qualification;

    public Driver(String name, int age, String UCN, String phoneNumber, double salary, Set<DriverQualification> qualification) {
        super(name, age, UCN, phoneNumber);
        this.qualification = qualification;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        if (!super.equals(o)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
