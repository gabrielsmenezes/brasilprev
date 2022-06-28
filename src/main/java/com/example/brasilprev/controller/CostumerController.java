package com.example.brasilprev.controller;

import com.example.brasilprev.service.costumer.insert.CostumerInsert;
import com.example.brasilprev.service.costumer.insert.CostumerInsertInput;
import com.example.brasilprev.service.costumer.insert.CostumerInsertOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    private final CostumerInsert insert;

    public CostumerController(CostumerInsert insert) {
        this.insert = insert;
    }

    @PostMapping
    public ResponseEntity<CostumerInsertOutput> insert(@RequestBody CostumerInsertInput input) {
        CostumerInsertOutput output = this.insert.execute(input);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(output.id()).toUri();

        return ResponseEntity.created(uri).body(output);
    }
}
