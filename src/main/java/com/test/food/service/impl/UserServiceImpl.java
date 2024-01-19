package com.test.food.service.impl;

import com.test.food.common.ResponseDTO;
import com.test.food.dto.MstUserDto;
import com.test.food.model.MstUser;
import com.test.food.repository.UserRepository;
import com.test.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

import static com.pln.sparepart.soaldeveloper.common.Checker.isNullStr;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<?> createUser(MstUserDto requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try{
            String existingUser = userRepo.findByEmail(requestDTO.getEmail());
            if (existingUser != null) {
                response.setCode("409");
                response.setMessage("Email " + requestDTO.getEmail() + " already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            if (isNullStr(requestDTO.getEmail()) &&
                isNullStr(requestDTO.getPassword())){

                response.setCode("204");
                response.setMessage("Field mandatory tidak kosong");
                return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            MstUser userEntity = new MstUser();
            userEntity.setEmail(requestDTO.getEmail());
            userEntity.setPassword(passEncript(requestDTO.getPassword()));

            userRepo.save(userEntity);

            response.setCode("200");
            response.setData(userEntity);
            response.setMessage("Master user has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateUser(MstUserDto requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try{
            String userList = userRepo.findByEmail(requestDTO.getEmail());
            if (userList == null){
                response.setCode("204");
                response.setMessage("Email " + requestDTO.getEmail() + "not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (isNullStr(requestDTO.getPassword())){
                response.setCode("204");
                response.setMessage("Password tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            MstUser userEntity = new MstUser();
            userEntity.setPassword(passEncript(requestDTO.getPassword()));

            userRepo.save(userEntity);

            response.setCode("200");
            response.setData(userEntity);
            response.setMessage("Password has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String passEncript(String pass){
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }
}
