package com.spring_final.daos;

import com.spring_final.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ActivityDaoRep extends JpaRepository<Activity, Integer> {

    @Override
    List<Activity> findAll();

    @Override
    Optional<Activity> findById(Integer integer);

    Activity getByName(String name);

    @Query(
            value = "SELECT * FROM activity ac LEFT JOIN activityrequest req ON ac.id=req.activity_id where (req.action='Add' AND req.status='Approved') OR req.activity_id IS NULL group by ac.id order by count(req.activity_id) LIMIT :page,:size",
            nativeQuery = true
    )
    List<Activity> findByNumberOfUsers(@Param("page") int page, @Param("size") int size);

    @Override
    long count();

    @Override
    <S extends Activity> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Activity entity);

}
