# O padrão Value Object, versionamento de APIs e migrations

## Sumário

- [Introdução](#introdução)
- [O padrão de projetos VO (Value Object)](#o-padrão-de-projetos-vo-value-object)
  - [Objetivos do VO no em um projeto](#objetivos-do-vo-no-em-um-projeto)
- [Conversão entre Entidade e Value Object](#conversão-entre-entidade-e-value-object)
  - [Alternativas ao DozerMapper](#alternativas-ao-dozermapper)
- [Versionamento](#versionamento)
- [Estratégia de versionamento adotada neste projeto](#estratégia-de-versionamento-adotada-neste-projeto)
- [PersonVOV2](#personvov2)
- [Migration com Flyway](#migration-com-flyway)
- [Exercícios](#exercícios)

## Introdução

Neste projeto analizaremos o padrão Value Object, versionamento de APIs e esquemas de migração de banco de dados.

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
   
### 2. Versionamento por Query Parameter
As versões da API são especificadas como parâmetros de consulta na URL.
   
#### Exemplo:
- `/api/recursos?version=1`
- `/api/recursos?version=2`
   
##### Prós:
- Mantém as URLs mais limpas e permite que a versão seja trocada sem mudar drasticamente a estrutura da URL.
   
##### Contras:
- Pode ser menos intuitivo e mais difícil de documentar em comparação com o versionamento na URL.
- Pode ser ignorado por caches de API.
   
### 3. Versionamento por Subdomínio ou Domínio
A versão da API é especificada pelo subdomínio ou pelo domínio.
   
#### Exemplo:
- `v1.api.example.com`
- `v2.api.example.com`
   
##### Prós:
- Pode ser útil para grandes sistemas distribuídos, permitindo isolar versões inteiras em diferentes servidores ou infraestrutura.
- URLs limpas e facilmente separáveis por versão.
   
##### Contras:
- Difícil de gerenciar conforme o número de versões cresce.
- Exige configurações DNS e manutenção extra para o ambiente de infraestrutura.
   
### 4. Versionamento por Path Versioning
Similar ao versionamento na URL, mas ao invés de incluir a versão na raiz, a versão é incluída em um nível mais profundo da URL.
   
#### Exemplo:
- `/api/recursos/v1`
- `/api/recursos/v2`
   
##### Prós:
- Similar à estratégia de versão na URL, mas a versão não é colocada na raiz, o que pode ser útil em certos casos de roteamento.
   
##### Contras:
- Ainda pode gerar URLs longas e difíceis de gerenciar conforme o número de versões aumenta.

### 5. Versionamento por Campo no Corpo da Requisição (Payload Versioning)
A versão é passada diretamente dentro do corpo da requisição.
   
#### Exemplo:
```json
{
  "version": "1.0",
  "data": {
    ...
  }
}
```
   
##### Prós:
- Pode ser útil para APIs que exigem muita flexibilidade nos dados.
   
##### Contras:
- Aumenta o risco de confusão entre diferentes versões e dificulta o uso de caches intermediários (ex.: proxies).
- Não é uma abordagem RESTful, pois o controle da versão está fora do cabeçalho HTTP.

## Estratégia de versionamento adotada neste projeto

Neste projeto foi utilizada a estratégia de versionamento por `Path Versioning`, no qual o recurso `people` possui duas versões:

- `/api/people/v1`
- `/api/people/v2`

Para fins de organização, foram criados dois controladores:

- [PersonController](./src/main/java/br/com/gomide/controller/PersonController.java)
- [PersonV2Controller](./src/main/java/br/com/gomide/controller/PersonV2Controller.java)

## PersonVOV2

O controlador [PersonV2Controller](./src/main/java/br/com/gomide/controller/PersonV2Controller.java) utiliza o Value Object [PersonVOV2](./src/main/java/br/com/gomide/data/vo/v2/PersonVOV2.java) como formato de dados para a troca de mensagens.

Neste VO, foi acrescentado o atributo `Date birthDay`. Como esse VO difere-se do modelo de dados, foi implementado uma classe de mapeamento customizada chamada [PersonMapper](./src/main/java/br/com/gomide/mapper/custom/PersonMapper.java).

Nessa classe, o campo `birthDay` é gerado a partir da data atual. Modificaremos essa lógica nos exercícios propostos.

Além disso, foram utilizadas as anotações `@JsonIgnore`, `@JsonProperty`, e `@JsonPropertyOrder` para controlalr a forma de serialização e desserialização dos arquivos JSON. Essas anotações são parte da biblioteca [Jackson](https://www.baeldung.com/jackson). No contexto da classe `PersonVOV2`, essas anotações configuram como os atributos dessa classe serão manipulados quando transformados em JSON ou lidos a partir de JSON.

### 1. `@JsonIgnore`
A anotação `@JsonIgnore` é usada para excluir um campo da serialização e desserialização JSON. Isso significa que, ao serializar um objeto da classe `PersonVOV2` para JSON, o campo anotado com `@JsonIgnore` será ignorado.

#### No contexto:
```java
@JsonIgnore
private String gender;
```
- O campo `gender` não será incluído na saída JSON quando um objeto `PersonVOV2` for serializado.
- Da mesma forma, se um JSON recebido incluir o campo `gender`, ele será ignorado durante a desserialização, ou seja, o valor de `gender` não será lido nem atribuído ao objeto.

#### Exemplo de JSON gerado:
```json
{
  "id": 1,
  "first_name": "John",
  "last_name": "Doe",
  "address": "123 Street",
  "birth_day": "2000-01-01"
}
```
Observe que `gender` não está presente.

### 2. `@JsonProperty`
A anotação `@JsonProperty` é usada para personalizar o nome de um campo durante a serialização e desserialização. Se um campo tiver um nome diferente no JSON (por exemplo, com underscores), `@JsonProperty` permite mapear esse nome JSON para o atributo Java correspondente.

#### No contexto:
```java
@JsonProperty("first_name")
private String firstName;

@JsonProperty("last_name")
private String lastName;

@JsonProperty("birth_day")
private Date birthDay;
```
- A propriedade `firstName` será serializada como `first_name` no JSON.
- A propriedade `lastName` será serializada como `last_name` no JSON.
- A propriedade `birthDay` será serializada como `birth_day` no JSON.

#### Exemplo de JSON gerado:
```json
{
  "id": 1,
  "first_name": "John",
  "last_name": "Doe",
  "address": "123 Street",
  "birth_day": "2000-01-01"
}
```
Durante a desserialização, Jackson também espera que o JSON contenha os nomes com underscores (`first_name`, `last_name`, etc.), e os valores serão atribuídos corretamente aos campos Java `firstName`, `lastName`, e `birthDay`.

### 3. `@JsonPropertyOrder`
A anotação `@JsonPropertyOrder` define a ordem dos campos quando a classe é serializada para JSON. Isso pode ser útil para garantir uma apresentação consistente, especialmente se a ordem dos campos for importante para o cliente que consome a API.

#### No contexto:
```java
@JsonPropertyOrder({ "id", "address", "firstName", "lastName", "address" })
```
- Essa anotação garante que os campos sejam serializados na ordem especificada: primeiro o `id`, seguido de `address`, `firstName`, e assim por diante.
- Se a ordem dos atributos fosse importante, por exemplo, ao enviar dados a um sistema legível por humanos, essa anotação garantiria que os campos aparecessem nessa sequência específica no JSON gerado.

#### Exemplo de JSON gerado com ordem definida:
```json
{
  "id": 1,
  "address": "123 Street",
  "first_name": "John",
  "last_name": "Doe",
  "birth_day": "2000-01-01"
}
```
Se o `@JsonPropertyOrder` não fosse especificado, Jackson poderia escolher qualquer ordem para os campos, o que pode ser problemático em algumas situações.

## Migration com Flyway

A partir desse projeto, utilizaremos a biblioteca [Flayway](https://documentation.red-gate.com/fd/flyway-documentation-138346877.html) como mecanismo de gerenciamento de versão do banco de dados. Essa biblioteca utiliza um esquema de nomenclatura específico para os arquivos de migração, o que permite organizar e aplicar scripts de banco de dados de maneira sequencial.

A convenção de nomes dos arquivos de migração Flyway segue um padrão que permite que o Flyway identifique o tipo de migração, a versão, e uma breve descrição do que a migração faz. Esse esquema ajuda a garantir que as migrações sejam aplicadas na ordem correta.

Por padrão os arquivos de migração ficam no diretório [src/main/resources/db/migration/](./src/main/resources/db/migration/).

### Adicionar suporte ao Flyway no projeto

Para adicionar o suporte a biblioteca Flyway no projeto, basta adicionar as seguintes dependências no arquivo [pom.xml](./pom.xml):

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>

<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-database-postgresql</artifactId>
</dependency>
```

### Esquema de Nomenclatura

A estrutura de nomeação dos arquivos de migração segue este formato:

```plaintext
<V|U|R>VERSION__DESCRIPTION.sql
```

- `V`: Representa uma migração de versão (Versioned migration).
- `U`: Representa uma migração "undo", usada para desfazer uma migração aplicada anteriormente.
- `R`: Representa uma migração repetível (Repeatable migration), que pode ser executada múltiplas vezes.
- `VERSION`: A versão da migração, que pode ser um número inteiro ou uma sequência de números separados por ponto (e.g., `1`, `1.1`, `2.3.4`).
- `__` (dois underscores): Separador entre a versão e a descrição.
- `DESCRIPTION`: Uma descrição curta e amigável da migração, usando apenas letras, números e underscores (`_`). Espaços não são permitidos.
- `.sql`: A extensão do arquivo, que indica que é um script SQL.

### Exemplos de Arquivos de Migração

#### 1. Versões (Versioned Migrations):

Arquivos de migração de versão são executados **uma única vez** e em ordem sequencial de acordo com a versão especificada no nome do arquivo.
   
**Exemplo**:
```plaintext
V1__create_person_table.sql
V2__add_age_column_to_person_table.sql
V2.1__update_person_names.sql
V3__add_orders_table.sql
```
- `V1`: A primeira migração cria a tabela `person`.
- `V2`: A segunda migração adiciona a coluna `age` na tabela `person`.
- `V2.1`: Uma subversão que atualiza os nomes das pessoas.
- `V3`: A terceira versão adiciona a tabela `orders`.

#### 2. Desfazer Migrações (Undo Migrations):

Se o suporte para "undo" estiver ativado, você pode criar scripts de "undo" para reverter migrações específicas. Isso é útil se houver necessidade de desfazer alterações aplicadas por uma migração anterior.

**Exemplo**:
```plaintext
U1__drop_person_table.sql
```
- `U1`: Este arquivo desfaz a migração `V1`, removendo a tabela `person`.

#### 3. Migrações Repetíveis (Repeatable Migrations):
Migrações repetíveis são scripts que podem ser executados múltiplas vezes. Elas não têm uma versão associada, mas sempre serão reaplicadas se houver uma alteração no conteúdo do arquivo.

**Exemplo**:
```plaintext
R__refresh_materialized_view.sql
```
- `R__refresh_materialized_view`: Este arquivo será executado sempre que houver uma mudança no script SQL, independentemente de quantas vezes tenha sido executado antes.

### Regras de Versionamento e Execução

1. **Execução sequencial**: Migrações de versão (arquivos começando com `V`) são aplicadas em ordem sequencial, de acordo com o número da versão. As versões mais antigas são aplicadas antes das mais novas.
   
2. **Migrações únicas**: Migrações de versão são executadas **apenas uma vez**. Depois que uma migração é aplicada, ela não é reaplicada, a menos que seja revertida explicitamente ou o banco de dados seja reconfigurado.

3. **Migrações repetíveis**: Migrações repetíveis (arquivos começando com `R`) são executadas sempre que são modificadas ou quando Flyway detecta que é necessário reaplicar (por exemplo, ao atualizar uma view materializada).

4. **Undos opcionais**: Migrações "undo" (arquivos começando com `U`) só são aplicadas se o Flyway estiver configurado para suportar rollback e a funcionalidade for necessária.

### Boas Práticas
- **Nomes descritivos**: A descrição (`DESCRIPTION`) deve ser curta, mas suficientemente clara para explicar o que a migração faz. Isso facilita o entendimento da evolução do banco de dados.
  
  **Exemplo**:
  ```plaintext
  V1__create_user_table.sql
  V2__add_email_column_to_user_table.sql
  ```

- **Versão sem pulos**: As versões de migração devem ser sequenciais e não pular versões. O Flyway pode detectar versões que faltam, o que pode gerar erros.

- **Evitar edições em versões já aplicadas**: Após uma migração ser aplicada a um ambiente, evitar alterações nos scripts de versão. Para correções, crie uma nova migração com uma versão subsequente.

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