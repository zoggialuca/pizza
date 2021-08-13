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
@Table(name="abc")
public class ABC {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //DB level validation
    @NotBlank
    @NonNull //even if I have used the validation annotation @NotBlank, I need the @NonNull in order to have the constructor
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ABC abc = (ABC) o;

        return Objects.equals(id, abc.id);
    }

    @Override
    public int hashCode() {
        return 202370913;
    }
}
