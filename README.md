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
