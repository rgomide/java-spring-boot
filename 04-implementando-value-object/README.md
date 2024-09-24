# O padrão de projetos VO (value Object)

## Sumário

Versionamento

Alternativas DozerMapper

Migration (Evolutionary Database Design) com Flyway

## Exercícios

### Adicionar novo campo

Esse exercício consiste em adicionar um novo campo na tabela `people` e modificar a versão `v2` da API desenvolvida neste projeto.

#### Adicionar campo birth_day

Adicione um novo arquivo de migration para acrescentar a coluna `birth_day` do tipo `date` na tabela `people`.

**:warning:Obs.:**  interrompa a execução do projeto antes de criar o arquivo de migração de dados.

#### Modificar Entity Person

Adicione o atributo `birthDay` na classe `Person` e faça o respectivo mapeamento para a coluna do banco de dados.

#### Modificar PersonMapper para converter os campos e calcular a idade

Modifique a classe `PersonMapper` para utilizar o atributo `birthDay` ao invés de valores estáticos.

Acrescente o atributo `Integer age` na classe `PersonVOV2` e calcule a idade na hora de converter uma entidade para um VO.

#### Adicionar GET /api/people/v2 para retornar nova versão de objeto

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