package Transport.company;

import java.util.Objects;
import java.util.TreeSet;

public class Driver extends Person {
    TreeSet<DriverQualification> qualification;

    public Driver(String name, int age, String UCN, String phoneNumber, TreeSet<DriverQualification> qualification) {
        super(name, age, UCN, phoneNumber);
        this.qualification = qualification;
    }

    public TreeSet<DriverQualification> getQualification() {
        return qualification;
    }

    private void setQualification(TreeSet<DriverQualification> qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        String qualifications = "";
        for (DriverQualification dq : qualification) {
            qualifications = qualifications.concat(dq.toString());
            if (dq == qualification.last()) {
                break;
            }
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
