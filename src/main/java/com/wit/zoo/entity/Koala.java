package com.wit.zoo.entity;

import lombok.Data;

// Extends ettiği parent class (Animal) 'ta eğer @noarguments olmasaydı bana burada constructor yazmadığım için hata verirdi.
@Data
public class Koala extends Animal {
    private int sleepHour;
    public Koala (int id, String name, double weight, Gender gender, int sleepHour){
        super(id, name, weight, gender);
        this.sleepHour = sleepHour;
    }

}