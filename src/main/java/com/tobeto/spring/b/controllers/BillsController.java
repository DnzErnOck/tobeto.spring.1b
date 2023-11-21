package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.repositories.BillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bill")
public class BillsController {
    private final BillRepository billRepository;

    public BillsController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping
    public List<Bill> getAll(){
        return billRepository.findAll();
    }
    @GetMapping("{id}")
    public Bill getById(@PathVariable int id){
        return billRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Bill bill){
        billRepository.save(bill);
    }
    @PutMapping("{id}")
    public Bill update(@RequestBody Bill newBill,@PathVariable int id){
        Optional<Bill> bill= billRepository.findById(id);
        if (bill.isPresent()){
            Bill foundBill=bill.get();
            foundBill.setId(newBill.getId());
            foundBill.setBillDate(newBill.getBillDate());
            foundBill.setTotalPrice(newBill.getTotalPrice());
            billRepository.save(foundBill);
            return foundBill;
        }
        else {
            return null;
        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        billRepository.deleteById(id);
    }
}
