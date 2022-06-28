package com.example.brasilprev.controller;

import com.example.brasilprev.service.costumer.insert.CustomerInsert;
import com.example.brasilprev.service.costumer.insert.CustomerInsertInput;
import com.example.brasilprev.service.costumer.insert.CustomerInsertOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/costumer")
public class CustomerController {

    private final CustomerInsert insert;

    public CustomerController(CustomerInsert insert) {
        this.insert = insert;
    }

    @PostMapping
    public ResponseEntity<CustomerInsertOutput> insert(@RequestBody CustomerInsertInput input) {
        CustomerInsertOutput output = this.insert.execute(input);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(output.id()).toUri();

        return ResponseEntity.created(uri).body(output);
    }
}
