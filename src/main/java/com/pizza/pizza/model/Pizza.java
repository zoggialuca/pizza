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
@Table(name="pizza"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Pizza extends RepresentationModel<Pizza>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;
    private final @NonNull Boolean isVegetarian;

    @OneToMany(mappedBy = "pizza") @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<PizzaIngredient> pizzaIngredients;

    protected Pizza(){
        this("");
    }

    public Pizza(String name){
        this(name, Boolean.FALSE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pizza pizza = (Pizza) o;

        return Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return 2055526148;
    }
}
