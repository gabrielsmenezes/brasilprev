package com.example.brasilprev.controller;

import com.example.brasilprev.service.costumer.insert.CustomerInsert;
import com.example.brasilprev.service.costumer.insert.CustomerInsertInput;
import com.example.brasilprev.service.costumer.insert.CustomerInsertOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> execute(ConstraintViolationException exception) {
        List<String> paths = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return ResponseEntity.badRequest().body(paths);
    }

}
