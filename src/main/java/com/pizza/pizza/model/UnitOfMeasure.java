package com.pizza.pizza.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="unit_of_measure"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class UnitOfMeasure extends RepresentationModel<UnitOfMeasure>{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private final @NonNull String name;

    protected UnitOfMeasure(){
        this("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UnitOfMeasure that = (UnitOfMeasure) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1493657823;
    }
}
