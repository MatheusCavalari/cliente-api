package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.domain.Cliente;

public interface IClienteService {
    Cliente getClienteByMatricula(String matricula);
    Cliente buscarCliente(String matricula);
    Cliente armazenarCliente(Cliente cliente);
}
