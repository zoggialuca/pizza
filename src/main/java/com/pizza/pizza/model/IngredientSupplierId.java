package com.pizza.pizza.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.Hibernate;

@Embeddable
@Getter
public class IngredientSupplierId implements Serializable {

  @Column(name = "ingredient_id", unique = true)
  private Long ingredientId;
  @Column(name = "supplier_id")
  private Long supplierId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    IngredientSupplierId that = (IngredientSupplierId) o;
    return Objects.equals(ingredientId, that.ingredientId)
        && Objects.equals(supplierId, that.supplierId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ingredientId, supplierId);
  }
}