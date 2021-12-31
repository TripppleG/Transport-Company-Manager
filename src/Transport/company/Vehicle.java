package Transport.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Vehicle {
    private String brand;
    private String model;
    private VehicleType vehicleType;
    private String licenseNumber;

    public Vehicle(String brand, String model, VehicleType vehicleType, String licenseNumber) {
        setBrand(brand);
        setModel(model);
        setVehicleType(vehicleType);
        setLicenseNumber(licenseNumber);
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        if (!brand.matches("[A-Za-z ]")) {
            throw new IllegalArgumentException("The Brand must contain only letters!");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        if (!model.matches("[A-Za-z0-9 ]")) {
            throw new IllegalArgumentException("The Brand must contain only letters!");
        }
        this.model = model;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    private void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    private void setLicenseNumber(String licenseNumber) {
        ArrayList<String> cityCodes = new ArrayList<String>(Arrays.asList("A", "B", "BH", "BP", "BT", "C", "CA", "CB", "CC", "CH", "CM",
                "CO", "CT", "E", "EB", "EH", "H", "K", "KH", "M", "OB", "P", "PA", "PB", "PK", "PP", "T", "TX", "X", "Y"));

        ArrayList<Character> validLetters = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'));

        int licenseNumberLength = licenseNumber.length();
        String startingLetters = licenseNumber.substring(0, licenseNumberLength - 6);

        if (licenseNumberLength != 5 && licenseNumberLength != 6) {
            throw new IllegalArgumentException("License number must be 5 or 6 characters long");
        }

        if (!cityCodes.contains(startingLetters)) {
            throw new IllegalArgumentException("License number's city code is invalid!");
        }

        String digitsInLicenseNumber = licenseNumber.substring(licenseNumberLength - 6, licenseNumberLength - 2);
        if (digitsInLicenseNumber.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("License number's four digits are invalid!");
        }
        if (!validLetters.contains(licenseNumber.charAt(licenseNumberLength - 2)) || !validLetters.contains(licenseNumber.charAt(licenseNumberLength - 1))) {
            throw new IllegalArgumentException("License number's last letters are invalid!");
        }
        this.licenseNumber = licenseNumber;
    }
}
