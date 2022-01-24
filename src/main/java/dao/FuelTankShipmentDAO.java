package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import entity.FuelTankShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;;
import java.util.List;
import java.util.Set;

public class FuelTankShipmentDAO {

    public static void saveFuelTankShipment(FuelTankShipment fuelTankShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(fuelTankShipment);
            transaction.commit();
        }
    }

    public static void saveOrUpdateFuelTankShipment(FuelTankShipment fuelTankShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(fuelTankShipment);
            transaction.commit();
        }
    }

    public static void deleteFuelTankShipment(FuelTankShipment fuelTankShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(fuelTankShipment);
            transaction.commit();
        }
    }

    public static void saveFuelTankShipments(Set<FuelTankShipment> fuelTankShipmentSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            fuelTankShipmentSet.stream().forEach(fts -> session.save(fts));
            transaction.commit();
        }
    }

    public static List<FuelTankShipment> readFuelTankShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT fts FROM FuelTankShipment fts", FuelTankShipment.class).getResultList();
        }
    }

    public static FuelTankShipment getFuelTankShipment(long id){
        FuelTankShipment fuelTankShipment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            fuelTankShipment = session.get(FuelTankShipment.class, id);
            transaction.commit();
        }
        return fuelTankShipment;
    }


    public static void deleteFuelTankShipments(Set<FuelTankShipment> fuelTankShipments) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            fuelTankShipments.stream().forEach(fts -> session.delete(fts));
            transaction.commit();
        }
    }

    public static List<FuelTankShipment> sortFuelTankShipmentByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Arrival_Date FROM FuelTankShipment Arrival_Date ORDER BY Arrival_Date.arrivalDate", FuelTankShipment.class).getResultList();
        }
    }

}

