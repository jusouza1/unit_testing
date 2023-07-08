package com.example.unit_testing.business;

import com.example.unit_testing.infrasctructure.PersonRepository;
import com.example.unit_testing.infrasctructure.entity.Person;
import com.example.unit_testing.infrasctructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findPeopleByCPF(String cpf) {
        try {
            notNull(cpf, "Cpf é obrigatorio!");
            return repository.findPerson(cpf);
        } catch(final Exception e) {
            throw new BusinessException(format("Erro ao buscar pessoas por cpf = %s", cpf), e);
        }
    }
}
