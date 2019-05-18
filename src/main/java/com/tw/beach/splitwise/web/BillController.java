package com.tw.beach.splitwise.web;

import com.tw.beach.splitwise.entity.Bill;
import com.tw.beach.splitwise.service.BillService;
import com.tw.beach.splitwise.service.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    private BillService billService = null;

    @Autowired
    BillController(BillService billService) {
        this.billService = billService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bills")
    public Bill createBill(@RequestBody Bill bill) throws ServiceLayerException {
        return billService.create(bill);
    }

    @GetMapping("/bills/{id}")
    public Bill fetchBillById(@PathVariable(value = "id") Long billId) throws ServiceLayerException {
        Bill bill = billService.findBill(billId);
        return bill;
    }

    @GetMapping("/bills")
    public List<Bill> fetchAllBills() throws ServiceLayerException {
        return billService.findAll();
    }

    @DeleteMapping("bills/{id}")
    public Bill deleteBillbyId(@PathVariable(value = "id") Long billId) throws ServiceLayerException {
        return billService.delete(billId);
    }
}