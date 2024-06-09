package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.adapter.out.ClienteRepository;
import matheus.cavalari.clienteApi.application.exception.ClienteJaExisteException;
import matheus.cavalari.clienteApi.application.exception.ClienteNaoEncontradoException;
import matheus.cavalari.clienteApi.domain.Cliente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService implements IClienteService {

    private Map<String, Cliente> clientes;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clientes = new HashMap<>();
        List<Cliente> clienteList = clienteRepository.findAll();
        for (Cliente cliente : clienteList) {
            clientes.put(cliente.getMatricula(), cliente);
        }
        // Adicionar clientes de exemplo
        clientes.put("12345678", Cliente.builder()
                .id(1L)
                .matricula("12345678")
                .nome("Michel Jordan")
                .email("michael@hotmail.com")
                .senha("12345678")
                .build());
        clientes.put("87654321", Cliente.builder()
                .id(2L)
                .matricula("87654321")
                .nome("Lebron James")
                .email("james@hotmail.com")
                .senha("87654321")
                .build());
        clientes.put("12348765", Cliente.builder()
                .id(3L)
                .matricula("12348765")
                .nome("Maradonna")
                .email("madonna@hotmail.com")
                .senha("12348765")
                .build());
    }

    public Cliente getClienteByEmailAndSenha(String email, String senha) {
        if (email == null || senha == null) {
            throw new ClienteNaoEncontradoException("Email ou senha não podem ser nulos");
        }

        for (Cliente cliente : clientes.values()) {
            if (email.equals(cliente.getEmail()) && senha.equals(cliente.getSenha())) {
                return cliente;
            }
        }

        throw new ClienteNaoEncontradoException("Cliente não encontrado para o email e senha informados");
    }


    @Override
    public Cliente armazenarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getMatricula())) {
            throw new ClienteJaExisteException("Cliente com Matricula " + cliente.getMatricula() + " já existe.");
        }
        clientes.put(cliente.getMatricula(), cliente);
        return cliente;
    }
}
