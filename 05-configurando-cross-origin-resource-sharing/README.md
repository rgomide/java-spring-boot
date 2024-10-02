# Configurando Cross-Origin Resource Sharing (CORS)

## Sumário

- [Introdução](#introdução)

## Introdução

Compartilhamento de recursos de origem cruzada (CORS)

o recurso de um site só pode acesar outro recurso do mesmo site se eles estiverem no mesmo domínio ([same-origin policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)). Mesmo endereço, subdomínio e porta.

## Habilitando o CORS no Spring Framework

### A anotação @CrossOrigin

### Habilitando o CORS de forma global

Adicionar a configuração `cors.originPatterns` no arquivo [application.properties](./src/main/resources/application.properties#L24). 

Implementar uma classe de configuração utilizando a anotação [@Configuration](https://docs.spring.io/spring-framework/reference/core/beans/java/configuration-annotation.html).


## Referências
- [Using the @Configuration annotation](https://docs.spring.io/spring-framework/reference/core/beans/java/configuration-annotation.html)
- [Cross-Origin Resource Sharing (CORS)](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/CORS)