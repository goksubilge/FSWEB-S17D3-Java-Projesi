package com.wit.zoo.exceptions;

import org.springframework.http.HttpStatus;
import com.wit.zoo.entity.Kangaroo;
import com.wit.zoo.entity.Koala;

import java.util.Map;


public class ZooValidation {
    public static void isValidIdAnimal(int id) {
        if (id <= 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void checkKangarooIdIsNotExist(Map<Integer,Kangaroo> animalMap, int id){
        if(!animalMap.containsKey(id)) {
            throw new ZooException("Id is not exist" + id, HttpStatus.NOT_FOUND);
        }
    }
    public static void checkKoalaIdIsNotExist(Map<Integer,Koala> animalMap, int id){
        if(!animalMap.containsKey(id)) {
            throw new ZooException("Id is not exist" + id, HttpStatus.NOT_FOUND);
        }
    }



}
