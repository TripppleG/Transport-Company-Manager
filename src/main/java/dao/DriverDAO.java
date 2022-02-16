package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            driverSet.stream().forEach(d -> session.save(d));
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
        // SELECT * FROM driver ORDER BY salary
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
            Root<Driver> root = criteriaQuery.from(Driver.class);
            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("salary")));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

//    public static List<Driver> sortDriversByQualifications(){
//        // SELECT * FROM driver ORDER BY qualifications
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
//            Root<Driver> root = criteriaQuery.from(Driver.class);
//            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("qualifications").));
//            return session.createQuery(criteriaQuery).getResultList();
//        }
//    }

//    public static List<Driver> sortDriversByQualificationsThenSalary(){
//        // // SELECT * FROM driver ORDER BY qualifications,
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Driver> criteriaQuery = criteriaBuilder.createQuery(Driver.class);
//            Root<Driver> root = criteriaQuery.from(Driver.class);
//            //criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("qualifications")), criteriaBuilder.asc(root.get("salary")));
//            return session.createQuery(criteriaQuery).getResultList();
//        }
//    }
}