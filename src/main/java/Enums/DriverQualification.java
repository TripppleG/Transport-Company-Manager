package Enums;

import javax.persistence.Embeddable;
import javax.persistence.Entity;


public enum DriverQualification {
    NORMAL_CARGO,
    SPECIAL_CARGO,
    HAZARD_CARGO,
    UP_TO_12_PEOPLE,
    MORE_THAN_12_PEOPLE,
    VAN,
    BUS,
    FUEL_TANK,
    TRUCK
}