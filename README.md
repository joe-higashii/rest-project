# API RESTful com Spring Boot e JWT

Esta é uma API RESTful desenvolvida usando Spring Boot, Spring Security e JWT para autenticação. A aplicação foi construída com foco em segurança, escalabilidade e documentação interativa.

---

## **Tecnologias Utilizadas**

- **Spring Boot 3.0**: Framework para construir a aplicação.
- **Spring Security**: Gerenciamento de autenticação e autorização.
- **JWT (JSON Web Tokens)**: Autenticação baseada em tokens.
- **Spring Data JPA**: Acesso ao banco de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Swagger/OpenAPI**: Documentação interativa da API.
- **BCrypt**: Criptografia de senhas.
- **Lombok**: Redução de código boilerplate (getters, setters, etc.).

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

5. **CRUD de Fornecedores**
   - Endpoints para criar, ler, atualizar e deletar fornecedores.
   - Acesso restrito a usuários autenticados.

---

## **Requisitos Prévios**

1. **Java 21:** Certifique-se de que o Java 21 esteja instalado e configurado.
2. **PostgreSQL:** O banco de dados PostgreSQL deve estar configurado e rodando.
3. **Variáveis de Ambiente:** Configure as variáveis de ambiente para evitar expor credenciais sensíveis no código.

---

## Configuração de Variáveis de Ambiente

Antes de rodar a aplicação, é necessário configurar variáveis de ambiente para evitar expor credenciais sensíveis no código. Siga as instruções abaixo para configurar sua máquina:

### Windows

1. Pressione `Windows + S` e procure por **"Variáveis de Ambiente"**. Clique em **"Editar as Variáveis de Ambiente do Sistema"**.
2. Na janela que abrir, clique em **"Variáveis de Ambiente"**.
3. Adicione as seguintes variáveis na seção de **Variáveis do Usuário** ou **Variáveis do Sistema**:
   - `DATABASE_URL`: `jdbc:postgresql://localhost:5432/seu_banco`
   - `DATABASE_USERNAME`: `usuario`
   - `DATABASE_PASSWORD`: `senha`
4. Salve e reinicie o terminal ou IDE.

### Linux

1. Abra o arquivo do shell correspondente:
   - Para Bash: `~/.bashrc`
   - Para Zsh: `~/.zshrc`
2. Adicione as seguintes linhas ao final do arquivo:
- `export DATABASE_URL='jdbc:postgresql://localhost:5432/seu_banco'`
- `export DATABASE_USERNAME='usuario'`
- `export DATABASE_PASSWORD='senha'`
3. Salve e rode o comando:

source `~/.bash_profile`
ou source `~/.zshrc`

### macOS

1. Abra o arquivo do shell correspondente:
- Para Bash: `~/.bash_profile`
- Para Zsh (macOS Catalina ou mais recente): `~/.zshrc`
2. Adicione as seguintes linhas:
- `export DATABASE_URL='jdbc:postgresql://localhost:5432/seu_banco'`
- `export DATABASE_USERNAME='usuario'`
- `export DATABASE_PASSWORD='senha'`
3. Salve e rode o comando:

source `~/.bash_profile`
ou source `~/.zshrc`

### Executando a Aplicação

Depois que as variáveis forem configuradas, você pode iniciar a aplicação normalmente. O Spring Boot automaticamente buscará as variáveis de ambiente e aplicará como configuração.

---

## Como Rodar o Projeto

1. Clone o repositório:
  - git clone https://github.com/joe-higashii/rest-project.git
2. Execute a aplicação:
`mvn spring-boot:run`
3. Acesse os endpoints:
- Swagger: `http://localhost:8080/swagger-ui/index.html`
- Registro: `POST /auth/register`
- Login: `POST /auth/login`


---

## **Principais Endpoints**

### **Autenticação**

1. **Registro de Usuário**
- **`POST /auth/register`**
- **Request Body (JSON)**:
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
- **Request Body (JSON)**:
  ```
  {
    "username": "admin",
    "password": "admin123"
  }
  ```
- **Respostas**:
  - `200`: Retorna um token JWT.
    ```
    {
      "message": "Login realizado com sucesso!",
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp..."
    }
    ```
  - `401`: Usuário ou senha inválidos.

3. **Renovação de Token**
- **`POST /auth/renew-token`**
- **Cabeçalho**:
  ```
  Authorization: Bearer <seu-token-jwt>
  ```
- **Respostas**:
  - `200`: Retorna o novo token.
  ```
  {
    "message": "Token renovado com sucesso!",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6Ik..."
  }
  ```
  - `401`: Token expirado ou inválido.

---

### **Admin**

- **Restrição com Papel ADMIN**
- **`GET /admin/test`**
- Apenas acessível por usuários com o papel `ADMIN`.
- **Respostas**:
 - `200`: "Acesso permitido para ADMIN."
 - `403`: Acesso negado.

---

### **Cloud Vendors**

1. **CRUD Completo**
- **`GET /cloudvendor/{vendorId}`**: Retorna os detalhes de um fornecedor específico.
- **`POST /cloudvendor`**: Cria um novo fornecedor.
- **`PUT /cloudvendor/{vendorId}`**: Atualiza os dados de um fornecedor existente.
- **`DELETE /cloudvendor/{vendorId}`**: Remove um fornecedor pelo ID.

---

## **Notas sobre Segurança**

- Os tokens JWT possuem expiração de 1 dia.
- Endpoints críticos estão protegidos por papéis (roles) no Spring Security.
- Senhas são armazenadas criptografadas com BCrypt.

---

## **Contribuição**

Contribuições são bem-vindas! Se você quiser sugerir melhorias ou corrigir problemas:
1. Faça um fork do projeto.
2. Crie uma nova branch para suas alterações:
- git checkout -b minha-branch
3. Envie suas alterações em um Pull Request.

---

## **Contato**

Caso tenha dúvidas ou sugestões, entre em contato:
- **Autor:** Joedson Mendes de Amorim
- **Email:** joedsondeamorim@outlook.com
