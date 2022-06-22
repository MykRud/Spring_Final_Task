package com.spring_final.daos;

import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDaoRep extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findById();

    User findByUsername();



    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    <S extends User> S save(S entity);

    @Override
    void delete(User entity);

    void update(User entity);

    @Query("INSERT INTO authority_user(authorities_id, users_id) VALUES(:authorityId, :userId)")
    void setAuthority(@Param("userId") int userId, @Param("authorityId") int authorityId);

    @Query("DELETE FROM authority_user WHERE users_id=:userId")
    void deleteAuthorities(@Param("userId") int userId);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);
}
