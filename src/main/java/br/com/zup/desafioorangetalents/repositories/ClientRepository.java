package br.com.zup.desafioorangetalents.repositories;

import br.com.zup.desafioorangetalents.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
