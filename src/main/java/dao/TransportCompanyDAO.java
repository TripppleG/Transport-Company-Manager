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
            transportCompanyList.stream().forEach(trCmp -> TransportCompanyDAO.saveTransportCompany(trCmp));
            transaction.commit();
        }
    }

    public static void saveOrUpdateTransportCompanies(Set<TransportCompany> transportCompanyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transportCompanyList.stream().forEach(trCmp -> TransportCompanyDAO.saveOrUpdateTransportCompany(trCmp));
            transaction.commit();
        }
    }

    public static List<TransportCompany> readTransportCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT trCmp FROM TransportCompany trCmp", TransportCompany.class).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByName(){
        // SELECT * FROM transport_company ORDER BY name
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> criteriaQuery = criteriaBuilder.createQuery(TransportCompany.class);
            Root<TransportCompany> root = criteriaQuery.from(TransportCompany.class);
            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("name")));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByIncome(){
        // SELECT * FROM transport_company ORDER BY name
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> criteriaQuery = criteriaBuilder.createQuery(TransportCompany.class);
            Root<TransportCompany> root = criteriaQuery.from(TransportCompany.class);
            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("income")));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public static List<TransportCompany> sortTransportCompaniesByNameThenIncome(){
        // SELECT * FROM transport_company ORDER BY name, income
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> criteriaQuery = criteriaBuilder.createQuery(TransportCompany.class);
            Root<TransportCompany> root = criteriaQuery.from(TransportCompany.class);
            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("name")), criteriaBuilder.asc(root.get("income")));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
