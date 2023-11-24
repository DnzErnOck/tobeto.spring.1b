package com.tobeto.spring.b.controllers;

import com.tobeto.spring.b.dtos.requests.bill.AddBillRequest;
import com.tobeto.spring.b.dtos.requests.bill.UpdateBillRequest;
import com.tobeto.spring.b.dtos.requests.car.UpdateCarRequest;
import com.tobeto.spring.b.dtos.responses.bill.GetBillListResponse;
import com.tobeto.spring.b.dtos.responses.bill.GetBillResponse;
import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.entities.Brand;
import com.tobeto.spring.b.repositories.BillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<GetBillListResponse> getAll(){
        List<Bill> billList = billRepository.findAll();
        List<GetBillListResponse> getBillListResponses = new ArrayList<>();
        for (Bill bill:billList) {
            GetBillListResponse response = new GetBillListResponse();
            response.setTotalPrice(bill.getTotalPrice());
            response.setBillDate(bill.getBillDate());
            getBillListResponses.add(response);
        }
            return getBillListResponses;
    }
    @GetMapping("{id}")
    public GetBillResponse getById(@PathVariable int id){
        Bill bill =billRepository.findById(id).orElseThrow();
        GetBillResponse getBillResponse = new GetBillResponse();
        getBillResponse.setId(bill.getId());
        getBillResponse.setTotalPrice(bill.getTotalPrice());
        getBillResponse.setBillDate(bill.getBillDate());
        return getBillResponse;
    }
    @PostMapping
    public void add(@RequestBody AddBillRequest addBillRequest){
        Bill bill = new Bill();
        bill.setTotalPrice(addBillRequest.getTotalPrice());
        billRepository.save(bill);
    }
    @PutMapping("{id}")
    public void update(@RequestBody UpdateBillRequest updateBillRequest, @PathVariable int id){
        Optional<Bill> bill= billRepository.findById(id);
        if (bill.isPresent()){
            Bill foundBill=bill.get();
            foundBill.setTotalPrice(updateBillRequest.getTotalPrice());
            billRepository.save(foundBill);

        }
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        billRepository.deleteById(id);
    }
}
