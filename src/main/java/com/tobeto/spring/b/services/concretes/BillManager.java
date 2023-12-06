package com.tobeto.spring.b.services.concretes;

import com.tobeto.spring.b.entities.Bill;
import com.tobeto.spring.b.repositories.BillRepository;
import com.tobeto.spring.b.services.abstracts.BillService;
import com.tobeto.spring.b.services.dtos.requests.bill.AddBillRequest;
import com.tobeto.spring.b.services.dtos.requests.bill.UpdateBillRequest;
import com.tobeto.spring.b.services.dtos.responses.address.GetAddressResponse;
import com.tobeto.spring.b.services.dtos.responses.bill.GetBillListResponse;
import com.tobeto.spring.b.services.dtos.responses.bill.GetBillResponse;
import com.tobeto.spring.b.services.dtos.responses.customer.GetListCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillManager implements BillService {
    private final BillRepository billRepository;

    @Override
    public List<GetBillListResponse> getAll() {
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

    @Override
    public GetBillResponse getById(int id) {
        Bill bill =billRepository.findById(id).orElseThrow();
        GetBillResponse getBillResponse = new GetBillResponse();
        getBillResponse.setId(bill.getId());
        getBillResponse.setTotalPrice(bill.getTotalPrice());
        getBillResponse.setBillDate(bill.getBillDate());
        return getBillResponse;
    }

    @Override
    public void add(AddBillRequest addBillRequest) {
        Bill bill = new Bill();
        bill.setTotalPrice(addBillRequest.getTotalPrice());
        billRepository.save(bill);
    }

    @Override
    public void update(UpdateBillRequest updateBillRequest, int id) {
        Optional<Bill> bill= billRepository.findById(id);
        if (bill.isPresent()){
            Bill foundBill=bill.get();
            foundBill.setTotalPrice(updateBillRequest.getTotalPrice());
            billRepository.save(foundBill);

        }
    }

    @Override
    public void delete(int id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<GetBillListResponse> searchDate(LocalDate billDate) {
        List<Bill> billList = billRepository.findByBillDateAfter(billDate);
        List<GetBillListResponse> getBillListResponses = new ArrayList<>();
        for (Bill bill:billList) {
            GetBillListResponse response = new GetBillListResponse();
            GetListCustomerResponse responseCustomer = new GetListCustomerResponse(bill.getCustomer().getName(),bill.getCustomer().getSurName());
            response.setTotalPrice(bill.getTotalPrice());
            response.setBillDate(bill.getBillDate());
            response.setCustomer(responseCustomer);
            getBillListResponses.add(response);
        }
        return getBillListResponses;
    }

    @Override
    public GetBillResponse getByPrice(Double totalPrice) {
        Bill bill = billRepository.findFirstByTotalPriceLessThanEqualOrderByTotalPriceDesc(totalPrice);
        GetBillResponse getBillResponse = new GetBillResponse();
        GetAddressResponse getAddressResponse=new GetAddressResponse(bill.getAddress().getPostalCode(),bill.getAddress().getAddressDetail());
        getBillResponse.setTotalPrice(bill.getTotalPrice());
        getBillResponse.setBillDate(bill.getBillDate());
        getBillResponse.setId(bill.getId());
        getBillResponse.setAddressResponse(getAddressResponse);
        return getBillResponse;
    }
}
