package com.pizza.pizza.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="unit_of_measure"
    , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class UnitOfMeasure{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @NonNull
    private String name;

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
