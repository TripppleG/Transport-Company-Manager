package dao;

import configuration.SessionFactoryUtil;
import entity.PeopleShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class PeopleShipmentDAO {

    public static void savePeopleShipment(PeopleShipment peopleShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(peopleShipment);
            transaction.commit();
        }
    }

    public static void saveOrUpdatePeopleShipment(PeopleShipment peopleShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(peopleShipment);
            transaction.commit();
        }
    }

    public static void deletePeopleShipment(PeopleShipment peopleShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(peopleShipment);
            transaction.commit();
        }
    }

    public static void savePeopleShipments(Set<PeopleShipment> peopleShipmentSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            peopleShipmentSet.stream().forEach(ps -> session.save(ps));
            transaction.commit();
        }
    }

    public static List<PeopleShipment> readPeopleShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT ps FROM PeopleShipment ps", PeopleShipment.class).getResultList();
        }
    }

    public static PeopleShipment getPeopleShipment(long id) {
        PeopleShipment peopleShipment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            peopleShipment = session.get(PeopleShipment.class, id);
            transaction.commit();
        }
        return peopleShipment;
    }

    public static void deletePeopleShipments(Set<PeopleShipment> peopleShipments) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            peopleShipments.stream().forEach(ps -> session.delete(ps));
            transaction.commit();
        }
    }

    public static List<PeopleShipment> sortFuelTankShipmentByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Arrival_Address FROM PeopleShipment Arrival_Address ORDER BY Arrival_Address.arrivalAddress", PeopleShipment.class).getResultList();
        }
    }
}

