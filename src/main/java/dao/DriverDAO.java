package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DriverDAO {
    public static void saveDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }
    }

    public static void saveDrivers(List<Driver> driverList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driverList.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<Driver> readDrivers() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Driver a", Driver.class).getResultList();
        }
    }
}
