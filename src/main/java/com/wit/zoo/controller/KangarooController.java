package com.wit.zoo.controller;

import com.wit.zoo.entity.Animal;
import com.wit.zoo.entity.Gender;
import com.wit.zoo.entity.Kangaroo;
import com.wit.zoo.entity.Koala;
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
        //TODO [Bilge] isValidCheck
        ZooValidation.isValidIdAnimal(id);
        // TODO [Bilge] checkKangarooExist;
        ZooValidation.checkKangarooIdIsNotExist(kangaroosMap,id);
        return kangaroosMap.get(id);
    }

    @PostMapping("/")
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo){
        //TODO [Bilge] checkKangarooExist;
        kangaroosMap.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo putKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        //TODO [Bilge] isValid check;
        //TODO [Bilge] checkKangarooExist => send save;
        kangaroo.setId(id);
        kangaroosMap.put(id,kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id){
        //TODO [Bilge] isValid check;
        //TODO [Bilge] checkKangarooExist;
        Kangaroo deletedKangaroo = kangaroosMap.get(id);
        kangaroosMap.remove(id);
        return deletedKangaroo;
    }
}
