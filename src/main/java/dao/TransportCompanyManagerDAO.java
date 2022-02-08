package dao;

import configuration.SessionFactoryUtil;
import entity.TransportCompany;
import entity.TransportCompanyManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransportCompanyManagerDAO {
    public static void saveTransportCompanyManager(TransportCompanyManager transportCompanyManager) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyManager.getTransportCompanies().stream().forEach(transportCompany -> TransportCompanyDAO.saveTransportCompany(transportCompany));
            session.save(transportCompanyManager);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransportCompany(TransportCompanyManager transportCompanyManager) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyManager.getTransportCompanies().stream().forEach(transportCompany -> TransportCompanyDAO.saveOrUpdateTransportCompany(transportCompany));
            session.saveOrUpdate(transportCompanyManager);
            transaction.commit();
        }
    }

    public static void deleteTransportCompany(TransportCompanyManager transportCompanyManager) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyManager.getTransportCompanies().stream().forEach(transportCompany -> TransportCompanyDAO.deleteTransportCompany(transportCompany));
            session.delete(transportCompanyManager);
            transaction.commit();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByName(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Name FROM TransportCompany Name ORDER BY Name.name", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByIncome(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Income FROM TransportCompany Income ORDER BY Income.income", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByIncomeThenName(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Company FROM TransportCompany Company ORDER BY Company.income, Company.name", TransportCompany.class).getResultList();
        }
    }
}
