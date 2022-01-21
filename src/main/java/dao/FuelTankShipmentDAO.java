package dao;

import configuration.SessionFactoryUtil;
import entity.FuelTankShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FuelTankShipmentDAO {
    public static void saveFuelTankShipment(FuelTankShipment fuelTankShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(fuelTankShipment);
            transaction.commit();
        }
    }

    public static void saveFuelTankShipments(List<FuelTankShipment> fuelTankShipmentList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            fuelTankShipmentList.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<FuelTankShipment> readFuelTankShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM FuelTankShipment a", FuelTankShipment.class).getResultList();
        }
    }
}
