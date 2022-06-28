package com.example.brasilprev.service.costumer.insert;

import com.example.brasilprev.domain.Costumer;

public record CostumerInsertOutput (Long id, String name, String cpf, String address) {

    public CostumerInsertOutput(Costumer costumer) {
        this(costumer.getId(), costumer.getName(), costumer.getCpf(), costumer.getAddress());
    }

}