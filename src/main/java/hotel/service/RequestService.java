package hotel.service;

import hotel.entity.Request;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RequestService {
    Request addRequest(Request request);
    void delete(int id);
    Request getById(int id);
    Request editRequest(Request request);
    List<Request> getAll();
}
