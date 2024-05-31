package matheus.cavalari.clienteApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Matheus",
                        email = "matheuscav@hotmail.com"
                ),
                description = "O Microserviço Cliente Api, é responsável pela criação de usuário e pela busca por matrícula",
                title = "Cliente API",
                version = "1.0"
        )
)

public class OpenApiConfig {
}
