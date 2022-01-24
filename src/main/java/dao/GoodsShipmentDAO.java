package dao;

import configuration.SessionFactoryUtil;
import entity.FuelTankShipment;
import entity.GoodsShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class GoodsShipmentDAO {

    public static void saveGoodsShipment(GoodsShipment goodsShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(goodsShipment);
            transaction.commit();
        }
    }

    public static void saveOrUpdateGoodsShipment(GoodsShipment goodsShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(goodsShipment);
            transaction.commit();
        }
    }

    public static void deleteGoodsShipment(GoodsShipment goodsShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(goodsShipment);
            transaction.commit();
        }
    }

    public static void saveGoodsShipments(Set<GoodsShipment> goodsShipmentSet) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsShipmentSet.stream().forEach(gs -> session.save(gs));
            transaction.commit();
        }
    }

    public static List<GoodsShipment> readGoodsShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT gs FROM GoodsShipment gs", GoodsShipment.class).getResultList();
        }
    }

    public static GoodsShipment getGoodsShipment(long id) {
        GoodsShipment goodsShipment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsShipment = session.get(GoodsShipment.class, id);
            transaction.commit();
        }
        return goodsShipment;
    }

    public static void deleteGoodsShipments(Set<GoodsShipment> goodsShipments) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsShipments.stream().forEach(gs -> session.delete(gs));
            transaction.commit();
        }
    }

    public static List<GoodsShipment> sortGoodsShipmentByDestination(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT Arrival_Date FROM GoodsShipment Arrival_Date ORDER BY Arrival_Date.arrivalDate", GoodsShipment.class).getResultList();
        }
    }
}

