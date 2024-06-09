package matheus.cavalari.clienteApi.adpter.in;

import matheus.cavalari.clienteApi.adapter.in.ClienteController;
import matheus.cavalari.clienteApi.application.IClienteService;
import matheus.cavalari.clienteApi.application.exception.ClienteJaExisteException;
import matheus.cavalari.clienteApi.application.exception.ClienteNaoEncontradoException;
import matheus.cavalari.clienteApi.domain.Cliente;
import matheus.cavalari.clienteApi.domain.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {
    @Mock
    private IClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        Cliente cliente = new Cliente();
        when(clienteService.getClienteByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha())).thenReturn(cliente);

        ResponseEntity<?> responseEntity = clienteController.login(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(clienteService, times(1)).getClienteByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());
    }

    @Test
    void login_ClienteNaoEncontrado() {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        when(clienteService.getClienteByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha())).thenThrow(new ClienteNaoEncontradoException("Cliente não encontrado"));

        ResponseEntity<?> responseEntity = clienteController.login(loginRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(clienteService, times(1)).getClienteByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());
    }

    @Test
    void criarCliente() {
        Cliente cliente = new Cliente();
        when(clienteService.armazenarCliente(cliente)).thenReturn(cliente);

        ResponseEntity<?> responseEntity = clienteController.criarCliente(cliente);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(clienteService, times(1)).armazenarCliente(cliente);
    }

    @Test
    void criarCliente_JaExiste() {
        Cliente cliente = new Cliente();
        when(clienteService.armazenarCliente(cliente)).thenThrow(new ClienteJaExisteException("Cliente já existe"));

        ResponseEntity<?> responseEntity = clienteController.criarCliente(cliente);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        verify(clienteService, times(1)).armazenarCliente(cliente);
    }
}
