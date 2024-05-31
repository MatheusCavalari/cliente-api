package matheus.cavalari.clienteApi.adapter.out;

import matheus.cavalari.clienteApi.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);
    Cliente findByMatricula(String matricula);
}
