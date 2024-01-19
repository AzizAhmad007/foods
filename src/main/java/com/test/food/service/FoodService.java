package com.test.food.service;

import com.test.food.dto.MstFoodDto;
import org.springframework.http.ResponseEntity;

public interface FoodService {

    public ResponseEntity<?> createFood(MstFoodDto requestDTO);

    public ResponseEntity<?> udpateFood(MstFoodDto requestDTO);

    public ResponseEntity<?> getNameFood(String nameFood);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteFood(String nameFood);

}
