package com.spring_final.daos.hibernateImpl;

import com.spring_final.model.TypeOfActivity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository //@Component
@Transactional
public class TypesOfActivitiesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addType(TypeOfActivity type){
        sessionFactory.getCurrentSession().save(type);
    }

    public TypeOfActivity getType(int id){
        TypeOfActivity type = sessionFactory.getCurrentSession().
                get(TypeOfActivity.class, id);//createQuery("FROM User WHERE id= :id").setParameter("id", id).uniqueResult();
        return type;
    }


    public TypeOfActivity getType(String name){
        TypeOfActivity type = (TypeOfActivity) sessionFactory.getCurrentSession().createQuery("FROM TypeOfActivity WHERE name = :name")
                .setParameter("name", name).uniqueResult();
        return type;
    }

    public List<TypeOfActivity> getTypes(){
        List<TypeOfActivity> types;
        types = (List<TypeOfActivity>) sessionFactory.getCurrentSession().createQuery("FROM TypeOfActivity").getResultList();
        return types;
    }


    public void delete(int id){
        TypeOfActivity type = getType(id);
        sessionFactory.getCurrentSession().remove(type);
    }


    public void update(TypeOfActivity type){
        sessionFactory.getCurrentSession().update(type);
    }
}
