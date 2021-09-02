package com.pizza.pizza.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupplierDTO {
  
  private Long id;
  private String name;
  private String email;
  private String address;
}
