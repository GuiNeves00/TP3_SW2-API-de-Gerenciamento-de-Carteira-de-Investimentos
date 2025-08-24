# TP3_SW2-API-de-Gerenciamento-de-Carteira-de-Investimentos
Trabalho Prático 3 da Disciplina Sistemas Web 2 - Guilherme Neves - 20.2.8173.

# API de Gerenciamento de Carteira de Investimentos

Esta é uma API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de uma carteira de investimentos, permitindo cadastrar, listar, filtrar, atualizar e remover ativos, além de visualizar um resumo consolidado da carteira.

## Pré-requisitos

Antes de começar, garanta que você tenha os seguintes softwares instalados em sua máquina:

- **Git:** Para clonar o projeto.
- **Java 17+:** Para compilar e executar a aplicação.
- **Apache Maven:** Para gerenciar as dependências e o build do projeto.
- **Docker e Docker Compose:** Para executar o banco de dados PostgreSQL em um container.
- **Postman:** (Opcional) Para testar a API de forma interativa.
- **Cliente de Banco de Dados:** (Opcional) Um cliente como [DBeaver](https://dbeaver.io/) ou [pgAdmin](https://www.pgadmin.org/) para visualizar os dados.

---

## Como Executar o Projeto

Siga os passos abaixo para colocar a aplicação em funcionamento.

### 1. Clone o Repositório

Abra seu terminal e clone este projeto para sua máquina local:

```bash
git clone <https://github.com/GuiNeves00/TP3_SW2-API-de-Gerenciamento-de-Carteira-de-Investimentos.git>
cd tp3_sw2
```

### 2. Inicie o Banco de Dados

Com o Docker em execução, utilize o Docker Compose para iniciar o container do PostgreSQL. Este comando irá baixar a imagem do Postgres (se necessário) e iniciá-la em segundo plano.

```bash
docker-compose up -d
```

O banco de dados estará acessível na porta `5432` da sua máquina.

### 3. Execute a Aplicação

O projeto utiliza o Maven Wrapper, que baixa e usa a versão correta do Maven automaticamente.

**Para macOS/Linux:**

```bash
./mvnw spring-boot:run
```

**Para Windows:**

```bash
.\mvnw.cmd spring-boot:run
```

Após alguns instantes, a aplicação estará em execução e pronta para receber requisições na porta `8080`.

---

## Testando com o Postman

Você pode testar todos os endpoints da API utilizando o Postman.

### Endpoints da API

#### 1. Cadastrar Novo Ativo

- **Método:** `POST`
- **URL:** `http://localhost:8080/investments`
- **Corpo (Body > raw > JSON):**
  ```json
  {
    "type": "ACAO",
    "symbol": "BBAS3",
    "quantity": 100,
    "purchasePrice": 19.68,
    "purchaseDate": "2025-07-31"
  }
  ```

#### 2. Listar Todos os Ativos

- **Método:** `GET`
- **URL:** `http://localhost:8080/investments`

#### 3. Filtrar Ativos por Tipo

- **Método:** `GET`
- **URL:** `http://localhost:8080/investments?type=ACAO`

#### 4. Atualizar um Ativo

- **Método:** `PUT`
- **URL:** `http://localhost:8080/investments/{id}` (substitua `{id}` pelo ID do ativo)
- **Corpo (Body > raw > JSON):**
  ```json
  {
    "type": "ACAO",
    "symbol": "BBAS3",
    "quantity": 150,
    "purchasePrice": 20.50,
    "purchaseDate": "2025-08-05"
  }
  ```

#### 5. Remover um Ativo

- **Método:** `DELETE`
- **URL:** `http://localhost:8080/investments/{id}` (substitua `{id}` pelo ID do ativo)

#### 6. Obter Resumo da Carteira

- **Método:** `GET`
- **URL:** `http://localhost:8080/investments/summary`

---

## Acessando o Banco de Dados

Todas as requisições (modificacões) devem ser refletidas no Banco de Dados.

### Dados de Conexão

Utilize os dados abaixo no seu cliente de banco de dados preferido (DBeaver, pgAdmin, etc...):

- **Host:** `localhost`
- **Porta:** `5432`
- **Banco de Dados:** `investment_portfolio`
- **Usuário:** `user`
- **Senha:** `password`
