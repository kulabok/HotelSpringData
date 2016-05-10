package hotel.service.impl;

import hotel.entity.Request;
import hotel.repository.RequestRepository;
import hotel.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request addRequest(Request request) {
        Request savedRequest = requestRepository.saveAndFlush(request);
        return savedRequest;
    }

    @Override
    public void delete(int id) {
        requestRepository.delete(id);
    }

    @Override
    public Request getById(int id) {
        return requestRepository.findOne(id);
    }

    @Override
    public Request editRequest(Request request) {
        return requestRepository.saveAndFlush(request);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }
}
