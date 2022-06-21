package com.spring_final.configuration;

import com.spring_final.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
public class HibernateConfig {

    /*@Bean
    ServiceRegistry getServiceRegistry(org.hibernate.cfg.Configuration configuration){
        return new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    }

    @Bean
    public org.hibernate.cfg.Configuration getConfiguration(){
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration().configure().addAnnotatedClass(Activity.class)
                .addAnnotatedClass(TypeOfActivity.class).addAnnotatedClass(User.class)
                .addAnnotatedClass(ActivityRequest.class).addAnnotatedClass(Authority.class);
        return config;
    }

    @Bean
    public SessionFactory getSessionFactory(){
        org.hibernate.cfg.Configuration configuration = getConfiguration();
        ServiceRegistry serviceRegistry = getServiceRegistry(configuration);
        return getConfiguration().buildSessionFactory(serviceRegistry);
    }*/

}
