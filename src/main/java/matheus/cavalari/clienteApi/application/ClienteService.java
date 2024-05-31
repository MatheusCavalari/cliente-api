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
        clientes.put("111111111", Cliente.builder()
                .id(1L)
                .matricula("111111111")
                .nome("Michel Jordan")
                .build());
        clientes.put("222222222", Cliente.builder()
                .id(2L)
                .matricula("222222222")
                .nome("Lebron James")
                .build());
        clientes.put("333333333", Cliente.builder()
                .id(3L)
                .matricula("333333333")
                .nome("Maradonna")
                .build());
    }

    @Override
    public Cliente  getClienteByMatricula(String matricula) {
        Cliente cliente = buscarCliente(matricula);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado para a Matricula informada");
        }

        return cliente;
    }

    @Override
    public Cliente buscarCliente(String matricula) {
        return clientes.get(matricula);
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
