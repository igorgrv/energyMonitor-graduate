
package graduate.energymonitor.domains.person.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.energymonitor.domains.person.entity.Person;
import graduate.energymonitor.domains.person.entity.dto.PersonDto;
import graduate.energymonitor.domains.person.service.PersonService;
import graduate.energymonitor.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("people")
@Tag(name = "People", description = "Methods for manipulating person data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Person Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Person ID not found", value = "{\"statusCode\":404,\"message\":\"Person ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class PersonController {

    private static final String PERSON_NOT_FOUND = "Person not found";
    private final PersonService personService;

    @Operation(summary = "Get all the people", description = "Method for getting all the people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all People", content = @Content(schema = @Schema(implementation = PersonDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.findAll();
        return ResponseEntity.ok().body(people);
    }

    @Operation(summary = "Get a person by ID", description = "Method to get a person based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = PersonDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id_person}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id_person") UUID id) {
        Person person = personService.findById(id).orElseThrow(() -> new NotFoundException(PERSON_NOT_FOUND));
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "Create a person", description = "Method to crete a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Person successfully created", content = @Content(schema = @Schema(implementation = Person.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - Person already exists", content = @Content(examples = {
                    @ExampleObject(summary = "Person already exists", value = "{\"statusCode\":409,\"message\":\"Person: person=Igor Romero, birth=2023-07-10, gender=MALE, relative=FATHER,  already exists\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("{username}")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDto request, @PathVariable String username) {
        Person person = personService.addPerson(request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @Operation(summary = "Update a person", description = "Method to update anexisting person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Personsuccessfully updated", content = @Content(schema = @Schema(implementation = Person.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PutMapping("{id_person}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id_person") UUID id,
            @Valid @RequestBody PersonDto request) {
        Person updatedPerson = personService.updatePerson(id, request);
        return ResponseEntity.ok().body(updatedPerson);
    }

    @Operation(summary = "Delete a person", description = "Method to delete an existing person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - Person successfully deleted", content = @Content(schema = @Schema(implementation = Person.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @DeleteMapping("{id_person}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id_person") UUID id) {
        Person deletedPerson = personService.deletePerson(id);
        return ResponseEntity.ok().body(deletedPerson);
    }
}
