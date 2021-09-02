package com.pizza.pizza.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "supplier"
        , uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    private String address;

    @OneToMany(mappedBy = "ingredient")
    private List<Ingredient> ingredients;

    public Supplier(String email, String name, String address) {
        this.email = email;
        this.name = name;
        this.address = address;
    }
}
