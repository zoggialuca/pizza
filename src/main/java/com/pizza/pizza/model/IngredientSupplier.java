package com.pizza.pizza.model;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class IngredientSupplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "supplier")
  @NotNull
  @NonNull
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "ingredient", unique = true)
  @NotNull
  @NonNull
  private Ingredient ingredient;

  protected IngredientSupplier() {
    this(new Supplier(), new Ingredient());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    IngredientSupplier that = (IngredientSupplier) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

