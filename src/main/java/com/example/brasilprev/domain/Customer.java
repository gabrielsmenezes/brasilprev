package com.example.brasilprev.domain;

import com.example.brasilprev.service.costumer.insert.CustomerInsertInput;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name should not be empty")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "cpf should not be empty")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotEmpty(message = "address should not be empty")
    @Column(nullable = false)
    private String address;

    @NotEmpty(message = "password should not be empty")
    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Authorities> authorities;

    public Customer() {
    }

    public Customer(Long id, String name, String cpf, String address, String password, List<Authorities> authorities) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.password = password;
        this.authorities = authorities;
    }

    public Customer(CustomerInsertInput input) {
        this(null, input.name(), input.cpf(), input.address(), input.password(), List.of(Authorities.ROLE_USER));
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }
}
