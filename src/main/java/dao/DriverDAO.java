package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Set;

public class DriverDAO {

    public static void saveDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }
    }

    public static void saveOrUpdateDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(driver);
            transaction.commit();
        }
    }

    public static void deleteDriver(Driver driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver.getQualification().stream().forEach(driverQualification -> session.delete(driverQualification));
            session.delete(driver);
            transaction.commit();
        }
    }

    public static void saveDrivers(Set<Driver> driverSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driverSet.stream().forEach(d -> session.saveOrUpdate(d));
            transaction.commit();
        }
    }

    public static List<Driver> readDrivers() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT d FROM Driver d", Driver.class).getResultList();
        }
    }

    public static Driver getDriver(String ucn) {
        Driver driver;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver = session.get(Driver.class, ucn);
            transaction.commit();
        }
        return driver;
    }

    public static void deleteDrivers(Set<Driver> drivers) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            drivers.stream().forEach(d -> session.delete(d));
            transaction.commit();
        }
    }

    public static List<Driver> sortDriversBySalary(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Salary FROM Driver Salary ORDER BY Salary.salary", Driver.class).getResultList();
        }
    }


    // Sorts the drivers' qualifications by size ascending
    public static List<Driver> sortDriversByQualifications(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Qualifications FROM Driver Qualifications order by Qualifications.qualifications.size", Driver.class).getResultList();
        }
    }

    public static List<Driver> sortDriversBySalaryThenQualifications(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Salart_And_Qualifications FROM Driver Salart_And_Qualifications ORDER BY Salart_And_Qualifications.salary, Salart_And_Qualifications.qualifications.size",
                    Driver.class).getResultList();
        }
    }
}