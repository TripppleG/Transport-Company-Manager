package configuration;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Driver.class);
            configuration.addAnnotatedClass(Shipment.class);
            configuration.addAnnotatedClass(GoodsShipment.class);
            configuration.addAnnotatedClass(PeopleShipment.class);
            configuration.addAnnotatedClass(FuelTankShipment.class);
            configuration.addAnnotatedClass(TransportCompany.class);
            configuration.addAnnotatedClass(TransportCompanyManager.class);
            configuration.addAnnotatedClass(Vehicle.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}