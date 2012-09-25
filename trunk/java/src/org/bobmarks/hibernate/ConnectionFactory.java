package org.bobmarks.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Connection factory class.
 */
public class ConnectionFactory {

    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
   
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}