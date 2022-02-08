package dao;

import configuration.SessionFactoryUtil;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;


public class TransportCompanyDAO {
    public static void saveTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany.getClients().stream().forEach(client -> ClientDAO.saveClient(client));
            transportCompany.getDrivers().stream().forEach(driver -> DriverDAO.saveDriver(driver));
            transportCompany.getShipments().stream().forEach(shipment -> ShipmentDAO.saveShipment(shipment));
            transportCompany.getVehicles().stream().forEach(vehicle -> VehicleDAO.saveVehicle(vehicle));
            session.save(transportCompany);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany.getClients().stream().forEach(client -> ClientDAO.saveOrUpdateClient(client));
            transportCompany.getDrivers().stream().forEach(driver -> DriverDAO.saveOrUpdateDriver(driver));
            transportCompany.getShipments().stream().forEach(shipment -> ShipmentDAO.saveOrUpdateShipment(shipment));
            session.saveOrUpdate(transportCompany);
            transaction.commit();
        }
    }

    public static void deleteTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany.getClients().stream().forEach(client -> ClientDAO.deleteClient(client));
            transportCompany.getDrivers().stream().forEach(driver -> DriverDAO.deleteDriver(driver));
            transportCompany.getShipments().stream().forEach(shipment -> ShipmentDAO.deleteShipment(shipment));
            session.delete(transportCompany);
            transaction.commit();
        }
    }

    public static void deleteTransportCompanies(Set<TransportCompany> transportCompanySet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanySet.stream().forEach(transportCompany -> deleteTransportCompany(transportCompany));
            session.delete(transportCompanySet);
            transaction.commit();
        }
    }

    public static TransportCompany getCompany(String bulstat) {
        TransportCompany company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(TransportCompany.class, bulstat);
            transaction.commit();
        }
        return company;
    }

    public static void saveTransportCompanies(Set<TransportCompany> transportCompanyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyList.stream().forEach(trCmp -> session.save(trCmp));
            transaction.commit();
        }
    }

    public static List<TransportCompany> readTransportCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT trCmp FROM TransportCompany trCmp", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortAllShipmentsByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Destination FROM Shipment Destination ORDER BY Destination.arrivalAddress",
                    TransportCompany.class).getResultList();
        }
    }

//    public static int priceOfAllShipments(){
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            return session.createQuery("SELECT SUM() FROM Shipment.shipmentPrice All_Shipments_Price",
//                    TransportCompany.class).getFirstResult();
//        }
//    }
}
