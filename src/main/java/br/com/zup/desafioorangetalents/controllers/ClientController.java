package br.com.zup.desafioorangetalents.controllers;

import br.com.zup.desafioorangetalents.models.Client;
import br.com.zup.desafioorangetalents.repositories.ClientRepository;
import br.com.zup.desafioorangetalents.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id) {

        var client = clientService.getById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client newClient) {
        var client = clientService.create(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @Valid @RequestBody Client client) {

        if (clientRepository.existsById(id)) {
            var newClient = clientService.update(id, client);
            return ResponseEntity.ok(newClient);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        if (clientRepository.existsById(id)) {
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
