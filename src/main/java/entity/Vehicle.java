package entity;

import enums.VehicleType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Comparable<Vehicle> {
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(targetEntity = Driver.class, fetch = FetchType.LAZY)
    private Driver driver;

    @Id
    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @ManyToOne(targetEntity = TransportCompany.class, fetch = FetchType.LAZY)
    private TransportCompany company;

    public Vehicle(String brand, String model, VehicleType vehicleType, String licenseNumber) {
        setBrand(brand);
        setModel(model);
        setVehicleType(vehicleType);
        setLicenseNumber(licenseNumber);
    }

    public Vehicle() {
        brand = "";
        model = "";
        vehicleType = VehicleType.NULL;
        licenseNumber = "";
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(@NotNull String brand) {
        String brandRegex = "[A-Za-z ]+";
        if (!brand.matches(brandRegex)) {
            throw new IllegalArgumentException("The brand must contain only letters!");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    private void setModel(@NotNull String model) {
        String modelRegex = "[A-Za-z0-9 -]+";
        if (!model.matches(modelRegex)) {
            throw new IllegalArgumentException("The model must contain only letters and digits!");
        }
        this.model = model;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    private void setVehicleType(@NotNull VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    private void setLicenseNumber(@NotNull String licenseNumber) {
        ArrayList<String> cityCodes = new ArrayList<>(Arrays.asList("A", "B", "BH", "BP", "BT", "C", "CA", "CB", "CC", "CH", "CM",
                "CO", "CT", "E", "EB", "EH", "H", "K", "KH", "M", "OB", "P", "PA", "PB", "PK", "PP", "T", "TX", "X", "Y"));

        ArrayList<Character> validEndingLetters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'));

        int licenseNumberLength = licenseNumber.length();
        String cityCodeLetters = licenseNumber.substring(0, licenseNumberLength - 6);

        if (licenseNumberLength != 7 && licenseNumberLength != 8) {
            throw new IllegalArgumentException("License number must be 7 or 8 characters long");
        }

        if (!cityCodes.contains(cityCodeLetters)) {
            throw new IllegalArgumentException("License number's city code is invalid!");
        }

        String digitsInLicenseNumber = licenseNumber.substring(licenseNumberLength - 6, licenseNumberLength - 2);
        String digitsOfLicenceNumberRegex = "[0-9]{4}";

        if (!digitsInLicenseNumber.matches(digitsOfLicenceNumberRegex)) {
            throw new IllegalArgumentException("License number's four digits are invalid!");
        }

        char preLastLetter = licenseNumber.charAt(licenseNumberLength - 2);
        char lastLetter = licenseNumber.charAt(licenseNumberLength - 1);
        if (!validEndingLetters.contains(preLastLetter) || !validEndingLetters.contains(lastLetter)) {
            throw new IllegalArgumentException("License number's last letters are invalid!");
        }
        this.licenseNumber = licenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return licenseNumber.equals(vehicle.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }

    @Override
    public String toString() {
        return  "Brand: " + brand + '\n' +
                "Model: " + model + '\n' +
                "entity.Vehicle type: " + vehicleType + '\n' +
                "License number : " + licenseNumber;
    }

    @Override
    public int compareTo(@NotNull Vehicle o) {
        return brand.compareTo(o.brand) != 0 ? brand.compareTo(o.brand) : model.compareTo(o.model);
    }

    public static Comparator<Vehicle> compareByLicenseNumber = new Comparator<>() {
        @Contract(pure = true)
        @Override
        public int compare(@NotNull Vehicle o1, @NotNull Vehicle o2) {
            return o1.licenseNumber.compareTo(o2.licenseNumber);
        }
    };
}
