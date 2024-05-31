package matheus.cavalari.clienteApi.application.exception;

public class ClienteJaExisteException extends RuntimeException {
    public ClienteJaExisteException(String mensagem) {
        super(mensagem);
    }
}
