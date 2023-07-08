package com.example.unit_testing.infrasctructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    private String name;
    private String cpf;
    private String profession;
    private Integer age;
    private String city;
    private String street;
    private int number;

}
