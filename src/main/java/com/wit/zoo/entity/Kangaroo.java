package com.wit.zoo.entity;

import lombok.Data;

// Extends ettiği parent class (Animal)'ta eğer @noarguments olmasaydı bana burada constructor yazmadığım için hata verirdi.
@Data
public class Kangaroo extends Animal {
    private double height;
    private boolean isAggressive;
    public Kangaroo (int id, String name, double height, double weight, Gender gender, boolean isAggressive){
        super(id, name, weight, gender );
        this.height = height;
        this.isAggressive = isAggressive;
    }
   }
