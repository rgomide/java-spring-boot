# O padrão de projetos VO (value Object)

## Sumário

## Introdução

## O padrão de projetos VO (Value Object)

No controlador [PersonController](./src/main/java/br/com/gomide/controller/PersonController.java), o `Value Object (VO)`, representado pela classe [PersonVO](./src/main/java/br/com/gomide/controller/PersonController.java), tem o objetivo de **transferir dados** entre diferentes camadas da aplicação sem expor diretamente a estrutura interna da entidade [Person](./src/main/java/br/com/gomide/model/Person.java). O uso de um VO (também chamado de **DTO - Data Transfer Object**) é uma prática comum em aplicações que seguem o padrão **MVC** (Model-View-Controller), principalmente para desacoplar as entidades de domínio da lógica de apresentação e do transporte de dados.

### Objetivos do VO no em um projeto:

1. Encapsulamento e Abstração de Dados:

   O VO encapsula os dados que são transferidos entre o cliente e o servidor, abstraindo detalhes internos da entidade de domínio `Person`. Isso significa que a estrutura do VO pode diferir da entidade (como ocultar certos atributos ou incluir informações derivadas) para atender melhor às necessidades da interface pública da aplicação (API).

2. Segurança:
   
   Ao usar um VO, você evita expor diretamente a entidade de domínio, protegendo dados sensíveis ou internos da estrutura da entidade. Isso é especialmente útil quando você não quer que certos campos sejam acessíveis ou manipuláveis diretamente pelo usuário externo.

3. Facilitação de Transformações:
   
   Com o uso de bibliotecas como o [DozerMapper](https://github.com/DozerMapper/dozer), que é um mapeador de objetos, o VO permite a fácil conversão entre a entidade [Person](./src/main/java/br/com/gomide/model/Person.java) e o [PersonVO](./src/main/java/br/com/gomide/data/vo/v1/PersonVO.java). Essa transformação possibilita que os dados da entidade de domínio sejam convertidos para um formato específico (VO) que será retornado ao cliente ou recebido via uma requisição.

4. Desacoplamento:

   O uso de um VO desacopla a camada de apresentação (a API) da camada de persistência (a entidade). Isso permite que a estrutura da entidade `Person` seja alterada sem necessariamente modificar o contrato de comunicação pública da API (o VO). Em outras palavras, você pode mudar a estrutura do banco de dados ou as entidades sem quebrar o contrato da API.

5. Validações e Lógica Simples:

   O VO pode ser enriquecido com validações e lógica simples antes mesmo de ser convertido para a entidade. Por exemplo, campos podem ser validados e ajustados no VO, antes de serem transformados na entidade de domínio `Person`.

6. Suporte a diferentes representações:
   
   Se houver necessidade de apresentar diferentes representações dos dados de `Person` em diferentes contextos (como um `PersonVO` para visualizações administrativas e outro `PersonVO` para usuários comuns), o uso de VOs permite essa flexibilidade sem alterar a entidade de domínio.

## Conversão entre Entidade e Value Object

Neste projeto foi utilizada a biblioteca DozerMapper para realizar a conversão automática entre `Entity` e `Value Object`.

Primeiramente, é necessário adicionar a seguinte dependência no arquivo [pom.xml](./pom.xml):

```xml
<dependency>
  <groupId>com.github.dozermapper</groupId>
  <artifactId>dozer-core</artifactId>
  <version>7.0.0</version>
</dependency>
```

Depois disso, foi criada uma classe [DozerMapper](./src/main/java/br/com/gomide/mapper/DozerMapper.java) responsável por converter um objeto de uma classe de origem para um outro objeto de uma classe de destino. 

Essa classe realiza a conversão de forma mais abrangente possível.

### Alternativas ao DozerMapper

De acordo com o repositório oficial, a biblioteca [DozerMapper](https://github.com/DozerMapper/dozer?tab=readme-ov-file#project-activity) não receberá mais atualizações e é recomendado uso de alguma alternativa, como por exemplo:

- [MapStruct](https://github.com/mapstruct/mapstruct)
- [ModelMapper](https://github.com/modelmapper/modelmapper)

## Versionamento

O versionamento de API é uma prática essencial para garantir que a evolução da API não quebre os consumidores existentes, permitindo atualizações e melhorias sem interromper a funcionalidade para os usuários. Aqui estão algumas estratégias de versionamento de API amplamente utilizadas:

### 1. Versionamento na URL
Uma das estratégias mais comuns é incluir a versão da API diretamente na URL. Isso é simples de implementar e muito claro para os consumidores da API.
   
#### Exemplo:
- `/api/v1/recursos`
- `/api/v2/recursos`
   
##### Prós:
- Fácil de entender e implementar.
- Facilita a gestão de várias versões ativamente em produção.
   
##### Contras:
- A URL fica mais longa e, ao longo do tempo, pode se tornar difícil de gerenciar se houver muitas versões.
   
---

### 2. **Versionamento por Query Parameter**
   - As versões da API são especificadas como parâmetros de consulta na URL.
   
   **Exemplo**:
   - `/api/recursos?version=1`
   - `/api/recursos?version=2`
   
   **Prós**:
   - Mantém as URLs mais limpas e permite que a versão seja trocada sem mudar drasticamente a estrutura da URL.
   
   **Contras**:
   - Pode ser menos intuitivo e mais difícil de documentar em comparação com o versionamento na URL.
   - Pode ser ignorado por caches de API.
   
---

### 3. **Versionamento por Cabeçalho (Header Versioning)**
   - A versão é especificada no cabeçalho HTTP da requisição. Esse método é mais transparente e deixa a URL da API intocada.
   
   **Exemplo**:
   - Cabeçalho `Accept`: `application/vnd.myapi.v1+json`
   - Cabeçalho `Accept`: `application/vnd.myapi.v2+json`
   
   **Prós**:
   - Mantém a URL limpa e a semântica mais RESTful.
   - Fácil de adaptar para mudanças no formato de retorno (ex. XML, JSON, etc.).
   
   **Contras**:
   - Mais complexo de implementar e pode ser menos intuitivo para desenvolvedores, exigindo uma boa documentação.
   - Nem todos os clientes ou proxies HTTP lidam bem com headers customizados.
   
---

### 4. **Versionamento por Subdomínio ou Domínio**
   - A versão da API é especificada pelo subdomínio ou pelo domínio.
   
   **Exemplo**:
   - `v1.api.example.com`
   - `v2.api.example.com`
   
   **Prós**:
   - Pode ser útil para grandes sistemas distribuídos, permitindo isolar versões inteiras em diferentes servidores ou infraestrutura.
   - URLs limpas e facilmente separáveis por versão.
   
   **Contras**:
   - Difícil de gerenciar conforme o número de versões cresce.
   - Exige configurações DNS e manutenção extra para o ambiente de infraestrutura.
   
---

### 5. **Versionamento por Media Type (Content Negotiation)**
   - Baseia-se no uso do cabeçalho `Accept` para solicitar uma versão específica da API, com diferentes media types.
   
   **Exemplo**:
   - `Accept: application/vnd.example.v1+json`
   - `Accept: application/vnd.example.v2+xml`
   
   **Prós**:
   - Pode lidar com versões e formatos de mídia ao mesmo tempo.
   - Altamente flexível e respeita o conceito de content negotiation do HTTP.
   
   **Contras**:
   - Mais complexo de implementar e entender.
   - Exige uma estrutura de documentação robusta para explicar as diferentes media types.
   
---

### 6. **Versionamento por Path Versioning**
   - Similar ao versionamento na URL, mas ao invés de incluir a versão na raiz, a versão é incluída em um nível mais profundo da URL.
   
   **Exemplo**:
   - `/api/recursos/v1`
   - `/api/recursos/v2`
   
   **Prós**:
   - Similar à estratégia de versão na URL, mas a versão não é colocada na raiz, o que pode ser útil em certos casos de roteamento.
   
   **Contras**:
   - Ainda pode gerar URLs longas e difíceis de gerenciar conforme o número de versões aumenta.
   
---

### 7. **Versionamento Implícito ou Semântico (Semantic Versioning)**
   - O versionamento é implícito e segue a convenção de versionamento semântico (ex.: 1.0.0, 2.0.0). As versões são determinadas com base nas mudanças de funcionalidades e compatibilidade.
   
   **Exemplo**:
   - `/api/recursos` para versões compatíveis.
   - Incremento de versão apenas quando houver mudanças que quebrem a compatibilidade.
   
   **Prós**:
   - Pode ser benéfico em casos onde pequenas mudanças não justificam uma nova versão explícita.
   - Segue a prática comum do versionamento semântico, que é familiar para muitos desenvolvedores.
   
   **Contras**:
   - Difícil de gerenciar quando há mudanças que afetam diferentes tipos de usuários.
   - Pode ser confuso se não for bem documentado e controlado.
   
---

### 8. **Versionamento por Campo no Corpo da Requisição (Payload Versioning)**
   - A versão é passada diretamente dentro do corpo da requisição.
   
   **Exemplo**:
   ```json
   {
     "version": "1.0",
     "data": {
       ...
     }
   }
   ```
   
   **Prós**:
   - Pode ser útil para APIs que exigem muita flexibilidade nos dados.
   
   **Contras**:
   - Aumenta o risco de confusão entre diferentes versões e dificulta o uso de caches intermediários (ex.: proxies).
   - Não é uma abordagem RESTful, pois o controle da versão está fora do cabeçalho HTTP.
   
---

### 9. **Versionamento por Parâmetro de Fragmento (URI Fragment)**
   - A versão é especificada como parte do fragmento da URI, depois do `#`.
   
   **Exemplo**:
   - `/api/recursos#v1`
   - `/api/recursos#v2`
   
   **Prós**:
   - Pode ser útil para lidar com estados de recursos no front-end, como em SPAs.
   
   **Contras**:
   - Não é uma abordagem RESTful.
   - Os fragmentos não são enviados ao servidor pela requisição HTTP, então essa abordagem é limitada.
   
---

### Conclusão

A escolha da estratégia de versionamento de API depende muito do contexto do projeto, dos consumidores da API e dos requisitos de evolução da aplicação. As estratégias mais populares e amplamente utilizadas são o **versionamento na URL** e o **versionamento por cabeçalho**, mas cada estratégia possui prós e contras específicos.

A decisão deve levar em conta:
- Facilidade de implementação e uso.
- Complexidade de manutenção.
- Compatibilidade com padrões de RESTful.
- Necessidades de backwards compatibility e suporte a múltiplas versões.

### Migration com Flyway


## Exercícios

### Adicionar novo campo

Esse exercício consiste em adicionar um novo campo na tabela `people` e modificar a versão `v2` da API desenvolvida neste projeto. Desenvolva e teste cada etapa a seguir:

#### 1. Adicionar campo birth_day

Adicione um novo arquivo de migration para acrescentar a coluna `birth_day` do tipo `date` na tabela `people`.

**:warning:Obs.:**  interrompa a execução do projeto antes de criar o arquivo de migração de dados.

#### 2. Modificar Entity Person

Adicione o atributo `birthDay` na classe `Person` e faça o respectivo mapeamento para a coluna do banco de dados.

#### 3. Modificar PersonMapper para converter os campos e calcular a idade

Modifique a classe `PersonMapper` para utilizar o atributo `birthDay` ao invés de valores estáticos.

Acrescente o atributo `Integer age` na classe `PersonVOV2` e calcule a idade na hora de converter uma entidade para um VO.

#### 4. Adicionar GET /api/people/v2 para retornar nova versão de objeto

Adicione uma rota `GET /api/peaople/v2` para buscar todos os objetos do recurso `people` e mostrar as informações no seguinte formato e ordem:

```json
{
  "age": ...,
  "address": ...,
  "gender": ...,
  "first_name": ...,
  "last_name": ...,
  "birth_day": ...,
  "id": ...
}
```

## Referências
- [Github - DozerMapper](https://github.com/DozerMapper/dozer)
- [A Guide to Mapping With Dozer](https://www.baeldung.com/dozer)
- [Evolutionary Database Design](https://martinfowler.com/articles/evodb.html)
- [Flyway: Naming Patterns Matter](https://www.red-gate.com/blog/database-devops/flyway-naming-patterns-matter)