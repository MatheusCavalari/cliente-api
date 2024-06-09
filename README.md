## API de Clientes

O microserviço cliente-api gerencia e armazena os usuários da plataforma de produtos digitais, garantindo a integridade e disponibilidade das informações.

### Como Utilizar

1. **Clonar o Repositório**:
   ```sh
    git clone https://github.com/MatheusCavalari/cliente-api.git

2. **Executar a Aplicação:**:
- **Porta do Servidor: 8081**
- **Banco de Dados: H2 em memória**
   ```sh
    mvn spring-boot:run

### Endpoints

1. **Criar Produto**: Endpoint para criar um novo usuário.
- **URL**: `/v1/cliente`
- **Método**: `POST`
- **Corpo da Requisição**:
  ```json
  {
    "id": 0,
    "nome": "string",
    "matricula": "string",
    "comunidade": "string",
    "email": "string",
    "senha": "string"
  }
  ```
- **Resposta de Sucesso**:
    - Código: `201 CREATED`
    - Corpo: Usuário criado

2. **Verificar Usuário Cadastrado**: Endpoint para verificar usuário cadastrado.
- **URL**: `/v1/cliente/login`
- **Método**: `POST`
- **Resposta de Sucesso**:
    - Código: `200 OK`
    - Corpo: Usuário cadastrado

### Dependências

- Spring Boot Web
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Boot Starter Data JPA
- H2 Database
- Lombok
- Springdoc OpenAPI
- JUnit

### Arquitetura Hexagonal

O microserviço cliente-api segue a arquitetura hexagonal (ou ports and adapters), que separa a lógica de negócios da implementação técnica. Nessa arquitetura, as camadas são organizadas da seguinte forma:

- **Domínio**: Contém as entidades de negócio, os serviços e as interfaces que definem as operações do domínio.
- **Aplicação**: Implementa a lógica de negócios usando os serviços do domínio.
- **Adaptadores**: São responsáveis por conectar o domínio à infraestrutura externa, como bancos de dados e serviços externos.

### Versão do Java

O microserviço foi desenvolvido utilizando Java 17, aproveitando as últimas funcionalidades e melhorias da linguagem.

Framework para desenvolvimento: Spring Boot 3.0.12.

### Gerenciamento de Dependências

O Maven foi utilizado como gerenciador de dependências e para realizar o build da aplicação. Ele simplifica o processo de compilação e gerenciamento de dependências, facilitando o desenvolvimento e a manutenção do projeto.

## Autor

Matheus Cavalari Barbosa
