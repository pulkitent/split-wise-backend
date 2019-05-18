package com.tw.beach.splitwise.service;

import com.tw.beach.splitwise.entity.Bill;
import com.tw.beach.splitwise.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private BillRepository billRepository = null;

    @Autowired
    BillService(BillRepository billRepository) throws ServiceLayerException {
        this.billRepository = billRepository;
    }

    public Bill create(Bill bill) throws ServiceLayerException {
        try {
            Bill savedBill = billRepository.save(bill);
            return savedBill;
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public Bill findBill(Long billId) throws ServiceLayerException {
        try {
            Bill bill = billRepository
                    .findById(billId)
                    .orElseThrow(() -> new RuntimeException("Bill not found on :: " + billId));
            return bill;
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public List<Bill> findAll() throws ServiceLayerException {
        try {
            return billRepository.findAll();
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public Bill delete(Long billId) throws ServiceLayerException {
        try {
            Optional<Bill> bill = billRepository.findById(billId);
            billRepository.delete(bill.get());
            return bill.get();
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }
}