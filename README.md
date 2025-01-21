# Desafio PicPay - Backend

Este repositório contém a solução para o desafio técnico do PicPay. O objetivo deste projeto é gerenciar carteiras digitais com funcionalidades como criação, consulta e atualização. A aplicação foi desenvolvida seguindo boas práticas de arquitetura e utilizando tecnologias modernas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** (Spring Data JPA, Spring Web, Springdoc OpenAPI)
- **MySQL**
- **Docker e Docker Compose**

---

## Como Executar o Projeto

### **1. Pré-requisitos**

- Docker e Docker Compose instalados.
- Java 21 instalado.
- Maven configurado.

### **2. Executando com Docker Compose**

1. Clone o repositório:
   ```bash
   git clone https://github.com/WallissonAlv/desafio-backend-Places.git
   cd desafio-backend-Places
   ```

2. Suba os containers:
   ```bash
   docker-compose up
   ```

3. A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

4. Acesse a documentação da API (Swagger) em:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

### **3. Executando Localmente**

1. Certifique-se de que o MySQL está em execução localmente na porta `3306`.

2. Atualize o arquivo `application.properties` para apontar para o banco local:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/picpaydb
   spring.datasource.username=admin
   spring.datasource.password=root
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação e a documentação conforme descrito acima.

---

## Endpoints Principais

### **1. Criar Carteira**
- **POST** `/wallets`
- **Body (JSON):**
  ```json
  {
    "description": "Nova Carteira"
  }
  ```
- **Resposta:**
  ```json
  {
    "id": 1,
    "description": "Nova Carteira",
    "createdAt": "2025-01-21T10:00:00Z"
  }
  ```

### **2. Consultar Carteiras**
- **GET** `/wallets`
- **Resposta:**
  ```json
  [
    {
      "id": 1,
      "description": "Nova Carteira",
      "createdAt": "2025-01-21T10:00:00Z"
    }
  ]
  ```

---

## Estrutura do Projeto

```plaintext
src/main/java
├── br.com.wallissonalves.picpay
│   ├── client          # Sistema Externos
│   ├── controller      # Contém os endpoints da API
│   ├── exception       # Tratamentos de Exceções
│   ├── entity          # Entidades do banco de dados
│   ├── repository      # Interfaces para acesso ao banco de dados
│   ├── service         # Regras de negócio
│   └── config          # Configurações gerais do projeto
```

---

## Testes

Execute os testes com o seguinte comando:
```bash
mvn test
```
Os testes cobrem:
- Regras de negócio (Service Layer)
- Acesso ao banco de dados (Repository Layer)

---

## Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests com melhorias e correções!

---

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.