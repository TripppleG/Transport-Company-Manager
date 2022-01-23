package dao;

import configuration.SessionFactoryUtil;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TransportCompanyDAO {
    public static void saveTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transportCompany);
            transaction.commit();
        }
    }


    public static void saveOrUpdateTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transportCompany);
            transaction.commit();
        }
    }

    public static void deleteTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompany.getClients().stream().forEach(client -> ClientDAO.deleteClient(client));
            transportCompany.getDrivers().stream().forEach(driver -> DriverDAO.deleteDriver(driver));
            transportCompany.getFuelTankShipments().stream().forEach(fuelTankShipment -> FuelTankShipmentDAO.deleteFuelTankShipment(fuelTankShipment));
            transportCompany.getGoodsShipments().stream().forEach(goodsShipment -> GoodsShipmentDAO.deleteGoodsShipment(goodsShipment));
            transportCompany.getPeopleShipments().stream().forEach(peopleShipment -> PeopleShipmentDAO.deletePeopleShipment(peopleShipment));
            session.delete(transportCompany);
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

    public static void saveTransportCompanies(Set<TransportCompany> transportCompanySet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanySet.stream().forEach(trCmp -> session.save(trCmp));
            transaction.commit();
        }
    }

    public static Set<TransportCompany> readTransportCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return Set.copyOf(session.createQuery("SELECT trComp FROM TransportCompany trComp", TransportCompany.class).getResultList());
        }
    }
}
