package com.test.food.repository;

import com.test.food.model.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MstUser, String> {

    @Query(value = "SELECT A.EMAIL FROM MSTUSER AS A WHERE A.EMAIL = :email", nativeQuery = true)
    String findByEmail(@Param("email") String email);

}
