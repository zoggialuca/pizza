package com.pizza.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="ingredient"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Ingredient extends RepresentationModel<Ingredient>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;

    @OneToMany(mappedBy = "ingredient") @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<PizzaIngredient> pizzaIngredients;

    protected Ingredient(){
        this("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1847634289;
    }
}
