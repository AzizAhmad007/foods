package com.test.food.service;

import com.test.food.dto.MstUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> createUser(MstUserDto requestDTO);

    public ResponseEntity<?> updateUser(MstUserDto requestDTO);
}
