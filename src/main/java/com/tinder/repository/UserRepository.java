package com.tinder.repository;

import com.tinder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query(value = "SELECT * FROM users WHERE login = :login", nativeQuery = true)
    User findByLogin(@Param("login") String login);

    @Query(value = "SELECT * FROM users WHERE sex = :sex", nativeQuery = true)
    List<User> findUsersBySex(@Param("sex") String sex);
}
