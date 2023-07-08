package com.example.unit_testing.business;

import com.example.unit_testing.infrasctructure.PersonRepository;
import com.example.unit_testing.infrasctructure.entity.Person;
import com.example.unit_testing.infrasctructure.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonService service;

    @Mock
    PersonRepository repository;

    Person person;

//    used when you have data that will be used on all
//    tests and that should be initialized before tests initialization

    @BeforeEach
    public void setUp() {
        person = new Person("Angelica", "12358569852", "Developer", 30, "Sao Paulo", "Rua das Cruzes", 54);
    }

    @Test
    void shouldFindPersonByCPFSuccessfully() {
        when(repository.findPerson(person.getCpf())).thenReturn(Collections.singletonList(person));

        List<Person> personList = service.findPeopleByCPF(person.getCpf());

        assertEquals(Collections.singletonList(person), personList);
        verify(repository).findPerson(person.getCpf());
        verifyNoMoreInteractions(repository);

    }


    @Test
    void shouldNotCallRepositoryIfParamCPFisNull() {
        final BusinessException e = assertThrows(BusinessException.class, () -> {
            service.findPeopleByCPF(null);
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Erro ao buscar pessoas por cpf = null"));
        assertThat(e.getCause(), notNullValue());
        assertThat(e.getCause().getMessage(), is("Cpf é obrigatorio!"));
        verifyNoInteractions(repository);
    }

    @Test
    void shouldTriggerExceptionWhenRepositoryFails() {
        when(repository.findPerson(person.getCpf())).thenThrow(new RuntimeException("Falha ao buscar pessoas por cpf"));

        final BusinessException e = assertThrows(BusinessException.class, () -> {
            service.findPeopleByCPF(person.getCpf());
        });

        assertThat(e.getMessage(), is(format("Erro ao buscar pessoas por cpf = %s", person.getCpf())));
        assertThat(e.getCause().getClass(), is(RuntimeException.class));
        assertThat(e.getCause().getMessage(), is("Falha ao buscar pessoas por cpf"));
        verify(repository).findPerson(person.getCpf());
        verifyNoMoreInteractions(repository);
    }

}
