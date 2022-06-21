package com.spring_final.configuration;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDriver {

    private Session session;
    private Transaction transaction;

    {
        session = HibernateBaseConfig.getSessionFactory().openSession();
    }

    public void openSession(){
        session = HibernateBaseConfig.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession(){
        transaction.commit();
        session.close();
    }

    public void rollback(){
        transaction.rollback();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
