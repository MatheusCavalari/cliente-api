package matheus.cavalari.clienteApi.adapter.in;

import matheus.cavalari.clienteApi.application.IClienteService;
import matheus.cavalari.clienteApi.application.exception.ClienteJaExisteException;
import matheus.cavalari.clienteApi.application.exception.ClienteNaoEncontradoException;
import matheus.cavalari.clienteApi.domain.Cliente;
import matheus.cavalari.clienteApi.domain.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final IClienteService clienteService;

    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Cliente cliente = clienteService.getClienteByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha());
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado para o email e senha informados");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Email ou senha inválidos: " + e.getMessage());
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado para o email e senha informados");
        } catch (Exception e) {
            logger.error("Erro ao obter o cliente pelo email e senha", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter o cliente pelo email e senha");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody Cliente clienteDto) {
        try {
            Cliente cliente = clienteService.armazenarCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (ClienteJaExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o cliente");
        }
    }

}
