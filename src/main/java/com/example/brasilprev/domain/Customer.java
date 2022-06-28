package com.example.brasilprev.domain;

import com.example.brasilprev.service.costumer.insert.CustomerInsertInput;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String address;

    public Customer() {}

    public Customer(Long id, String name, String cpf, String address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
    }

    public Customer(CustomerInsertInput input) {
        this(null, input.name(), input.cpf(), input.address());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
