# Como usar os verbos HTTP no Spring

## Sumário

- [Introdução](#introdução)
- [Classe PersonController](#classe-personcontroller)
  - [Anotações Importantes](#anotações-importantes)
  - [Métodos e Anotações para Operações HTTP](#métodos-e-anotações-para-operações-http)
- [Classe PersonService](#classe-personservice)
  - [Anotação @Service](#anotação-service)
  - [Contexto da Classe PersonService no Projeto](#contexto-da-classe-personservice-no-projeto)
  - [Resumo](#resumo)
- [Referências](#referências)

## Introdução

Neste módulo veremos como utilizar os verbos HTTP `GET`, `POST`, `PUT` e `DELETE` no Spring.

## Classe PersonController

A classe [PersonController](./src/main/java/br/com/gomide/controller/PersonController.java) lida com operações básicas de CRUD (Create, Read, Update, Delete) para um recurso chamado [Person](./src/main/java/br/com/gomide/model/Person.java). Vamos analisar as anotações e métodos com ênfase nos métodos HTTP `GET`, `POST`, `PUT`, e `DELETE`.

### Anotações Importantes

1. `@RestController`: Indica que esta classe é um controlador onde cada método é automaticamente serializado em JSON e retornado como uma resposta HTTP.

2. `@RequestMapping("/person")`: Define o caminho base para todos os endpoints nesta classe. Ou seja, todos os métodos terão o caminho base `/person`.

### Métodos e Anotações para Operações HTTP

#### @GetMapping
- Método `findAll()`:
  - **Propósito**: Retorna uma lista de todas as pessoas.
  - **Anotação**: `@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)`
  - **Explicação**: Este método responde a uma requisição HTTP GET no caminho `/person` e retorna um JSON contendo todas as instâncias de [Person](./src/main/java/br/com/gomide/model/Person.java).

- Método `findById()`:
  - **Propósito**: Retorna uma pessoa específica com base no ID fornecido.
  - **Anotação**: `@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)`
  - **Explicação**: Este método responde a uma requisição HTTP GET no caminho `/person/{id}`, onde `{id}` é um parâmetro de caminho. O ID é capturado com `@PathParam`, e o serviço retorna a pessoa correspondente em formato JSON.

#### @PostMapping
- Método `create()`:
  - **Propósito**: Cria uma nova pessoa.
  - **Anotação**: `@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)`
  - **Explicação**: Este método responde a uma requisição HTTP POST no caminho `/person`. O corpo da requisição deve conter um JSON representando a nova pessoa a ser criada, e o método retorna a pessoa criada em formato JSON. A anotação `@RequestBody` vincula o corpo da requisição ao objeto `Person`.

#### @PutMapping
- Método `update()`:
  - **Propósito**: Atualiza uma pessoa existente.
  - **Anotação**: `@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)`
  - **Explicação**: Este método responde a uma requisição HTTP PUT no caminho `/person`. O corpo da requisição deve conter um JSON com os dados da pessoa a ser atualizada, e o método retorna a pessoa atualizada em formato JSON.

#### @DeleteMapping:
- Método `delete()`:
  - **Propósito**: Deleta uma pessoa existente com base no ID fornecido.
  - **Anotação**: `@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)`
  - **Explicação**: Este método responde a uma requisição HTTP DELETE no caminho `/person/{id}`. O ID é capturado com `@PathVariable`, e o serviço deleta a pessoa correspondente. O método não retorna nenhum conteúdo, apenas uma resposta de sucesso ou falha.

Essas anotações `@GetMapping`, `@PostMapping`, `@PutMapping`, e `@DeleteMapping` são usadas para mapear as requisições HTTP (GET, POST, PUT, DELETE) aos métodos do controlador. Cada uma define como a API deve responder a diferentes operações que são comuns em sistemas CRUD. A utilização das anotações garante que os métodos sejam chamados corretamente com base na natureza da requisição HTTP recebida.

## Classe PersonService

A classe [PersonService](./src/main/java/br/com/gomide/services/PersonService.java) é responsável por implementar a lógica de negócios associada ao gerenciamento de objetos [Person](./src/main/java/br/com/gomide/model/Person.java) em um sistema.

### Anotação @Service

A anotação `@Service` é usada em classes que definem a lógica de negócios. Ela indica ao Spring que essa classe é um serviço, ou seja, um componente que contém a lógica central da aplicação e pode ser injetado em outras partes do sistema, como controladores.

No projeto, `PersonService` é marcado como um serviço, o que significa que ele será gerenciado pelo contêiner de injeção de dependências do Spring. Isso facilita a injeção dessa classe em outros componentes, como o controlador `PersonController`, por meio da anotação [@Autowired](./src/main/java/br/com/gomide/controller/PersonController.java#L), permitindo o uso dos métodos definidos em `PersonService` para manipulação de dados.

### Contexto da Classe `PersonService` no Projeto

A classe `PersonService` serve como a camada de serviço no padrão de projeto de três camadas (Controller, Service, Repository). Ela contém a lógica necessária para manipular instâncias de `Person`, sendo responsável por operações como encontrar, criar, atualizar e deletar pessoas. As instâncias de `PersonService` são utilizadas pelos controladores para realizar as operações requisitadas pelos usuários.

#### Gerenciamento de Dados

Em vez de interagir diretamente com uma base de dados, esta implementação está utilizando dados mockados (fictícios) para simular o comportamento de uma aplicação real. Isso é útil para desenvolvimento, testes ou protótipos.

#### Método `mockPerson(int i)`

O método `mockPerson(int i)` é usado para gerar objetos `Person` fictícios. Ele cria e retorna uma nova instância de `Person` com dados simulados, como nome, sobrenome, gênero e endereço, usando o índice `i` para garantir que cada instância tenha valores ligeiramente diferentes.
  
O método usa um contador (`counter`), que é um `AtomicLong`, para gerar um ID único para cada `Person`.

Ele define os atributos da pessoa (`firstName`, `lastName`, `gender`, `address`) usando valores predefinidos concatenados com o índice `i`.

Este método é utilizado, por exemplo, no método `findAll()`, onde ele cria uma lista de oito pessoas mockadas. Isso simula a recuperação de dados de uma base de dados, permitindo que o desenvolvedor veja como a aplicação se comportaria ao lidar com múltiplos objetos `Person`.

#### Outros Métodos

- `findAll()`:
  - Retorna uma lista de pessoas simuladas, usando o método `mockPerson()` para criar cada objeto.
  - É usado para simular a listagem de todos os registros de pessoas.

- `findById(String id)`:
  - Retorna uma única pessoa simulada com base em um ID fictício gerado internamente.
  
- `create(Person person)` e `update(Person person)`:
  - Simulam a criação e atualização de uma pessoa, respectivamente. Eles não alteram dados reais, mas apenas registram a ação e retornam o objeto `Person` fornecido.

- `delete(String id)`:
  - Simula a exclusão de uma pessoa com base em um ID. Esse método não realiza a exclusão real, mas registra que o método foi chamado.

### Resumo

A classe `PersonService` com a anotação `@Service` funciona como a camada de serviço no projeto, encapsulando a lógica de negócios para manipular objetos `Person`. O método `mockPerson(int i)` é usado para gerar dados fictícios para simular operações de CRUD. A classe serve como um intermediário entre o controlador (`PersonController`) e a lógica de manipulação de dados, permitindo que a aplicação responda às requisições do usuário de forma organizada e gerenciável.

## Referências

- [Spring - Mapping Requests](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-requestmapping.html)
- [Anotação @Service](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html)