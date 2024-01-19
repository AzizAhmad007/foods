package com.test.food.repository;

import com.test.food.model.MstFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<MstFood, String> {

    @Query(value = "SELECT A.NAME FROM MSTFOODS AS A WHERE A.NAME = :nameFood", nativeQuery = true)
    String findByNameFood(@Param("nameFood") String nameFood);

    @Query(value = "SELECT A.NAME, A.AMOUNT, A.UNIT FROM MSTFOODS AS A WHERE A.NAME = :nameFood", nativeQuery = true)
    MstFood findByFood(@Param("nameFood") String nameFood);

    @Query(value = "SELECT A.NAME, A.AMOUNT, A.UNIT FROM MSTFOODS AS A ORDER BY A.NAME ASC", nativeQuery = true)
    List<MstFood> findAll();
}
