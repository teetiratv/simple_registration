package com.example.registration.security;

import com.example.registration.entity.Person;
import com.example.registration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<Person> person = personRepository.findByUsername(username);

        return PersonPrincipal.create(person.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<Person> person = personRepository.findById(id);

        return PersonPrincipal.create(person.get());
    }
}
