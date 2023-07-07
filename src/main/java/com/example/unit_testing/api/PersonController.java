package com.example.unit_testing.api;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@RestController
@RequestMapping(value = "teste-junit5", produces = {"aplication/json"})
@Slf4j
@Tag(name = "open-api")
public class PersonController {

    private final PessoaService service;

    public PersonController(PessoaService service) {
        this.service = service;
    }

    @Operation(summary = "Busca pessoa por cpf", method = "GET");

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
