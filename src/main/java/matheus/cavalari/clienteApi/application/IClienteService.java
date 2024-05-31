package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.domain.Cliente;

public interface IClienteService {
    Cliente getClienteByCpf(String cpf);
    Cliente buscarCliente(String cpf);
    Cliente armazenarCliente(Cliente cliente);
}
