package com.wewv.dal;

import com.wewv.models.Cook;
import com.wewv.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {

    @Query(value = "SELECT t from cook t where t.username = :username")
    Cook getByUsername(@Param("username") String username);
}
