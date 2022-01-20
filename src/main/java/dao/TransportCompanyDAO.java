package dao;

import configuration.SessionFactoryUtil;
import entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransportCompanyDAO {

    public static void saveTransportCompany(TransportCompany transportCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transportCompany);
            transaction.commit();
        }
    }

    public static void saveTransportCompanies(List<TransportCompany> transportCompanyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyList.stream().forEach((trCmp) -> session.save(trCmp));
            transaction.commit();
        }
    }

    public static List<TransportCompany> readTransportCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM TransportCompany a", TransportCompany.class).getResultList();
        }
    }
}
