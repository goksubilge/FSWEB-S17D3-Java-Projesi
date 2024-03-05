package com.wit.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data => Getter, Setter, HashCode, toEqual
// @AllArgsConstructor => no arguments constructor
// @NoArgsConstructor => all arguments constructor
// @Builder => Person person = Person.builder().firstName("John").lastName("Doe").age(30).build();
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Animal {
    private int id;
    private String name;
    private double  weight;
    private Gender gender;
}
