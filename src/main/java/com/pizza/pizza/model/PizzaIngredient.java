package com.pizza.pizza.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pizza_ingredient", uniqueConstraints = { @UniqueConstraint(columnNames = { "pizza", "ingredient" }) })
public class PizzaIngredient extends RepresentationModel<PizzaIngredient> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pizza", nullable = false)
  @NotNull
  @NonNull
  private Pizza pizza;

  @ManyToOne
  @JoinColumn(name = "ingredient", nullable = false)
  @NotNull
  @NonNull
  private Ingredient ingredient;

  @ManyToOne(targetEntity = UnitOfMeasure.class)
  @JoinColumn(name = "unit_of_measure")
  private UnitOfMeasure unitOfMeasure;

  private Double quantity;

  protected PizzaIngredient() {
    this(new Pizza(), new Ingredient());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    PizzaIngredient that = (PizzaIngredient) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 672714343;
  }
}
