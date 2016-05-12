package hotel.service.dao.impl;

import hotel.repository.BillRepository;
import hotel.service.dao.BillServiceDao;
import hotel.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class BillServiceDaoImpl implements BillServiceDao {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill addBill(Bill bill) {
        Bill savedBill = billRepository.saveAndFlush(bill);
        return savedBill;
    }

    @Override
    public void delete(int id) {
        billRepository.delete(id);
    }

    @Override
    public Bill getById(int id) {
        return billRepository.findOne(id);
    }

    @Override
    public Bill editBill(Bill bill) {
        return billRepository.saveAndFlush(bill);
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }
}
