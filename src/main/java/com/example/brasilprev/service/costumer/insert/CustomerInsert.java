package com.example.brasilprev.service.costumer.insert;

import com.example.brasilprev.domain.Customer;
import com.example.brasilprev.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerInsert {

    private final CustomerRepository repository;
    private final BCryptPasswordEncoder encoder;

    public CustomerInsert(CustomerRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public CustomerInsertOutput execute(CustomerInsertInput input) {
        Customer customer = new Customer(input);

        customer.setPassword(this.encoder.encode(customer.getPassword()));
        Customer saved = this.repository.save(customer);

        return new CustomerInsertOutput(saved);
    }
}
