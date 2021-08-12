package com.pizza.pizza.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="pizza_ingredient"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"pizza", "ingredient"})}    
)
public class PizzaIngredient extends RepresentationModel<PizzaIngredient>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizza", nullable = false)
    @NotNull
    @NonNull
    private final Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "ingredient", nullable = false)
    @NotNull
    @NonNull
    private final Ingredient ingredient;

    private Double quantity;

    @ManyToOne(targetEntity = UnitOfMeasure.class)
    @JoinColumn(name = "unit_of_measure")
    private UnitOfMeasure unitOfMeasure;

    protected PizzaIngredient(){
        this(new Pizza(), new Ingredient());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PizzaIngredient that = (PizzaIngredient) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 672714343;
    }
}
