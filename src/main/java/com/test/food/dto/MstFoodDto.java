package com.test.food.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MstFoodDto {
    private String name;
    private BigDecimal amount;
    private String unit;
}
