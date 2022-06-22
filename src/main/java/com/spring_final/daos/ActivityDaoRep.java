package com.spring_final.daos;

import com.spring_final.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ActivityDaoRep extends JpaRepository<Activity, Integer> {

    @Override
    List<Activity> findAll();

    @Override
    Activity getOne(Integer integer);

    Activity getByName(String name);

    @Override
    Page<Activity> findAll(Pageable pageable);

    @Override
    <S extends Activity> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Activity entity);

}
