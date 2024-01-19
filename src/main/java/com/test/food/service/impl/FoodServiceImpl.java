package com.test.food.service.impl;

import com.test.food.common.ResponseDTO;
import com.test.food.dto.MstFoodDto;
import com.test.food.model.MstFood;
import com.test.food.repository.FoodRepository;
import com.test.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pln.sparepart.soaldeveloper.common.Checker.isNullStr;

@Service(value = "foodService")
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepo;

    @Override
    public ResponseEntity<?> createFood(MstFoodDto requestDTO) {
        ResponseDTO response = new ResponseDTO();

        try{
            //cek apakah nama food sudah ada
            String existingFood = foodRepo.findByNameFood(requestDTO.getName());
            if (existingFood != null) {
                response.setCode("409");
                response.setMessage("Name Foods " + requestDTO.getName() + " already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            //cek apakah field mandatory tidak kosong
            if (isNullStr(requestDTO.getName()) &&
                isNullStr(""+requestDTO.getAmount()) &&
                isNullStr(requestDTO.getUnit())) {

                response.setCode("204");
                response.setMessage("Field mandatory tidak kosong");
                return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Buat entity baru dan simpan ke repository
            MstFood foodEntity = new MstFood();
            foodEntity.setName(requestDTO.getName());
            foodEntity.setAmount(requestDTO.getAmount());
            foodEntity.setUnit(requestDTO.getUnit());

            foodRepo.save(foodEntity);

            response.setCode("200");
            response.setData(foodEntity);
            response.setMessage("Master foods has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> udpateFood(MstFoodDto requestDTO) {
        ResponseDTO response = new ResponseDTO();

        try{
            String foodList = foodRepo.findByNameFood(requestDTO.getName());
            if (foodList == null) {
                response.setCode("204");
                response.setMessage("Name food " + requestDTO.getName() + "not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (isNullStr(requestDTO.getName()) ||
                isNullStr(""+requestDTO.getAmount()) ||
                isNullStr(requestDTO.getUnit())) {

                response.setCode("204");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            MstFood foodEntity = new MstFood();
            foodEntity.setName(requestDTO.getName());
            foodEntity.setAmount(requestDTO.getAmount());
            foodEntity.setUnit(requestDTO.getUnit());

            foodRepo.save(foodEntity);

            response.setCode("200");
            response.setData(foodEntity);
            response.setMessage("Master foods has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getNameFood(String nameFood) {
        ResponseDTO response = new ResponseDTO();
        MstFood food = foodRepo.findByFood(nameFood);

        if (food == null){
            response.setCode("204");
            response.setMessage("Name food " + nameFood + " not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(food);
        response.setMessage("Get Data By Master food successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        ResponseDTO response = new ResponseDTO();
        List<MstFood> foodList = foodRepo.findAll();

        response.setCode("200");
        response.setData(foodList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteFood(String nameFood) {
        ResponseDTO response = new ResponseDTO();
        MstFood food = foodRepo.findByFood(nameFood);

        if (food == null){
            response.setCode("204");
            response.setMessage("Name food " + nameFood + " not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        foodRepo.delete(food);

        response.setCode("200");
        response.setData(food);
        response.setMessage("Delete Data By Master Food successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
