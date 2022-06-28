package com.example.brasilprev.service.costumer.insert;

import com.example.brasilprev.domain.Customer;

public record CustomerInsertOutput(Long id, String name, String cpf, String address) {

    public CustomerInsertOutput(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getCpf(), customer.getAddress());
    }

}