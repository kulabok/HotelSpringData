package hotel.service.dao;

import hotel.entity.Bill;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface BillServiceDao {
    Bill addBill(Bill bill);
    void delete(int id);
    Bill getById(int id);
    Bill editBill(Bill bill);
    List<Bill> getAll();
}