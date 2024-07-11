# Path parameters e exceptions

## Sumário
- [Introdução](#introdução)
- [Query parameters](#query-parameters)
- [Path parameters](#path-parameters)
- [Calculadora com Path Parameters](#calculadora-com-path-parameters)
  - [Lógica da calculadora](#lógica-da-calculadora)
  - [Tratamento de exceções](#tratamento-de-exceções)
- [Exercícios](#exercícios)
- [Referências](#referências)

## Introdução
Entendendo as anotações `@RestController`, `@RequestMapping` e `@PathVariable`.

Diferença entre `Query Parameters` e `Path Parameters`.

## Query parameters
Utilizado para enviar parâmetros adicionais, também é conhecido como `search parameters`. 

Os query params são informados na URL da requisição logo após o caminho completo. O caractere `?` é utilizado para indicar o início dos query params e a codificação segue uma estrutura de `chave=valor` para cada parâmetro. Além disso, os parâmetros são separados por `&`.

Exemplo:
```
https://www.meuendereco.com.br?id=9&name=Denecley
```
De acordo com a URL acima, existem dois parâmetros: `id` e `name`. O parâmetro `id` possui o valor `9` e `name` possui o valor igual a `Denecley`.

## Path parameters
Os path parameters (parâmetros de caminho) são uma parte da URL que é utilizada para identificar recursos específicos no servidor. Eles fazem parte do caminho da URL e são geralmente usados para passar informações dinâmicas. Por exemplo, em uma URL que representa um recurso específico, os path parameters são usados para especificar qual recurso deve ser acessado.

Vamos ver um exemplo para entender melhor:

```
https://example.com/users/123/orders/456
```

Nesta URL, temos dois path parameters:
1. `123` identifica um usuário específico.
2. `456` identifica um pedido específico desse usuário.

A estrutura geral da URL pode ser descrita assim:

```
https://example.com/users/{userId}/orders/{orderId}
```

- `{userId}` e `{orderId}` são placeholders para os path parameters.
- Quando a URL é acessada, esses placeholders são substituídos por valores reais, como `123` e `456` no exemplo acima.

Os path parameters são muito úteis em APIs RESTful, onde permitem criar URLs significativas e descritivas para acessar recursos específicos de forma intuitiva. Eles são diferentes dos query parameters, que aparecem após um ponto de interrogação (`?`) na URL e são geralmente usados para filtrar ou modificar a resposta de uma maneira mais geral.

## Calculadora com Path Parameters

No projeto de exemplo, foi implementado um serviço REST para calcular a soma de dois valores informados por variáveis de caminho.

A requisição proposta foi mapeada como:
```
GET /sum/{firstValue}/{secondValue}
```

### Lógica da calculadora

A class controladora é a [MathController](./src/main/java/br/com/gomide/MathController.java), que possui o método `sum` responsável por:
- Receber a requisição e extrair as variáveis de caminho
- Validar os dados de entrada
- Efetuar a operação de soma
- Enviar a resposta

#### Anotação @RequestMapping

A anotação `@RequestMapping` uitlizada no método [sum](./src/main/java/br/com/gomide/MathController.java#L13) é usada para mapear solicitações HTTP para métodos específicos em controladores Spring. No exemplo acima, a URL `/sum/{firstValue}/{secondValue}` está sendo mapeada para o método somar, e o método aceita solicitações HTTP do tipo GET.

-	`value = "/sum/{firstValue}/{secondValue}"`: Define a URL que deve ser acessada para invocar o método. Os valores `firstValue` e `secondValue` são parâmetros de caminho que serão extraídos da URL.
-	`method = RequestMethod.GET`: Especifica que este método deve ser chamado quando uma solicitação HTTP GET for feita para a URL especificada.

#### Anotação @PathVariable

As anotações [`@PathVariable`](./src/main/java/br/com/gomide/MathController.java#L15) são usadas para extrair valores dos parâmetros de caminho da URL e vinculá-los aos parâmetros do método.

-	`@PathVariable(value = "firstValue") String strFirstValue`: Extrai o valor de `firstValue` da URL e o atribui à variável `strFirstValue`.
-	`@PathVariable(value = "secondValue") String strSecondValue`: Extrai o valor de `secondValue` da URL e o atribui à variável `strSecondValue`.

### Tratamento de exceções

Foi utilizado o mecanismo do Spring para tratamento de exceções a nível de requisição a partir da classe [`ResponseEntityExceptionHandler`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html).

A partir da implementação desta classe conseguimos interceptar exceções e tratar o retorno criando um objeto de resposta.

#### Classe `UnsupportedMathOperationException`

A classe [UnsupportedMathOperationException](./src/main/java/br/com/gomide/exceptions/UnsupportedMathOperationException.java) foi criada para representar as exceções referentes a operações matemáticas numéricas.

#### Classe `ExceptionResponse`

A classe [`ExceptionResponse`](./src/main/java/br/com/gomide/exceptions/ExceptionResponse.java) é utilizada para definir a estrutura de uma resposta de exceção em uma aplicação.

A classe possui três atributos:

-	`timestamp`: Armazena a data e hora em que a exceção ocorreu.
-	`message`: Armazena uma mensagem descritiva sobre a exceção.
-	`details`: Armazena detalhes adicionais sobre a exceção.

#### Classe `CustomizedResponseEntityExceptionHandler`

## Exercícios

### 1. Implementar as demais operações matemáticas

Implemente as demais operações aritméticas como `subtração`, `divisão` e `multiplicação`.

### 2. Refatoração

O nosso controller operações de vários contextos como realizasr cálculos, efetuar validações e orquestrar as operações de requisição e resposta.

Modifique o projeto para separar as operações por contextos em classes específicas facilitando manutenções futuras e o reuso de código.

## Referências
- [Por que operações com números de ponto flutuantes podem retornar resultados estranhos?](https://floating-point-gui.de/basic/)
- [ResponseEntityExceptionHandler](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html)