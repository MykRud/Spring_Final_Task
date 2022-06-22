package com.spring_final.daos;

import com.spring_final.model.Activity;
import com.spring_final.model.TypeOfActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TypesOfActivitiesDaoRep extends JpaRepository<TypeOfActivity, Integer> {

    @Override
    List<TypeOfActivity> findAll();

    @Override
    Page<TypeOfActivity> findAll(Pageable pageable);

    @Override
    TypeOfActivity getOne(Integer integer);

    TypeOfActivity getByName(String name);

    @Override
    <S extends TypeOfActivity> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(TypeOfActivity entity);

}
