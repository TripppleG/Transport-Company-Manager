package dao;

import configuration.SessionFactoryUtil;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;


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

    /**
     *
     * @param transportCompanySet
     */
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

    public static List<TransportCompany> sortTransportCompanyByName(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Name FROM TransportCompany Name ORDER BY Name.name", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompanyByIncome(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Income FROM TransportCompany Income ORDER BY Income.income", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortAllShipmentsByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Destination FROM PeopleShipment join GoodsShipment join FuelTankShipment Destination ORDER BY Destination.arrivalDate",
                    TransportCompany.class).getResultList();

        }
    }

    public static List<TransportCompany> countOfAllShipments(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT COUNT(All_Shipments) FROM PeopleShipment join GoodsShipment join FuelTankShipment All_Shipments",
                    TransportCompany.class).getResultList();

        }
    }

    public static List<TransportCompany> priceOfAllShipments(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT COUNT(All_Shipments_Price) FROM PeopleShipment.shipmentPrice join GoodsShipment.shipmentPrice join FuelTankShipment.shipmentPrice All_Shipments_Price",
                    TransportCompany.class).getResultList();

        }
    }
}
