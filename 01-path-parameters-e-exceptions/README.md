# Path parameters e exceptions

## Sumário
- [Introdução](#introdução)

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


### Tratamento de exceções

## Exercícios

### 1. Implementar as demais operações matemáticas

Implemente as demais operações aritméticas como `subtração`, `divisão` e `multiplicação`.

### 2. Refatoração

O nosso controller operações de vários contextos como realizasr cálculos, efetuar validações e orquestrar as operações de requisição e resposta.

Modifique o projeto para separar as operações por contextos em classes específicas facilitando manutenções futuras e o reuso de código.

## Referências
- [Por que operações com números de ponto flutuantes podem retornar resultados estranhos?](https://floating-point-gui.de/basic/)