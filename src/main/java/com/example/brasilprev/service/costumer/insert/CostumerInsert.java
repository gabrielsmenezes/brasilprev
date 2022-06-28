package com.example.brasilprev.service.costumer.insert;

import com.example.brasilprev.domain.Costumer;
import com.example.brasilprev.repository.CostumerRepository;
import org.springframework.stereotype.Service;

@Service
public class CostumerInsert {

    private final CostumerRepository repository;

    public CostumerInsert(CostumerRepository repository) {
        this.repository = repository;
    }

    public CostumerInsertOutput execute(CostumerInsertInput input) {
        Costumer costumer = new Costumer(input);

        Costumer saved = this.repository.save(costumer);

        return new CostumerInsertOutput(saved);
    }
}
