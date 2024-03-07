package com.wit.zoo.exceptions;

import org.springframework.http.HttpStatus;
import com.wit.zoo.entity.Kangaroo;
import com.wit.zoo.entity.Koala;

import java.util.Map;

public class ZooValidation {
    public static void isValidIdAnimal(int id) {
        if (id <= 0) {
            throw new ZooException("1) Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void checkKangarooIdExistence(Map<Integer,Kangaroo> animalMap, int id, boolean isExist ){
        if(isExist){
            if(animalMap.containsKey(id)) {
                throw new ZooException("2) Id is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        } else {
            if(!animalMap.containsKey(id)) {
                throw new ZooException("3) Id is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        }
    }
    public static void checkKoalaIdExistence(Map<Integer,Koala> animalMap, int id, boolean isExist){
        if(isExist){
            if(animalMap.containsKey(id)) {
                throw new ZooException("4) Id is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        } else {
            if(!animalMap.containsKey(id)) {
                throw new ZooException("5) Id is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        }
    }
    public static void isAnimalWeightValid(double weight) {
        if (weight <= 0) {
            throw new ZooException("6) Weight is must be greater than: " + weight, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isKoalaSleepHourValid(double weight) {
        if (weight <= 0) {
            throw new ZooException("7) Weight is must be greater than: " + weight, HttpStatus.BAD_REQUEST);
        }
    }
}
