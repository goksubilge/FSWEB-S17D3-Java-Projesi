package com.wit.zoo.controller;

import com.wit.zoo.entity.Gender;
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
@RequestMapping("/koalas") // General Path

public class KoalaController {
    Map<Integer, Koala> koalasMap;

    @PostConstruct
    public void initKoalaMap(){
        koalasMap = new HashMap<>();
        koalasMap.put(1,new Koala(1,"GigglyPuff", 210, Gender.FEMALE, 8));
    }

    // ResponseEntity örneği olarak:
     @GetMapping("/hi")
    public ResponseEntity<String> helloKoala(){
        return new ResponseEntity<>("hello Koala", HttpStatus.CREATED);
     }

    @GetMapping("/status")
    public ResponseEntity<List<Koala>> getAllStatusKoalaList(){
        return new ResponseEntity<>(koalasMap.values().stream().toList(), HttpStatus.OK);
    }
    //
    @GetMapping("/")
    public List<Koala> getAllKoalaList() {
        return koalasMap.values().stream().toList();
    }

     @GetMapping("/{id}")
    public Koala getIdKoala(@PathVariable int id){
         // isValidCheck
         ZooValidation.isValidIdAnimal(id);
         // checkKoalaExist
         ZooValidation.checkKoalaIdExistence(koalasMap,id,false);
         return koalasMap.get(id);
     }

     @PostMapping("/")
    public Koala saveKoala(@RequestBody Koala koala){
         // isValid check;
         ZooValidation.isValidIdAnimal(koala.getId());
        // checkKoalaExist;
         ZooValidation.checkKoalaIdExistence(koalasMap, koala.getId(), true);
         // checkWeight;
         ZooValidation.isAnimalWeightValid(koala.getWeight());
        koalasMap.put(koala.getId(),koala);
        return koala;
     }

     @PutMapping("/{id}")
    public Koala putKoala(@PathVariable int id,@RequestBody Koala koala){
         // isValid check;
         ZooValidation.isValidIdAnimal(id);
         // checkWeight;
         ZooValidation.isAnimalWeightValid(koala.getWeight());
         //  checkKoalaExistence(false) => send saveKoala;
         koala.setId(id);
         if(koalasMap.containsKey(id)){
             // koala.setId(id); => hata düzeltmek için yukarı taşıdım. önce id 'yi setlemeli
             koalasMap.put(id,koala);
             return koala;
         } else {
             return saveKoala(koala);
         }
     }

     @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        // isValid check;
         ZooValidation.isValidIdAnimal(id);
         // checkKoalaExist;
         ZooValidation.checkKoalaIdExistence(koalasMap,id,false);
         Koala deletedKoala = koalasMap.get(id);
        koalasMap.remove(id);
        return deletedKoala;
     }
}