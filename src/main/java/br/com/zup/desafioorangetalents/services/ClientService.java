package br.com.zup.desafioorangetalents.services;

import br.com.zup.desafioorangetalents.models.Client;
import br.com.zup.desafioorangetalents.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Integer id) {
        return clientRepository.findById(id);
    }

    public Client create(Client newClient) {
        newClient.setCreatedAt(OffsetDateTime.now());
        return clientRepository.save(newClient);
    }

    public Client update(Integer id, Client client) {

        var currentClient = clientRepository.findById(id);
        client.setId(id);
        client.setCreatedAt(currentClient.get().getCreatedAt());

        return clientRepository.save(client);
    }

    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }
}
