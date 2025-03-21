package org.ticket.platform.ticket_platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticket.platform.ticket_platform.model.Operator;
import org.ticket.platform.ticket_platform.repository.OperatorRepository;

@Service
public class OperatorService {

    @Autowired
    OperatorRepository operatorRepository;

    public List<Operator> getAllOperators(){
        return operatorRepository.findAll();
    }

}
