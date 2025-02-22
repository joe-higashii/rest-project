# API RESTful com Spring Boot e JWT

Esta é uma API RESTful desenvolvida usando Spring Boot, Spring Security e JWT para autenticação. A aplicação foi construída com foco em segurança, escalabilidade, qualidade de código e documentação interativa.

---

## **Tecnologias Utilizadas**

- **Spring Boot 3.0**: Framework para construção da API.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **JWT (JSON Web Tokens)**: Autenticação baseada em tokens.
- **Spring Data JPA**: Acesso ao banco de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Swagger/OpenAPI**: Documentação interativa da API.
- **BCrypt**: Criptografia de senhas para maior segurança.
- **Lombok**: Redução de código boilerplate com anotações como `@RequiredArgsConstructor`.
- **ModelMapper**: Mapeamento automático entre entidades e DTOs, simplificando a conversão de dados.

---

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
   - Obter estatísticas de contratos agrupadas por status.

7. **Tratamento de Erros Detalhado**
   - Erros são capturados e respondidos com mensagens claras no formato padronizado:
     ```json
     {
       "timestamp": "2025-02-21T20:00:00",
       "status": 404,
       "error": "Not Found",
       "message": "Cliente não encontrado com ID: 5",
       "path": "/clients/5"
     }
     ```
   - Tipos de erros tratados:
     - `400 Bad Request`: Entradas inválidas (ex.: campos ausentes).
     - `404 Not Found`: IDs inexistentes.
     - `500 Internal Server Error`: Erros inesperados.

8. **Uso de DTOs**
   - DTOs personalizados para proteger informações sensíveis e retornar apenas os dados necessários na API:
     - `ClientDTO`, `CloudServiceDTO`, `UsageContractDTO`, etc.
   - DTOs de entrada (`CreateClientRequest`, `CreateUsageContractRequest`) validados com Bean Validation (`@Valid`, `@NotNull`, etc.).

---

## **Requisitos Prévios**

1. **Java 21:** Certifique-se de ter o Java 21 instalado e configurado corretamente em sua máquina.
2. **PostgreSQL:** Um banco de dados PostgreSQL deve estar configurado e rodando.
3. **Variáveis de Ambiente:** Configure as variáveis de ambiente abaixo para evitar credenciais sensíveis no código.

---

## **Configuração de Variáveis de Ambiente**

### Windows

1. Abra as configurações de **Variáveis de Ambiente** e adicione os valores abaixo:
   - `DATABASE_URL`: `jdbc:postgresql://localhost:5432/seu_banco`
   - `DATABASE_USERNAME`: `seu_usuario`
   - `DATABASE_PASSWORD`: `sua_senha`

### Linux/macOS

1. Abra o arquivo de configuração do shell:
   - Para Bash: `~/.bashrc`
   - Para Zsh: `~/.zshrc`
2. Adicione as variáveis:
   ```bash
   export DATABASE_URL="jdbc:postgresql://localhost:5432/seu_banco"
   export DATABASE_USERNAME="seu_usuario"
   export DATABASE_PASSWORD="sua_senha"
   ```
3. Atualize o shell:
   ```bash
   source ~/.bashrc
   ```

---

## **Principais Endpoints**

### **Autenticação**

1. **Registro de Usuário**
   - **`POST /auth/register`**
   - Exemplo de Entrada (JSON):
     ```json
     {
       "username": "admin",
       "password": "admin123",
       "role": "ADMIN"
     }
     ```
   - Respostas:
     - `200`: Usuário registrado com sucesso.
     - `400`: Username já existe ou papel inválido.

2. **Login**
   - **`POST /auth/login`**
   - Exemplo de Entrada (JSON):
     ```json
     {
       "username": "admin",
       "password": "admin123"
     }
     ```
   - Respostas:
     - `200`: Retorna um token JWT.
     - `401`: Usuário ou senha inválidos.

3. **Renovação de Token**
   - **`POST /auth/renew-token`**
   - Cabeçalho:
     ```bash
     Authorization: Bearer 
     ```

---

### **Clientes e Serviços**

1. **Listar Todos os Clientes**
   - **`GET /clients`**

2. **Criar um Cliente**
   - **`POST /clients`**
   - Exemplo de Entrada (JSON):
     ```json
     {
       "name": "Empresa Cliente",
       "email": "empresa@example.com"
     }
     ```

3. **Listar Serviços de um Cliente**
   - **`GET /clients/{id}/services`**
   - Lista todos os serviços contratados por um cliente específico.

4. **Listar Clientes de um Serviço**
   - **`GET /cloudservice/{id}/clients`**
   - Lista todos os clientes que contrataram um serviço específico.

---

### **Contratos**

1. **Criar um Contrato**
   - **`POST /usage-contracts`**
   - Exemplo de Entrada (JSON):
     ```json
     {
       "client": { "id": 1 },
       "service": { "id": 1 },
       "status": "ACTIVE"
     }
     ```

2. **Listar Contratos por Status**
   - **`GET /usage-contracts/status/{status}`**

3. **Filtrar Contratos por Intervalo de Datas**
   - **`GET /usage-contracts/date-range?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`**

4. **Obter Estatísticas de Contratos**
   - **`GET /usage-contracts/statistics`**

---

## **Notas sobre Segurança**

- Tokens JWT possuem expiração de 1 dia.
- Endpoints protegidos por papéis (`USER` ou `ADMIN`).
- Senhas armazenadas com criptografia robusta (BCrypt).

---

## **Refatorações Recentes**

- **ModelMapper:** Utilizado para conversão automática entre entidades e DTOs.
- **GlobalExceptionHandler:** Tratamento estruturado de erros com mensagens claras e detalhadas.
- **DTOs:** Introdução de DTOs para proteger dados sensíveis e personalizar as respostas da API.

---

## **Novas Funcionalidades**

1. **Paginação e Ordenação**
   - Suporte a paginação e ordenação em endpoints que retornam listas grandes.
   - Exemplo de uso:
     ```
     GET /clients?page=0&size=5&sort=name,asc
     ```

2. **Segurança Avançada com Spring Security**
   - Utilização de **`@PreAuthorize`** para proteger métodos em nível granular.
   - Restrições baseadas em papéis (`USER` vs `ADMIN`).
   - Endpoint para renovação de senha.

3. **Logs Estruturados**
   - Logs no formato JSON configurados com Logback e `logstash-logback-encoder`.
   - Integrado para monitoramento com Elastic Stack (ELK).

4. **Rate Limiting**
   - Limitação de requisições por IP configurada para **100 requisições por minuto**.
   - Retorna erro **429 Too Many Requests** quando o limite é excedido.

---

## **Notas Adicionais**

- Valores configuráveis (e.g., JWT_SECRET, mensagens de erro) são gerenciados na classe `AppConstants` para centralizar configurações importantes.
- A estrutura de resposta de erros foi padronizada com mensagens detalhadas.

---

## **Contato**

Caso tenha dúvidas, sugestões ou precise de ajuda:
- **Autor:** Joedson Mendes de Amorim
- **Email:** joedsondeamorim@outlook.com