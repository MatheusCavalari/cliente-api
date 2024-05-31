package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.adapter.out.ClienteRepository;
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
            clientes.put(cliente.getCpf(), cliente);
        }
        // Adicionar clientes de exemplo
        clientes.put("111.111.111-11", Cliente.builder()
                .id(1L)
                .cpf("111.111.111-11")
                .nome("Michel Jordan")
                .build());
        clientes.put("222.222.222-22", Cliente.builder()
                .id(2L)
                .cpf("222.222.222-22")
                .nome("Lebron James")
                .build());
        clientes.put("333.333.333-33", Cliente.builder()
                .id(3L)
                .cpf("333.333.333-33")
                .nome("Maradonna")
                .build());
    }

    @Override
    public Cliente  getClienteByCpf(String cpf) {
        if (!verificarCPF(cpf)) {
            throw new IllegalArgumentException("O formato válido é  DDD.DDD.DDD-DD");
        }
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado para o CPF informado");
        }

        return cliente;
    }

    @Override
    public Cliente buscarCliente(String cpf) {
        return clientes.get(cpf);
    }

    public boolean verificarCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    @Override
    public Cliente armazenarCliente(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
        return cliente;
    }
}
