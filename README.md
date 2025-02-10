# API RESTful com Spring Boot e JWT

Esta é uma API RESTful desenvolvida com Spring Boot, utilizando JWT para autenticação, Spring Security para autorização, Spring Data JPA para acesso ao banco de dados e Swagger para documentação interativa.

## Tecnologias Utilizadas

- **Spring Boot 3.0**
- **Spring Security**
- **JWT (JSON Web Tokens):** autenticação baseada em token.
- **Spring Data JPA:** acesso ao banco de dados.
- **Swagger/OpenAPI:** documentação interativa.
- **BCrypt:** criptografia de senhas.
- **Lombok:** simplificação do código.

## Funcionalidades

- **Registro e Login de Usuários**
  - Registro de novos usuários com validação de papéis (USER, ADMIN).
  - Login com retorno de token JWT.
  
- **Proteção de Endpoints com JWT**
  - Endpoints protegidos com autenticação baseada em token JWT.
  - Diferenciação de acesso com base no papel (USER ou ADMIN).
  
- **Renovação de Tokens JWT**
  - Endpoint para renovar tokens válidos antes de expirar.
  
- **Documentação Interativa**
  - Swagger disponível em `/swagger-ui/index.html`.

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

## Como Rodar o Projeto

1. Clone o repositório:
  - git clone https://github.com/joe-higashii/rest-project.git
2. Execute a aplicação:
`mvn spring-boot:run`
3. Acesse os endpoints:
- Swagger: `http://localhost:8080/swagger-ui/index.html`
- Registro: `POST /auth/register`
- Login: `POST /auth/login`

## Endpoints

### Autenticação:
- `POST /auth/register`: Registro de novos usuários.
- `POST /auth/login`: Login e retorno de token JWT.
- `POST /auth/renew-token`: Renovação de token JWT.

### Admin:
- `GET /admin/test`: Teste de endpoint restrito a ADMIN.

### Cloud Vendors:
- CRUD completo: `GET`, `POST`, `PUT`, `DELETE /cloudvendor`.