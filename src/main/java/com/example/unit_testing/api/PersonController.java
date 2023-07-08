package com.example.unit_testing.api;


import com.example.unit_testing.business.PersonService;
import com.example.unit_testing.infrasctructure.entity.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static java.lang.String.format;

@RestController
@RequestMapping(value = "teste-junit5", produces = {"aplication/json"})
@Slf4j
@Tag(name = "open-api")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @Operation(summary = "Busca pessoa por cpf", method = "GET")

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping("/cpf")
    @CrossOrigin(allowedHeaders = "*")
    public ResponseEntity<List<Person>> findProfessionalData(@RequestParam("cpf") String cpf){
        log.info(format("Buscando dados de pessoa por cpf = %s!", cpf));

        return ResponseEntity.ok(service.findPeopleByCPF(cpf));
    }
}
