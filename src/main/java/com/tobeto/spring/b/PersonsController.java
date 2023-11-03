package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persons") //bu controllerın kontrol edeceği alt routerları tanımlamak
public class PersonsController {
    List<Person> inMemoryList =new ArrayList<>();

    @GetMapping
    public List<Person> get(){
        return inMemoryList;
    }

    //query string, path veriable
    //path => https://localhost:8080/api/person/1 => 1 = id
    //query string => https://localhost:8080/api/person/getById?id=1
    @GetMapping("{id}")
    public Optional<Person> getById(@RequestParam int id){
        //stream api
        //lambda Expression
       return  inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst();

    }
    @PostMapping
    public void add(@RequestBody Person person){
        inMemoryList.add(person);
    }
    @PutMapping("/update/{id}")
    public Person update(@PathVariable int id , @RequestBody Person person) throws Exception {
        Optional<Person> existingPerson =  getById(id);
        if (existingPerson.isPresent()){
            Person person1 = existingPerson.get();
            person1.setName(person.getName());
            person1.setSurname(person.getSurname());
            person1.setAge(person.getAge());
            return person1;
        }
        else {
            throw new Exception("Bu id li bir nesne bulunamadı.");
        }
    }
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        Optional<Person> person =getById(id);
        if (person.isPresent()){
            inMemoryList.remove(person.get());
            return true;
        }
        else {
            return false;
        }
    }

}
