package com.wit.zoo.controller;

import com.wit.zoo.entity.Gender;
import com.wit.zoo.entity.Kangaroo;
import jakarta.annotation.PostConstruct;
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

    @GetMapping("/")
    public List<Kangaroo> getAllKangarooList(){
        return kangaroosMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getIdKangaroo(@PathVariable int id){
        //TODO [Bilge] isValidCheck
        // TODO [Bilge] checkKangarooExist;
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
