# API para cadastro de clientes em um banco fictício.

Criando um exemplo de API com Java 11 e Spring. O banco de dados utilizado para persistência foi o MySQL.

### O que pode ser feito com esta API
- Cadastrar novo cliente;
- Atualizar um cliente;
- Buscar todos os clientes cadastrados;
- Buscar um cliente por id;
- Excluir um cliente.

### Códigos HTTP da API
- **200**: Solicitação bem-sucedida;
- **201**: Um novo cliente foi cadastrado;
- **204**: Cliente foi excluído com sucesso;
- **400**: A corpo da requisição é inválido;
- **404**: Cliente não encontrado;
- **409**: Conflito -> CPF ou E-mail já existente.

**OBS1.:** antes de executar este projeto é necessário configurar as credenciais do banco de dados no arquivo *application.properties*.

**OBS2.:** em *main/resources/db/create_table.sql* se encontra os comandos *SQL* para criação das tabelas.

**OBS3.:** em *main/resources/documentation/postman_documentation.json* se encontra a documentação da API.