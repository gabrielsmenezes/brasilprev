package com.example.brasilprev.service.costumer.insert;

import com.example.brasilprev.domain.Customer;
import com.example.brasilprev.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerInsert {

    private final CustomerRepository repository;

    public CustomerInsert(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerInsertOutput execute(CustomerInsertInput input) {
        Customer customer = new Customer(input);

        Customer saved = this.repository.save(customer);

        return new CustomerInsertOutput(saved);
    }
}
