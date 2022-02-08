package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import entity.Shipment;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class ShipmentDAO {

    public static void saveShipment(Shipment shipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            DriverDAO.saveDriver(shipment.getDriver());
            ClientDAO.saveClient(shipment.getClient());
            session.save(shipment);
            transaction.commit();
        }
    }

    public static void saveOrUpdateShipment(Shipment shipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(shipment);
            transaction.commit();
        }
    }

    public static void deleteShipment(Shipment shipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(shipment);
            transaction.commit();
        }
    }

    public static void saveShipments(Set<Shipment> shipmentSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            shipmentSet.stream().forEach(fts -> session.save(fts));
            transaction.commit();
        }
    }

    public static List<Shipment> readShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT fts FROM Shipment fts", Shipment.class).getResultList();
        }
    }

    public static Shipment getShipment(long id){
        Shipment shipment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            shipment = session.get(Shipment.class, id);
            transaction.commit();
        }
        return shipment;
    }

    public static void deleteShipments(Set<Shipment> shipments) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            shipments.stream().forEach(fts -> session.delete(fts));
            transaction.commit();
        }
    }

    public static Long countOfAllShipments(TransportCompany transportCompany){
        // SELECT COUNT(*) FROM Shipments
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Shipment> root = criteriaQuery.from(Shipment.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            return session.createQuery(criteriaQuery).getSingleResult();
        }
    }

    public static List<Shipment> sortShipmentsByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Arrival_Address FROM Shipment Arrival_Address ORDER BY Arrival_Address.arrivalAddress", Shipment.class).getResultList();
        }
    }
}

