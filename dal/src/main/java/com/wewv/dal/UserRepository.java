package com.wewv.dal;

import com.wewv.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT t from user t where t.username = :username")
    User getByUsername(@Param("username") String username);
}
