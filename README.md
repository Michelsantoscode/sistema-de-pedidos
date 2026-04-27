# 📦 Pedidos API

API REST desenvolvida em Java com Spring Boot para gerenciamento de pedidos, usuários e produtos, estruturada seguindo boas práticas de desenvolvimento backend com foco em organização, escalabilidade e manutenção de código.

---

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Validation**
- **Hibernate / JPA**
- **ModelMapper**
- **Lombok**
- **SpringDoc OpenAPI (Swagger UI)**
- **MySQL** *(ou banco configurado em `application.properties`)*

---

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
Controller → Assembler → Service → Repository → Database
```

| Camada | Responsabilidade |
|---|---|
| `controller` | Recebe requisições HTTP, valida entrada e retorna respostas |
| `assembler` | Converte entre entidades de domínio e DTOs (Input/Model) |
| `service` | Contém as regras de negócio |
| `repository` | Acesso ao banco de dados via Spring Data JPA |
| `model` | Entidades JPA e enums de domínio |
| `exceptionhandler` | Tratamento centralizado de erros com `@ControllerAdvice` |

---

## 📋 Funcionalidades

### 👤 Usuários
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/usuarios` | Cadastrar usuário |
| `GET` | `/usuarios` | Listar todos os usuários |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID |
| `PUT` | `/usuarios/{id}` | Atualizar usuário |
| `DELETE` | `/usuarios/{id}` | Excluir usuário |

### 🛒 Produtos
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/produto` | Cadastrar produto |
| `GET` | `/produto` | Listar todos os produtos |
| `GET` | `/produto/{id}` | Buscar produto por ID |
| `DELETE` | `/produto/{id}` | Excluir produto |

### 📦 Pedidos
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/pedidos` | Criar pedido |
| `GET` | `/pedidos` | Listar todos os pedidos |
| `GET` | `/pedidos/{id}` | Buscar pedido por ID |
| `DELETE` | `/pedidos/{id}` | Excluir pedido |
| `POST` | `/pedidos/{id}/pagar` | Pagar pedido |
| `POST` | `/pedidos/{id}/enviar` | Enviar pedido |
| `POST` | `/pedidos/{id}/entregar` | Entregar pedido |

---

## 🔄 Ciclo de Vida do Pedido

O pedido segue uma máquina de estados com transições obrigatórias:

```
CRIADO ──► PAGO ──► ENVIADO ──► ENTREGUE
```

Qualquer tentativa de transição fora dessa sequência retorna um erro `400 Bad Request`.

---

## ⚙️ Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL (ou banco de sua preferência)

## 📖 Documentação da API

Com a aplicação rodando, acesse a documentação interativa via Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

## 🛡️ Tratamento de Erros

A API utiliza `@ControllerAdvice` para tratamento centralizado de exceções, retornando respostas no formato `ProblemDetail` (RFC 9457):

| Exceção | Status | Descrição |
|---|---|---|
| `NegocioException` | `400 Bad Request` | Violação de regra de negócio |
| `MethodArgumentNotValidException` | `400 Bad Request` | Falha na validação dos campos |
| `ResponseStatusException` | Variável | Erros HTTP genéricos |
| `DataIntegrityViolationException` | `409 Conflict` | Violação de integridade no banco (ex: e-mail duplicado) |

### Exemplo de resposta de erro

```json
{
  "status": 400,
  "detail": "nome: não deve estar em branco; preco: não deve ser nulo"
}
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/michel/pedidos/
│
├── api/
│   ├── controller/          # PedidoController, ProdutoController, UsuarioController
│   ├── exceptionhandler/    # ApiExceptionHandler
│   └── model/
│       ├── input/           # DTOs de entrada (PedidoInput, ProdutoInput, UsuarioInput)
│       └── *.Model          # DTOs de saída (PedidoModel, ProdutoModel, UsuarioModel)
│
├── assembler/               # Conversão entre entidades e DTOs
│
├── commom/                  # Configurações (ModelMapperConfig)
│
└── domain/
    ├── exception/           # Exceções de domínio
    ├── model/               # Entidades JPA + enums (StatusPedido)
    ├── repository/          # Interfaces Spring Data JPA
    └── service/             # Regras de negócio
```

---

## 👨‍💻 Autor

Desenvolvido por **Michel**
