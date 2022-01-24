package dao;

import configuration.SessionFactoryUtil;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class VehicleDAO {

    public static void saveVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }
    }

    public static void saveOrUpdateVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }
    }


    public static void deleteVehicle(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }

    public static void saveVehicles(Set<Vehicle> vehicleSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicleSet.stream().forEach(v -> session.save(v));
            transaction.commit();
        }
    }

    public static Set<Vehicle> readVehicles() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return Set.copyOf(session.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList());
        }
    }

    public static Vehicle getVehicle(String licenseNumber) {
        Vehicle vehicle;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, licenseNumber);
            transaction.commit();
        }
        return vehicle;
    }

    public static void deleteVehicles(Set<Vehicle> vehicles) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles.stream().forEach(v -> session.delete(v));
            transaction.commit();
        }
    }
}

