package matheus.cavalari.clienteApi.application;

import matheus.cavalari.clienteApi.adapter.out.ClienteRepository;
import matheus.cavalari.clienteApi.application.exception.ClienteJaExisteException;
import matheus.cavalari.clienteApi.application.exception.ClienteNaoEncontradoException;
import matheus.cavalari.clienteApi.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClienteByEmailAndSenha_NotFound() {
        when(clienteRepository.findAll()).thenReturn(List.of());

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.getClienteByEmailAndSenha("test@example.com", "wrongpassword");
        });
    }

    @Test
    void armazenarCliente() {
        Cliente cliente = Cliente.builder()
                .matricula("12345")
                .build();

        when(clienteRepository.findAll()).thenReturn(List.of());

        Cliente result = clienteService.armazenarCliente(cliente);

        assertNotNull(result);
        assertEquals("12345", result.getMatricula());
        verify(clienteRepository, times(1)).findAll();
    }

}
