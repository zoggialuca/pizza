package com.pizza.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pizza", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Pizza extends RepresentationModel<Pizza> {

  @OneToMany(mappedBy = "pizza")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  Set<PizzaIngredient> pizzaIngredients;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false) //DB level validation
  @NotBlank //application level validation
  @NonNull
  private String name;
  private Boolean isVegetarian;
  private Double price;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Pizza pizza = (Pizza) o;

    return Objects.equals(id, pizza.id);
  }

  @Override
  public int hashCode() {
    return 2055526148;
  }
}
