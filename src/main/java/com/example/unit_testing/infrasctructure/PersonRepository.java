package com.example.unit_testing.infrasctructure;

import com.example.unit_testing.infrasctructure.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepository {

    public List<Person> findPerson(String cpf) {
        List<Person> personList = listPerson();
        return personList.stream()
                .filter(Objects::nonNull)
                .filter(person -> person.getCpf().equals(cpf))
                .toList();
    }

    private static List<Person> listPerson() {
        return Arrays.asList(
          new Person("Angelica", "12358569852", "Developer", 30, "Sao Paulo", "Rua das Cruzes", 54),
                new Person("Maria", "76342335281", "Product Owner", 25, "Rio de Janeiro", "Rua dos Carneiros", 29),
                new Person("Pedro", "16583712750", "Body Guard", 58, "Porto Alegre", "Rua dos Andrades", 94),
                new Person("Felipe", "52307186506", "Lawyer", 42, "Maceio", "Rua Central", 7859),
                new Person("João", "85051062709", "Accountant", 33, "São Paulo", "Rua das Hortencias", 5587),
                new Person("Elder", "27421118675", "Public Server", 58, "Porto Alegre", "Avenida Maua", 53)
        );
    }


}
