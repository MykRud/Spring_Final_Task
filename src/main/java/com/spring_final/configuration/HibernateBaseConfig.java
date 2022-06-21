package com.spring_final.configuration;

import com.spring_final.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateBaseConfig {

    private static SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration().configure().addAnnotatedClass(Activity.class)
                .addAnnotatedClass(TypeOfActivity.class).addAnnotatedClass(User.class)
                .addAnnotatedClass(ActivityRequest.class).addAnnotatedClass(Authority.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactory = config.buildSessionFactory(reg);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
