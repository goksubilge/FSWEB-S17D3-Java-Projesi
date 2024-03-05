package com.wit.zoo.controller;

import com.wit.zoo.entity.Gender;
import com.wit.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
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

     @GetMapping("/")
    public List<Koala> getAllKoalaList(){
        return koalasMap.values().stream().toList();
     }

     @GetMapping("/{id}")
    public Koala getIdKoala(@PathVariable int id){
         //TODO [Bilge] isValidCheck
         // TODO [Bilge] checkKoalaExist
        return koalasMap.get(id);
     }

     @PostMapping("/")
    public Koala saveKoala(@RequestBody Koala koala){
        //TODO [Bilge] checkKoalaExist;
         //TODO [Bilge] checkKoalaWeight;
        koalasMap.put(koala.getId(),koala);
        return koala;
     }

     @PutMapping("/{id}")
    public Koala putKoala(@PathVariable int id,@RequestBody Koala koala){
         //TODO [Bilge] isValid check;
         //TODO [Bilge] checkKoalaWeight;
         //TODO [Bilge] checkKoala => send save;
         koala.setId(id);
         koalasMap.put(id,koala);
         return koala;
     }

     @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        // TODO [Bilge] isValid check;
         // TODO [Bilge] checkKoalaExist;
        Koala deletedKoala = koalasMap.get(id);
        koalasMap.remove(id);
        return deletedKoala;
     }
}
