package dao;

import configuration.SessionFactoryUtil;
import entity.PeopleShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PeopleShipmentDAO {
    public static void savePeopleShipment(PeopleShipment peopleShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(peopleShipment);
            transaction.commit();
        }
    }

    public static void savePeopleShipments(List<PeopleShipment> peopleShipmentList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            peopleShipmentList.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<PeopleShipment> readPeopleShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM PeopleShipment a", PeopleShipment.class).getResultList();
        }
    }
}
