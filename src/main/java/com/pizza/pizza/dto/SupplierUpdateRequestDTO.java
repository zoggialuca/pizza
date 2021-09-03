package com.pizza.pizza.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierUpdateRequestDTO {
  @NotBlank
  private String name;

}
