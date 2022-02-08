package entity;

import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@MappedSuperclass
public abstract class Person implements Comparable<Person> {
    @Column(name = "name", nullable = false)
    protected String name;
    @Column(name = "age", nullable = false)
    protected int age;
    @Id
    @Column(name = "ucn")
    protected String UCN;
    @Column(name = "phone_number", nullable = false, unique = true)
    protected String phoneNumber;

    protected Person(String name, String UCN, String phoneNumber) {
        setName(name);
        setUCNAndAge(UCN);
        setPhoneNumber(phoneNumber);
    }

    public Person() {
        name = "";
        age = 0;
        UCN = "";
        phoneNumber = "";
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Age: " + age + '\n' +
                "UCN: " + UCN + '\n' +
                "Phone Number: " + phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return UCN.equals(person.UCN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UCN);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        final String nameRegex = "[A-Za-z ]+";
        if (!name.matches(nameRegex)) {
            throw new IllegalArgumentException("The name must contain only letters!"); // Sorry X Æ A-12
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getUCN() {
        return UCN;
    }

    private void setUCNAndAge(String UCN) {
        if (UCN.length() != 10) {
            throw new IllegalArgumentException("The length of the UCN is invalid!");
        }

        int year = Integer.parseInt(UCN.substring(0, 2));
        int month = Integer.parseInt(UCN.substring(2, 4));
        if (month >= 1 && month < 12) {
            year += 1900;
        } else if (month >= 41 && month < 52) {
            year += 2000;
            month -= 40;
        } else {
            throw new IllegalArgumentException("The month of the UCN is invalid!");
        }
        // The oldest person alive is born in 1902
        if (year < 1902 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("The year in the UCN is invalid!");
        }

        int isLeapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0) ? 1 : 0;
        int day = Integer.parseInt(UCN.substring(4, 6));
        int maxDay = 31;
        switch (month) {
            case 4, 6, 9, 11 -> maxDay -= 1;
            case 2 -> maxDay -= 3 + isLeapYear;
        }

        if (day < 1 || day > maxDay) {
            throw new IllegalArgumentException("The day in the UCN is invalid!");
        }

        // The formula for calculating the last digit of the UCN
        int lastDigit = ((UCN.charAt(0) - '0') * 2 + (UCN.charAt(1) - '0') * 4 + (UCN.charAt(2) - '0') * 8 +
                        (UCN.charAt(3) - '0') * 5 + (UCN.charAt(4) - '0') * 10 + (UCN.charAt(5) - '0') * 9 +
                        (UCN.charAt(6) - '0') * 7 + (UCN.charAt(7) - '0') * 3 + (UCN.charAt(8) - '0') * 6) % 11;
        if (lastDigit == 10) {
            lastDigit = 0;
        }

        if (lastDigit != UCN.charAt(9) - '0') {
            throw new IllegalArgumentException("The UCN is invalid!");
        }
        this.UCN = UCN;

        // Setting age based on the UCN
        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate current = LocalDate.now();
        Period period = Period.between(birthDate, current);
        age = period.getYears();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "(\\+|00)([^0])([0-9]{1,3})([0-9]{4,12})";
        if (!phoneNumber.matches(phoneNumberRegex)) {
            throw new IllegalArgumentException("Invalid phone number!");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }
}
