package com.pizza.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ingredient",
       uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) }
)
public class Ingredient extends RepresentationModel<Ingredient> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) //DB level validation
  @NotBlank
  @NonNull //even if I have used the validation annotation @NotBlank, I need the @NonNull in order to have the constructor
  private String name;

  private String notes;

  @OneToMany(mappedBy = "ingredient")
  @ToString.Exclude
  @JsonIgnore
  private Set<PizzaIngredient> pizzaIngredients;

  @ManyToOne
  @JoinColumns({@JoinColumn(name = "supplier"), @JoinColumn(name = "ingredient")})
  private IngredientSupplier supplier;

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
          return false;
      }
    Ingredient that = (Ingredient) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 1847634289;
  }
}
