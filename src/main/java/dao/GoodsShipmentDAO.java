package dao;

import configuration.SessionFactoryUtil;
import entity.GoodsShipment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodsShipmentDAO {
    public static void saveGoodsShipment(GoodsShipment goodsShipment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(goodsShipment);
            transaction.commit();
        }
    }

    public static void saveGoodsShipments(List<GoodsShipment> goodsShipmentList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsShipmentList.stream().forEach((v) -> session.save(v));
            transaction.commit();
        }
    }

    public static List<GoodsShipment> readGoodsShipments() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM GoodsShipment a", GoodsShipment.class).getResultList();
        }
    }
}
