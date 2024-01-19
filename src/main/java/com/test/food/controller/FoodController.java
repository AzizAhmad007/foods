package com.test.food.controller;

import com.test.food.dto.MstFoodDto;
import com.test.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/save")
    public ResponseEntity<?> createFood(@RequestBody MstFoodDto request){
        return foodService.createFood(request);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFood(@RequestBody MstFoodDto request){
        return foodService.udpateFood(request);
    }

    @GetMapping("/get/{nameFood}")
    public ResponseEntity<?> getNameFood(@PathVariable String nameFood){
        return foodService.getNameFood(nameFood);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return foodService.getAll();
    }

    @DeleteMapping("/delete/{nameFood}")
    public ResponseEntity<?> deleteFood(@PathVariable String nameFood){
        return foodService.deleteFood(nameFood);
    }
}
