package com.example.brasilprev.security;

import com.example.brasilprev.domain.Customer;
import com.example.brasilprev.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository repository;

    public UserDetailsServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf){
        Customer customer = repository.findCustomerByCpfEquals(cpf).orElseThrow(() -> {
            throw new UsernameNotFoundException(cpf);
        });

        return new UserDetailsImpl(customer);
    }
}