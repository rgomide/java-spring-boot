# Mapeando os relacionamentos

## Sumário

- [Introdução](#introdução)
- [Rotas](#rotas)
  - [Pessoas](#pessoas)
  - [Telefones](#telefones)
  - [Emails](#emails)
## Introdução

## Rotas

### Pessoas
- `GET /api/v1/people` - Listar todas as pessoas
- `GET /api/v1/people/{id}` - Buscar uma pessoa pelo ID
- `POST /api/v1/people` - Criar uma pessoa
- `PUT /api/v1/people/{id}` - Atualizar uma pessoa
- `DELETE /api/v1/people/{id}` - Excluir uma pessoa

### Telefones

- `GET /api/v1/phones` - Listar todos os telefones
- `GET /api/v1/phones/{id}` - Buscar um telefone pelo ID
- `POST /api/v1/phones` - Criar um telefone
- `PUT /api/v1/phones/{id}` - Atualizar um telefone
- `DELETE /api/v1/phones/{id}` - Excluir um telefone

### Emails

Como os emails estão relacionados obrigatoriamente a uma pessoa, as rotas do tipo `GET` e `POST` precisam receber o `personId` como parâmetro.

- `GET /api/v1/people/{personId}/emails` - Listar todos os emails de uma pessoa
- `POST /api/v1/people/{personId}/emails` - Criar um email para uma pessoa
- `PUT /api/v1/emails/{id}` - Atualizar um email
- `DELETE /api/v1/emails/{id}` - Excluir um email

## Referências
- [Generate Data](https://generatedata.com/generator)