package entity;

import Enums.DriverQualification;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "driver")
public class Driver extends Person {

    @Column(name = "qualifications")

    private Set<DriverQualification> qualification;

    public Driver(String name, int age, String UCN, String phoneNumber, Set<DriverQualification> qualification) {
        super(name, age, UCN, phoneNumber);
        this.qualification = qualification;
    }

    public Driver(){
        qualification = new TreeSet();
    }

    public Set<DriverQualification> getQualification() {
        return qualification;
    }

    private void setQualification(Set<DriverQualification> qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        String qualifications = "";
        int counter = 0;
        for (DriverQualification dq : qualification) {
            qualifications = qualifications.concat(dq.toString());
            if (counter == qualification.size()) {
                break;
            }
            counter++;
            qualifications = qualifications.concat(", ");
        }
        return super.toString() + "\n" + "Qualifications: " + qualifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(qualification, driver.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), qualification);
    }
}
