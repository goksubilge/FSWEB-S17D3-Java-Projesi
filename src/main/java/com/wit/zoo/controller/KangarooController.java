package com.wit.zoo.controller;

import com.wit.zoo.entity.Gender;
import com.wit.zoo.entity.Kangaroo;
import com.wit.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  // Bu controllerdan bir instance yaratması için
@RequestMapping("/kangaroos") // General Path

public class KangarooController {
    Map<Integer, Kangaroo> kangaroosMap;

    @PostConstruct
    public void initKangarooMap() {
        kangaroosMap = new HashMap<>();
        kangaroosMap.put(1, new Kangaroo(1,"Dragonite", 210, 2.21 ,Gender.MALE,false));
    }

    // ResponseEntity örneği olarak:
    @GetMapping("/hey")
    public ResponseEntity<String> helloKangaroo(){
        return new ResponseEntity<>("hello Kangaroo", HttpStatus.CREATED);
    }
    @GetMapping("/status")
    public ResponseEntity<List<Kangaroo>> getAllStatusKangarooList(){
        return new ResponseEntity<>(kangaroosMap.values().stream().toList(), HttpStatus.OK);
    }
    //
    @GetMapping("/")
    public List<Kangaroo> getAllKangarooList(){
        return kangaroosMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getIdKangaroo(@PathVariable int id){
        // isValidCheck
        ZooValidation.isValidIdAnimal(id);
        // checkKangarooExist;
        ZooValidation.checkKangarooIdExistence(kangaroosMap,id,false);
        return kangaroosMap.get(id);
    }

    @PostMapping("/")
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo){
        // isValid check;
        ZooValidation.isValidIdAnimal(kangaroo.getId());
        // checkKangarooExist;
        ZooValidation.checkKangarooIdExistence(kangaroosMap, kangaroo.getId(), true);
        // checkWeight;
        ZooValidation.isAnimalWeightValid(kangaroo.getWeight());
        kangaroosMap.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo putKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        // isValid check;
        ZooValidation.isValidIdAnimal(id);
        // checkWeight;
        ZooValidation.isAnimalWeightValid(kangaroo.getWeight());
        // checkKangarooExist(false) => send saveKangaroo;
        kangaroo.setId(id);
        if(kangaroosMap.containsKey(id)){
            // kangaroo.setId(id); => hata düzeltmek için yukarı taşıdım. önce id 'yi setlemeli
            kangaroosMap.put(id,kangaroo);
            return kangaroo;
        } else {
            return saveKangaroo(kangaroo);
        }
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id){
        // isValid check;
        ZooValidation.isValidIdAnimal(id);
        // checkKangarooExist;
        ZooValidation.checkKangarooIdExistence(kangaroosMap,id,false);
        Kangaroo deletedKangaroo = kangaroosMap.get(id);
        kangaroosMap.remove(id);
        return deletedKangaroo;
    }
}