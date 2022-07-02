package com.example.brasilprev.security;

import com.example.brasilprev.domain.Authorities;
import com.example.brasilprev.domain.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String cpf;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String cpf, String password, List<Authorities> authorities) {
        super();
        this.cpf = cpf;
        this.password = password;
        this.authorities = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).toList();
    }

    public UserDetailsImpl(Customer customer) {
        this(customer.getCpf(), customer.getPassword(), customer.getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}