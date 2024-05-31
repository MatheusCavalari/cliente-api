package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.domain.Cliente;

public interface IClienteService {
    Cliente getClienteByEmailAndSenha(String email, String senha);
    Cliente armazenarCliente(Cliente cliente);
}
