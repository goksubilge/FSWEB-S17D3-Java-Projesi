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
    public static void checkKangarooIdExistence(Map<Integer,Kangaroo> animalMap, int id, boolean isExist ){
        if(isExist){
            if(animalMap.containsKey(id)) {
                throw new ZooException("Id is already exist" + id, HttpStatus.BAD_REQUEST);
            }
        } else {
            if(!animalMap.containsKey(id)) {
                throw new ZooException("Id is not exist" + id, HttpStatus.NOT_FOUND);
            }
        }
    }
    public static void checkKoalaIdExistence(Map<Integer,Koala> animalMap, int id, boolean isExist){
        if(isExist){
            if(animalMap.containsKey(id)) {
                throw new ZooException("Id is already exist" + id, HttpStatus.BAD_REQUEST);
            }
        } else {
            if(!animalMap.containsKey(id)) {
                throw new ZooException("Id is not exist" + id, HttpStatus.NOT_FOUND);
            }
        }
    }


}
