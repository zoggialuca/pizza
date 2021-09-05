package com.pizza.pizza.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.NonNull;
import org.hibernate.Hibernate;

//@Entity
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name="abc")
public class ABC {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) //DB level validation
  @NotBlank
  @NonNull //even if I have used the validation annotation @NotBlank, I need the @NonNull in order to have the constructor
  private String name;

  @Override
  public int hashCode() {
    return 202370913;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ABC abc = (ABC) o;

    return Objects.equals(id, abc.id);
  }
}
