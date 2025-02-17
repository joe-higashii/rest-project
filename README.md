# API RESTful com Spring Boot e JWT

Esta é uma API RESTful desenvolvida usando Spring Boot, Spring Security e JWT para autenticação. A aplicação foi construída com foco em segurança, escalabilidade e documentação interativa.

## **Tecnologias Utilizadas**

- **Spring Boot 3.0**: Framework para construir a aplicação.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **JWT (JSON Web Tokens)**: Autenticação baseada em tokens.
- **Spring Data JPA**: Acesso ao banco de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Swagger/OpenAPI**: Documentação interativa da API.
- **BCrypt**: Criptografia de senhas.
- **Lombok**: Redução de código boilerplate com `@RequiredArgsConstructor` e outras anotações.

## **Funcionalidades**

1. **Registro e Login de Usuários**
   - Registro de novos usuários (`USER` ou `ADMIN`) com validação de papéis.
   - Login com autenticação via token JWT.

2. **Proteção de Endpoints**
   - Uso de tokens JWT para proteger endpoints.
   - Diferenciação de permissões com base nos papéis (`USER` ou `ADMIN`).

3. **Renovação de Tokens**
   - Endpoint para renovar tokens JWT antes de expirar.

4. **Documentação Interativa**
   - Swagger disponível em: `http://localhost:8080/swagger-ui/index.html`.

5. **CRUD Completo**
   - Gerenciamento de fornecedores (`CloudVendor`), serviços na nuvem (`CloudService`), clientes (`Client`) e contratos de uso (`UsageContract`).

6. **Consultas Personalizadas**
   - Listar serviços contratados por um cliente específico.
   - Listar clientes que contrataram um serviço específico.
   - Filtrar contratos por status (e.g., `ACTIVE`, `CANCELLED`) ou intervalo de datas.

7. **Tratamento de Erros**
   - `400 Bad Request`: Entradas inválidas (ex.: campos ausentes).
   - `404 Not Found`: IDs inexistentes.
   - `500 Internal Server Error`: Erros inesperados (tratamento global).

## **Requisitos Prévios**

1. **Java 21:** Certifique-se de que o Java 21 esteja instalado e configurado.
2. **PostgreSQL:** O banco de dados PostgreSQL deve estar configurado e rodando.
3. **Variáveis de Ambiente:** Configure as variáveis de ambiente para evitar expor credenciais sensíveis no código.

## Configuração de Variáveis de Ambiente

Siga as instruções abaixo para configurar variáveis de ambiente na sua máquina:

### Windows

1. Pressione `Windows + S` e procure por **"Variáveis de Ambiente"**. Clique em **"Editar as Variáveis de Ambiente do Sistema"**.
2. Adicione as variáveis:
   - `DATABASE_URL`: `jdbc:postgresql://localhost:5432/seu_banco`
   - `DATABASE_USERNAME`: `seu_usuario`
   - `DATABASE_PASSWORD`: `sua_senha`

### Linux/macOS

1. Abra o arquivo do shell correspondente:
   - Para Bash: `~/.bashrc`
   - Para Zsh: `~/.zshrc`
2. Adicione as variáveis:
```
  export DATABASE_URL="jdbc:postgresql://localhost:5432/seu_banco"
  export DATABASE_USERNAME="seu_usuario"
  export DATABASE_PASSWORD="sua_senha"
```
3. Atualize o shell:
`source ~/.bashrc`

## **Principais Endpoints**

### **Autenticação**

1. **Registro de Usuário**
- **`POST /auth/register`**
- Exemplo de Entrada (JSON):
```
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}
```
- **Respostas**:
- `200`: Usuário registrado com sucesso.
- `400`: Username já cadastrado ou papel inválido.

2. **Login**
- **`POST /auth/login`**
- Exemplo de Entrada (JSON):
```
{
"username": "admin",
"password": "admin123"
}
```
- **Respostas**:
- `200`: Retorna um token JWT.
- `401`: Usuário ou senha inválidos.

3. **Renovação de Token**
- **`POST /auth/renew-token`**
- **Cabeçalho**:
`Authorization: Bearer <seu-token-jwt>`

### **Clientes e Serviços**

1. **Listar Serviços de um Cliente**
- **`GET /clients/{id}/services`**
- Lista todos os serviços contratados por um cliente específico.

2. **Listar Clientes de um Serviço**
- **`GET /cloudservice/{id}/clients`**
- Lista todos os clientes que contrataram um serviço específico.

### **Contracts**

1. **Filtrar Contratos por Status**
- **`GET /usage-contracts/status/{status}`**
- Retorna contratos com o status fornecido.

2. **Filtrar Contratos por Intervalo de Datas**
- **`GET /usage-contracts/date-range?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`**
- Retorna contratos dentro do intervalo de datas fornecido.

## **Notas sobre Segurança**

- Tokens JWT possuem expiração de 1 dia.
- Endpoints protegidos por papéis (`USER` ou `ADMIN`).
- Senhas armazenadas criptografadas com BCrypt.

## **Refatoração Recente**

O projeto foi atualizado para usar **injeção de dependências via construtor** com o Lombok:
- Substituímos `@Autowired` por `@RequiredArgsConstructor` em todas as classes.
- Isso melhora a imutabilidade e a testabilidade do código.

## **Contato**

Caso tenha dúvidas ou sugestões, entre em contato:
- **Autor:** Joedson Mendes de Amorim
- **Email:** joedsondeamorim@outlook.com
